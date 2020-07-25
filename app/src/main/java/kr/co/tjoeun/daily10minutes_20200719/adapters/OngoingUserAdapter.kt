package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.User

class OngoingUserAdapter(val mContext:Context, resId:Int, val mList:List<User>) : ArrayAdapter<User>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.ongoing_user_list_item, null)
        }

        val row = tempRow!!

        val profileImg = row.findViewById<ImageView>(R.id.profileImg)  //ImageView는 CircleImageView의 부모이므로 다형성 적용 가능
        val nickName = row.findViewById<TextView>(R.id.nickNameTxt)
        val email = row.findViewById<TextView>(R.id.emailTxt)
        val challengeDays = row.findViewById<TextView>(R.id.challengeDaysTxt)

        val data = mList[position]

        nickName.text = data.nickName
        email.text = data.email
        challengeDays.text = "도전 ${data.projectDays}일차"

//        profiles_images
//        Glide.with(mContext).load(data.)

        return row
    }
}