package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setUpEvents()
    abstract fun setValues()
}