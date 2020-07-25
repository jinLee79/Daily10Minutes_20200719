package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10minutes_20200719.data.Project
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

//    이 화면에서 보여줄 프로젝트의 id값
    var mProjectId = 0

//    이 화면에서 보여줄 프로젝트 자체 변수
    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

        mProjectId = intent.getIntExtra("projectId", 0)

        getProjectDetailFromServer()
    }

//    프로젝트 상세 정보를 불러오는 기능
    fun getProjectDetailFromServer() {
        ServerUtil.getRequestProjectDetail(mContext, mProjectId, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

//                projectObj로 Project 형태로 변환 => 멤버변수로 저장

                mProject = Project.getProjectFromJson(projectObj)

//                프로젝트 정보를 화면에 반영
                runOnUiThread {
                    Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
                    projectTitleViewTxt.text = mProject.title

                }
            }
        })
    }

}