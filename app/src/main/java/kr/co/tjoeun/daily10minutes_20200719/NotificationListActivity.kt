package kr.co.tjoeun.daily10minutes_20200719

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification_list.*
import kr.co.tjoeun.daily10minutes_20200719.adapters.NotificationAdapter
import kr.co.tjoeun.daily10minutes_20200719.data.Notification
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class NotificationListActivity : BaseActivity() {

    lateinit var mNotificationAdapter: NotificationAdapter

    val mNotificationList = ArrayList<Notification>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

//        서버에서 알림 목록 받아오기
        getNotificationListWithNotisFromServer()

    }

    override fun setValues() {

        mNotificationAdapter = NotificationAdapter(mContext, R.layout.notification_list_item, mNotificationList)
        notificationListView.adapter = mNotificationAdapter
    }

    fun getNotificationListWithNotisFromServer() {

        ServerUtil.getRequestNotificationListWithNotis(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val data = json.getJSONObject("data")
                val notifications = data.getJSONArray("notifications")

                for (i in 0 until notifications.length()) {
                    val notification = Notification.getNotificationFromJson(notifications.getJSONObject(i))
                    mNotificationList.add(notification)
                }

                runOnUiThread {
                    mNotificationAdapter.notifyDataSetChanged()
                }
            }

        })
    }
}