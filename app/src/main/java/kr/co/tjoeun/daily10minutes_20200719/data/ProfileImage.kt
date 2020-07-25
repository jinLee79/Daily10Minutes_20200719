package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject

class ProfileImage(
    var imgId: Int,
    var uploadeId: Int,
    var imgUrl: String) {

    fun getProfileImageFromJson(json:JSONObject) : ProfileImage {

        val imgId = json.getInt("id")
        val uploaderId = json.getInt("user_id")
        val imgUrl = json.getString("img_url")

        return ProfileImage(imgId, uploadeId, imgUrl)
    }

}