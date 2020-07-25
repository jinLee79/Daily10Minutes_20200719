package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject

class ProfileImage() {

    var imgId = 0
    var uploaderId = 0
    var imgUrl = ""

    fun getProfileImageFromJson(json:JSONObject) : ProfileImage {

        val p = ProfileImage()

        p.imgId = json.getInt("id")
        p.uploaderId = json.getInt("user_id")
        p.imgUrl = json.getString("img_url")

        return p
    }

}