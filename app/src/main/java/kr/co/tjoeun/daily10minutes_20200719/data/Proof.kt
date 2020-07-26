package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Proof {
    var id = 0
    var content = ""
    var proofTime = Calendar.getInstance()  // 인증 일시를 분석해서 세팅 예정
    var imageUrlList = ArrayList<String>()  // 이미지의 주소만 따서 목록으로 저장

    lateinit var user : User  // 인증을 올린 사람에 대한 정보

//    좋아요 / 댓글 개수 저장 변수
    var likeCnt = 0
    var replyCnt = 0

    companion object {

//        적당한 json을 넣으면 => Proof 로 변환해주는 기능

        fun getProofFromJson(json:JSONObject) : Proof {
            val p = Proof()

            p.id = json.getInt("id")
            p.content = json.getString("content")

//            서버에서는 인증일시를 String으로 알려줌.
//            앱에서는 인증일시를 Calendar로 저장해야 함.
//            String => Calendar로 변환. SimpleDateFormat 필요.
            val proofTimeStr = json.getString("proof_time")

//            서버에서 내려주는 양식을 읽어올 SimpleDateFormat 생성
//            "2020-06-09 02:53:34" 양식 분석
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//            sdf를 이용해서 => 캘린더 변수에 시간 대입
            p.proofTime.time = sdf.parse(proofTimeStr)


//            images JSONArray 내부의 JSONObject 내부의 image_url들을 String으로 얻어내서
//            p.이미지 주소 목록에 저장
            val images =  json.getJSONArray("images")

            for (i in 0 until images.length()) {
                val imgObj = images.getJSONObject(i)
                val imgUrl = imgObj.getString("img_url")
                p.imageUrlList.add(imgUrl)
            }

//            인증글 작성자 정보 파싱

//            user JSONObject 추출 => User클래스 변환 => p.user에 저장
            val userObj = json.getJSONObject("user")
            p.user = User.getUserFromJson(userObj)

            p.likeCnt = json.getInt("like_count")
            p.replyCnt = json.getInt("reply_count")

            return p
        }
    }
}