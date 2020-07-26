package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_project_proof_list.*
import java.text.SimpleDateFormat
import java.util.*

class ViewProjectProofListActivity : BaseActivity() {

//    인증을 확인할 날짜를 저장해주는 변수
//    proofDate => 기본값이 현재 시간으로 저장됨.
    val proofDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_proof_list)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

//        proofDate에 담긴 일시 (Calendar) 를 => 2020년 6월 9일 형태로 문구 (String) 출력
//        SimpleDateFormat의 기능 중 format 사용
        val sdf = SimpleDateFormat("yyyy년 M월 d일")

        Log.d("proofDate", proofDate.toString())
        val proofDateStr = sdf.format(proofDate.time)
        Log.d("proofDate.time", proofDate.time.toString())
        proofDateTxt.text = proofDateStr

    }
}