package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.Proof
import kr.co.tjoeun.daily10minutes_20200719.data.User
import java.text.SimpleDateFormat

class ProofAdapter(val mContext:Context, resId:Int, val mList:List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val userProfileImg = row.findViewById<ImageView>(R.id.userProfileImg)
        val userNickName = row.findViewById<TextView>(R.id.userNickNameTxt)
        val proofTime = row.findViewById<TextView>(R.id.proofTimeTxt)
        val content = row.findViewById<TextView>(R.id.contentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)

        val likeCnt = row.findViewById<Button>(R.id.likeBtn)
        val replyCnt = row.findViewById<Button>(R.id.replyBtn)

        //근거 데이터
        val data = mList[position]

        content.text = data.content
        userNickName.text = data.user.nickName

        Glide.with(mContext).load(data.user.profileImageList[0].imgUrl).into(userProfileImg)

//        인증일시 : 2020년 6월 9일 오전 2시 8분 양식으로 출력
        proofTime.text = SimpleDateFormat("yyyy년 M월 d일 a h시 m분").format(data.proofTime.time)

//        그림이 있느냐? 없느냐 구별 해야 함 => How? data의 이미지 주소 목록의 크기값 확인
        if (data.imageUrlList.size == 0) {
//            그림이 첨부가 안 된 경우 => 이미지뷰 숨김
            proofImg.visibility = View.GONE
        }
        else {
//            한장 이상의 그림이 첨부된 경우 => 이미지뷰 표시
            proofImg.visibility = View.VISIBLE

//            맨 앞장에 첨부된 그림을 실제로 표시
            Glide.with(mContext).load(data.imageUrlList[0]).into(proofImg)
        }

        likeCnt.text = "좋아요 ${data.likeCnt.toString()} 개"
        replyCnt.text = "답글 ${data.replyCnt.toString()} 개"

        return row
    }
}