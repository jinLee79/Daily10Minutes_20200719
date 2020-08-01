package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }

    override fun setValues() {

        mProjectId = intent.getIntExtra("projectId", 0)

//        프로젝트의 제목은 intent가 넘겨주는 내용을 그대로 표시
        projectTitleTxt.text = intent.getStringExtra("projectTitle")



    }
}