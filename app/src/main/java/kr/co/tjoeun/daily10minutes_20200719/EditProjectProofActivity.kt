package kr.co.tjoeun.daily10minutes_20200719

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_project_proof.*
import kr.co.tjoeun.daily10minutes_20200719.data.Project

class EditProjectProofActivity : BaseActivity() {

    var mProjectId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_project_proof)
        setUpEvents()
        setValues()
    }
    override fun setUpEvents() {

        postProofBtn.setOnClickListener {

            val input = proofMessageEdt.text.toString()

            if (input.length < 5) {
                Toast.makeText(mContext, "인증 내용은 5자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            하루에 한번만 올릴 수 있으니 => 진짜 올릴건지 확인받고 올리자.

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("오늘의 인증하기")
            alert.setMessage("인증글은 하루에 한번만 올릴 수 있습니다. 정말 인증 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

//                의사가 확인 되었으니 => 서버에 게시글을 올리자.



            })
            alert.setNegativeButton("취소", null)
            alert.show()


        }

    }

    override fun setValues() {

        mProjectId = intent.getIntExtra("projectId", 0)

//        프로젝트의 제목은 intent가 넘겨주는 내용을 그대로 표시
        projectTitleTxt.text = intent.getStringExtra("projectTitle")



    }
}