package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject

class ProfileImage() {

    var imgId = 0
    var imgUrl = ""

    companion object {

//        프사 정보 파싱 기능
        fun getProfileImageFromJson(json:JSONObject) : ProfileImage {

            val pi = ProfileImage()

            pi.imgId = json.getInt("id")
            pi.imgUrl = json.getString("img_url")

            return pi
        }
    }

}