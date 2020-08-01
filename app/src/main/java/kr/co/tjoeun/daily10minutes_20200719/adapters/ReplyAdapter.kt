package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.Reply
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import org.json.JSONObject

class ReplyAdapter(val mContext:Context, resId:Int, val mList:List<Reply>) : ArrayAdapter<Reply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.reply_list_item, null)
        }

        val row = tempRow!!

        val replyWriterNickName = row.findViewById<TextView>(R.id.replyWriterNickNameTxt)
        val replyContent = row.findViewById<TextView>(R.id.replyContentTxt)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val likeCntTxt = row.findViewById<TextView>(R.id.likeCntTxt)

        val likeLayout = row.findViewById<LinearLayout>(R.id.likeLayout)

        //근거 데이터
        val data = mList[position]

        replyWriterNickName.text = data.writer.nickName
        replyContent.text = data.content

        if (data.likeCount == 0) {
            likeLayout.visibility = View.GONE
        }
        else {
            likeCntTxt.text = "좋아요 ${data.likeCount}개"
        }

        likeBtn.setOnClickListener {
            ServerUtil.postRequestLikeReply(mContext, data.id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    서버가 주는 메세지를 토스트로 출력
                    val message = json.getString("message")

                    val uiHandler = Handler(Looper.getMainLooper())

                    uiHandler.post {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

//                        어댑터 새로고침
                        notifyDataSetChanged()
                    }
                }

            })
        }

        return row
    }
}