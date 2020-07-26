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

class ProofAdapter(val mContext:Context, resId:Int, val mList:List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow==null) {
            tempRow = inf.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!



        return row
    }
}