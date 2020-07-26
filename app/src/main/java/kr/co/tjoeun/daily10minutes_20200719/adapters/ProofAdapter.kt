package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

        //근거 데이터
        val data = mList[position]

        content.text = data.content
        userNickName.text = data.user.nickName
        proofTime.text = SimpleDateFormat("yyyy년 MM월 dd일 a h시 m분 s초").format(data.proofTime.time)
        Glide.with(mContext).load(data.user.profileImageList[0].imgUrl).into(userProfileImg)


        return row
    }
}