package kr.co.tjoeun.daily10minutes_20200719

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_proof_detail.*
import kotlinx.android.synthetic.main.reply_list_item.*
import kr.co.tjoeun.daily10minutes_20200719.data.Proof
import kr.co.tjoeun.daily10minutes_20200719.data.Reply
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import kr.co.tjoeun.daily10minutes_20200719.utils.TimeUtil
import org.json.JSONObject

class ViewProofDetailActivity : BaseActivity() {

//    목록에서 넘겨주는 인증글의 id값
    var mProofId = 0

//    이 화면에서 표시해야할 데이터들이 담긴 인증 글
    lateinit var mProof : Proof

//    서버에서 내려주는 댓글들을 담을 목록
    var mReplyList = ArrayList<Reply>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_detail)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {
        
        postReplyBtn

    }

    override fun setValues() {

        mProofId = intent.getIntExtra("proof_id", 0)
        getProofDataFromServer()
    }

    fun getProofDataFromServer() {
//        GET - /project_proof/id값 API 호출

        ServerUtil.getRequestProofDetail(mContext, mProofId, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val data = json.getJSONObject("data")
                val proof = data.getJSONObject("project")

                mProof = Proof.getProofFromJson(proof)

//                같이 담겨오는 댓글목록을 처리
                val replies = proof.getJSONArray("replies")
                for (i in 0 until replies.length()) {
                    mReplyList.add(Reply.getReplyFromJson(replies.getJSONObject(i)))
                }

                runOnUiThread {
                    writerNickNameTxt.text = mProof.user.nickName
                    createAtTxt.text = TimeUtil.getTimeAgoStringFromCalendar(mProof.proofTime)
                    contentTxt.text = mProof.content
                }

            }

        })

    }

}