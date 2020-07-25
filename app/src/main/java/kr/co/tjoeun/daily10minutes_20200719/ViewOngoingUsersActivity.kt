package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_ongoing_users.*
import kr.co.tjoeun.daily10minutes_20200719.data.Project
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class ViewOngoingUsersActivity : BaseActivity() {

    var mProjectId = 0

//    서버에서 받아온 프로젝트 정보
    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ongoing_users)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {
        mProjectId = intent.getIntExtra("projectId", 0)
        getOngoingUsersFromServer()
    }

//    진행 중인 사람 명단 + 상세정보 불러오기

    fun getOngoingUsersFromServer() {
        ServerUtil.getRequestProjectDetailWithUsers(mContext, mProjectId, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                mProject = Project.getProjectFromJson(projectObj)

//                프로젝트 정보 UI 반영
                runOnUiThread {
                    titleTxt.text = mProject.title
                    userCountTxt.text = "참여 중 인원 : ${mProject.ongoingUsersCount}명"
                }

            }
        })
    }
}