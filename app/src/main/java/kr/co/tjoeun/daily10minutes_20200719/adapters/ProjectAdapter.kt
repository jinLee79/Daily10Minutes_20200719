package kr.co.tjoeun.daily10minutes_20200719.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minutes_20200719.R
import kr.co.tjoeun.daily10minutes_20200719.data.Project
import org.w3c.dom.Text

class ProjectAdapter(val mContext:Context, resId:Int, val mList:List<Project>) : ArrayAdapter<Project>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.project_list_item, null)
        }

        val row = tempRow!!

        val projectImg = row.findViewById<ImageView>(R.id.projectImg)
        val projectTitleViewTxt = row.findViewById<TextView>(R.id.projectTitleViewTxt)
        val projectDescriptionTxt = row.findViewById<TextView>(R.id.projectDescriptionTxt)

        val data = mList[position]

        projectTitleViewTxt.text = data.title
        projectDescriptionTxt.text = data.description

//        웹상의 어느 주소에 있는 이미지
//        어느 화면에서 어떤 데이터를 어디에 뿌려줄거야?
        Glide.with(mContext).load(data.imageUrl).into(projectImg)

        return tempRow
    }
}