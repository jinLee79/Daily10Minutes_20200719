package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.Reply
import kr.co.tjoeun.daily10minutes_20200719.utils.ServerUtil
import kr.co.tjoeun.daily10minutes_20200719.utils.TimeUtil
import org.json.JSONObject
import java.util.*

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
        val replyWrittenTime = row.findViewById<TextView>(R.id.replyWrittenTimeTxt)

//        val likeLayout = row.findViewById<LinearLayout>(R.id.likeLayout)

        //근거 데이터
        val data = mList[position]

        replyWriterNickName.text = data.writer.nickName
        replyContent.text = data.content

//        작성 시간 표시
        replyWriterNickName.text = TimeUtil.getTimeAgoStringFromCalendar(data.createdAt)

//        좋아요 갯수 처리 => 0개면 숨김, 그 이상이면 보여주고 갯수 반영
        if (data.likeCount == 0) {
//            likeLayout.visibility = View.GONE
            likeCntTxt.visibility = View.GONE
        }
        else {
            likeCntTxt.visibility = View.VISIBLE
            likeCntTxt.text = "좋아요 ${data.likeCount}개"
        }

//        내 좋아요 여부에 따른 처리
//        myLike = true : 빨간 테두리 + 빨간 글씨 (naverRed) + 좋아요 취소
//        반대 : 회색 테두리 + 검정 글씨 + 좋아요
        if (data.myLike) {
//            테두리 => xml로 그려둔 drawable 활용
            likeBtn.setBackgroundResource(R.drawable.red_border_box)

//            빨간 글씨 => res > colors.xml 에 있는 항목 (naverRed) 가져오기
//            likeBtn.setTextColor(R.color.naverRed)  //이거 에러남
//            likeBtn.setTextColor(Color.RED)  //이건 기본 컬러들
            likeBtn.setTextColor(mContext.resources.getColor(R.color.naverRed))

//            좋아요 취소로 문구 변경
            likeBtn.text = "좋아요 취소"
        }
        else {
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)

//            검정 글씨 => 기본색에서 BLACK 지원 (기본 색 사용법 예시 => 실무에선 잘 쓰지 않음)
            likeBtn.setTextColor(Color.BLACK)
            likeBtn.text = "좋아요"
        }

        likeBtn.setOnClickListener {
            ServerUtil.postRequestLikeReply(mContext, data.id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    서버가 알려주는 댓글의 변경된 상태를 data변수에 반영 (ui 바로 반영되게)
                    val dataObj = json.getJSONObject("data")

//                    dataObj 내부의 JSONObject => Reply 형태로 변환
                    val like = Reply.getReplyFromJson(dataObj.getJSONObject("like"))

//                    이미 리스트뷰에 뿌려져 있는 data 변수의 값 일부 변경
                    data.likeCount = like.likeCount
                    data.myLike = like.myLike

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