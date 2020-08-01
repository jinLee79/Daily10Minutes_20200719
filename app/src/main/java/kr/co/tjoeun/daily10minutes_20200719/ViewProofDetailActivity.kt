package kr.co.tjoeun.daily10minutes_20200719

import android.os.Bundle
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil

class ViewProofDetailActivity : BaseActivity() {

//    목록에서 넘겨주는 인증글의 id값
    var mProofId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_detail)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

        mProofId = intent.getIntExtra("proof_id", 0)
        getProofDataFromServer()
    }

    fun getProofDataFromServer() {
//        GET - /project_proof/id값 API 호출

    }

}