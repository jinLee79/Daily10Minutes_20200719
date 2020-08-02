package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.Notification
import kr.co.tjoeun.daily10minutes_20200719.utils.TimeUtil

class NotificationAdapter(val mContext:Context, resId:Int, val mList:List<Notification>) : ArrayAdapter<Notification>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.notification_list_item, null)
        }

        val row = tempRow!!

        val notifyTitle = row.findViewById<TextView>(R.id.notifyTitleTxt)
        val notifyCreatedAt = row.findViewById<TextView>(R.id.notifyCreatedAtTxt)
        val notifyActUser = row.findViewById<ImageView>(R.id.notifyActUserImg)

        val data = mList[position]

        notifyTitle.text = data.title
        notifyCreatedAt.text = TimeUtil.getTimeAgoStringFromCalendar(data.createdAt)

        Glide.with(mContext).load(data.actUser.profileImageList[0]).into(notifyActUser)

        return row
    }
}