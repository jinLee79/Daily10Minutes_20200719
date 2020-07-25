package kr.co.tjoeun.daily10minutes_20200719

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_ongoing_users.*
import kr.co.tjoeun.daily10minutes_20200719.adapters.OngoingUserAdapter
import kr.co.tjoeun.daily10minutes_20200719.data.Project
import kr.co.tjoeun.daily10minutes_20200719.data.User
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class ViewOngoingUsersActivity : BaseActivity() {

    var mProjectId = 0

//    서버에서 받아온 프로젝트 정보
    lateinit var mProject : Project

//    사용자 정보를 저장할 목록
    val mOngoingUsersList = ArrayList<User>()

    lateinit var mOngoingUserAdapter : OngoingUserAdapter

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

        mOngoingUserAdapter = OngoingUserAdapter(mContext, R.layout.ongoing_user_list_item, mOngoingUsersList)
        usersListView.adapter = mOngoingUserAdapter

    }

//    진행 중인 사람 명단 + 상세정보 불러오기

    fun getOngoingUsersFromServer() {
        ServerUtil.getRequestProjectDetailWithUsers(mContext, mProjectId, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                mProject = Project.getProjectFromJson(projectObj)

//                projectObject 내부에 ongoing_users배열을 활용해서 사용자 명단을 목록에 저장
                val ongoingUsers = projectObj.getJSONArray("ongoing_users")

                for (i in 0 until ongoingUsers.length() ) {

                    val ongoingUserObj = ongoingUsers.getJSONObject(i)
                    val ongoingUser = User.getUserFromJson(ongoingUserObj)

//                    val profileImages = ongoingUserObj.getJSONArray("profile_images")
//
//                        for (j in 0 until profileImages.length()) {
//                            val profileImg = profileImages.getJSONObject(j)
//
//                        }

                    mOngoingUsersList.add(ongoingUser)
                }


//                프로젝트 정보 UI 반영
                runOnUiThread {
                    titleTxt.text = mProject.title
                    userCountTxt.text = "참여 중 인원 : ${mProject.ongoingUsersCount}명"

//                    참여중인 사용자 명단도 반영
                    mOngoingUserAdapter.notifyDataSetChanged()

                }

            }
        })
    }
}