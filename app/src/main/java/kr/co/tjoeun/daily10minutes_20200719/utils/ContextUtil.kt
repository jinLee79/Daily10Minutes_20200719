package kr.co.tjoeun.daily10minutes_20200719.utils

import android.content.Context

class ContextUtil {

    companion object {

//        일종의 저장할 파일 이름
        private val prefName = "daily10minutesPref"

        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        fun setLoginUserToken(context: Context, token: String) {
//            파일 열기 (파일이름, 모드설정)
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            파일 편집, 최종 반영
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getLoginUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            return 하되, 만약 default value 설정해줌.
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

    }
}