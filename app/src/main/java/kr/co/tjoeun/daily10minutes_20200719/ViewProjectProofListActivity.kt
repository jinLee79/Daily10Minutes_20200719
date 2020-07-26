package kr.co.tjoeun.daily10minutes_20200719

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_project_proof_list.*
import kr.co.tjoeun.daily10minutes_20200719.data.Proof
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewProjectProofListActivity : BaseActivity() {

//    몇번 프로젝트에 대한 인증목록인지
    var mProjectId = 0

//    인증 게시글들이 담길 목록
    val mProofList = ArrayList<Proof>()

//    인증을 확인할 날짜를 저장해주는 변수
//    proofDate => 기본값이 현재 시간으로 저장됨.
    val mProofDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_proof_list)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

//        날짜 변경 버튼을 누르면 proofDate에 저장된 날짜를 변경
        changeProofDateBtn.setOnClickListener {

//            날짜 선택하는 팝업창을 띄우자.
            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

//                인증 날짜를 선택된 날짜로 변경
                mProofDate.set(Calendar.YEAR, year)
                mProofDate.set(Calendar.MONTH, month)
                mProofDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//                변경된 인증 날짜를 화면에 반영
                val sdf = SimpleDateFormat("yyyy년 M월 d일")
                proofDateTxt.text = sdf.format(mProofDate.time)

//                변경ㅈ된 날짜의 인증 내역 가져오기
                getProofListFromServer()

            }, mProofDate.get(Calendar.YEAR), mProofDate.get(Calendar.MONTH), mProofDate.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()
        }

    }

    override fun setValues() {

//        이전 화면에서 넘겨준 프로젝트 id값을 멤버변수에 저장
        mProjectId = intent.getIntExtra("projectId", 0)

//        proofDate에 담긴 일시 (Calendar) 를 => 2020년 6월 9일 형태로 문구 (String) 출력
//        SimpleDateFormat의 기능 중 format 사용
        val sdf = SimpleDateFormat("yyyy년 M월 d일")

        Log.d("proofDate", mProofDate.toString())
        Log.d("sdf" , sdf.toString())

        val proofDateStr = sdf.format(mProofDate.time)
        Log.d("proofDate.time", mProofDate.time.toString())
        Log.d("proofDateStr", proofDateStr.toString())

        proofDateTxt.text = proofDateStr

//        오늘 날짜의 인증 내역 가져오기
        getProofListFromServer()
    }

//    서버에서 이 프로젝트의 날짜별 인증 내역을 가져오는 기능

    fun getProofListFromServer() {

//        선택해둔 날짜를 => 2020-06-08 형식의 String으로 가공
        val dateStr = SimpleDateFormat("yyyy-MM-dd").format(mProofDate.time)

        ServerUtil.getRequestProjectDetailWithProofList(mContext, mProjectId, dateStr, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

            }
        })
    }

}