package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject

class User {
    var id = 0
    var email = ""
    var nickName = ""
    var projectDays = 0

//    해당 사용자가 갖고 있는 프사들을 저장할 목록
    val profileImageList = ArrayList<ProfileImage>()

    companion object {

//        jsonObject를 적당히 넣어주면 => User로 변환해주는 기능

        fun getUserFromJson (json: JSONObject) : User {

            val u = User()
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickName = json.getString("nick_name")
            u.projectDays = json.getInt("project_days")

//            사용자가 갖고 있는 프사 목록 채우기
            val profile_images = json.getJSONArray("profile_images")

            for (i in 0 until profile_images.length()) {
                val profile_imgObj = profile_images.getJSONObject(i)
                val profileImage = ProfileImage.getProfileImageFromJson(profile_imgObj)
                u.profileImageList.add(profileImage)
            }

            return u
        }
    }
}