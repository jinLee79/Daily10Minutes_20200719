package kr.co.tjoeun.daily10minutes_20200719

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

//    커스텀 액션바 xml에서 만들어둔 뷰들은 멤버변수로 만들고 , 직접 연결하자.
//    BaseActivity를 상속받는 모든 액티비티들이 => 이 변수를 같이 상속받게 된다.
    lateinit var notificationImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        가장 처음 실행되는 SplashActivity 화면에 NoActionBar 설정 해뒀으므로 에러남
//        액션바가 있는지 확인하고 실행. (하단에서 액션바 null 아니라고 우겼으니깐.. )
        supportActionBar?.let {

//            액션바가 null이 아닐 때만 실행되는 내용
            setCustomActionBar()
        }

    }

//    각각의 화면마다 다 구현해야할 내용이 달라지는 함수: abstract
    abstract fun setUpEvents()
    abstract fun setValues()

//    모든 화면에서 실행해야할 내용이 같다 => 무슨 일을 할지도 실제 작성

    fun setCustomActionBar() {

//        귀찮게 null 체크 계속 하지 않도록, 액션바가 절대 null이 아니라고 별개의 변수에 옮겨닮자.
        val myActionBar = supportActionBar!!

//        액션바를 커스텀으로 사용할 수 있도록 세팅
        myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        실제로 보여줄 커스텀 화면을 세팅
        myActionBar.setCustomView(R.layout.custom_action_bar)

//        커스텀액션바 뒤의 기본 색 제거 => 액션바를 들고 있는 툴바의 좌우 여백을 0ㅇ로 설정하자.
        val parentToolbBar = myActionBar.customView.parent as Toolbar
        parentToolbBar.setContentInsetsAbsolute(0, 0)   //내부 여백 설정을 절대값으로 하는 메소드

//        액션바 XML에 있는 뷰들을 => kt(코틀린)에서도 사용할 수 있도록 연결
        notificationImg = myActionBar.customView.findViewById(R.id.notificationImg)
    }
}