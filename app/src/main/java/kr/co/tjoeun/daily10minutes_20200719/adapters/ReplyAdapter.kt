package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.ViewProofDetailActivity
import kr.co.tjoeun.daily10minutes_20200719.data.Proof
import kr.co.tjoeun.daily10minutes_20200719.data.Reply
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import kr.co.tjoeun.daily10minutes_20200719.utils.TimeUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ReplyAdapter(val mContext:Context, resId:Int, val mList:List<Reply>) : ArrayAdapter<Reply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.reply_list_item, null)
        }

        val row = tempRow!!

        val nickName = row.findViewById<TextView>(R.id.replyNickNameTxt)
        val content = row.findViewById<TextView>(R.id.contentTxt)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)

        //근거 데이터
        val data = mList[position]

        nickName.text =


        return row
    }
}