package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Notification {

    var id = 0
    var title = ""
    val createdAt = Calendar.getInstance()
    lateinit var actUser : User

    companion object {

        private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        fun getNotificationFromJson(json: JSONObject) : Notification {

            val n = Notification()

            n.id = json.getInt("id")
            n.title = json.getString("title")
            n.createdAt.time = sdf.parse(json.getString("created_at"))

            n.actUser = User.getUserFromJson(json.getJSONObject("act_user"))

            return n
        }
    }

}