package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setUpEvents()

    }

    override fun setUpEvents() {

        loginBtn.setOnClickListener {

//            입력한 아이디 / 비번 받아오기
            val inputId = emailEdt.text.toString()
            val inputPw = pwdEdt.text.toString()

//            서버에 로그인 요청
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }

            })

        }

    }

    override fun setValues() {

    }


}