package kr.co.tjoeun.daily10minutes_20200719

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kr.co.tjoeun.daily10minutes_20200719.utils.ContextUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

        val myHandler = Handler()

//        시간 좀 지나면 일 해주세요 => 무슨 일을 실행할지는 람다에 적어주기
        myHandler.postDelayed({

//            로그인을 했느냐 안 했느냐는
//            토큰에 저장된 값이 있느냐, 빈칸이냐로 구별하자.
//            전제는 Splash 화면이 처음 화면인 것을 AndroidManifest.xml에서 설정한 이후 (NoActionBar 속성도 추가해주었음)
            if (ContextUtil.getLoginUserToken(mContext) == "") {
//                로그인을 아직 안 한 경우
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
            }
            else {
//                로그인을 해둔 경우
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
            }

            //스플래쉬 화면은 종료
            finish()

        }, 2500)

    }

}