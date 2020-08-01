package kr.co.tjoeun.daily10minutes_20200719.data

import org.json.JSONObject

class Project {

    var id = 0;       //Integer
    var title = ""    //String
    var imageUrl = ""
    var description = ""

    var proofMethod = ""
    var ongoingUsersCount = 0

//    내 현재 진행 상태를 표시하는 변수 : null => 참가 해본 적이 없는 상태
    var myLastStatus : String? = null

//    완주까지 필요한 인증 횟수
    var completeDays = 0

//    내가 그동안 인증한 횟수
    var proofCount = 0

    companion object {

//        적절한 JSONObject를 재료로 받아서 => Project 객체로 뽑아주는 기능

        fun getProjectFromJson(json:JSONObject) : Project {

            val p = Project()

//            json에 들어있는 데이터들을 이용해서 => p의 데이터로 대입
            p.id = json.getInt("id")
            p.title = json.getString("title")
            p.imageUrl = json.getString("img_url")
            p.description = json.getString("description")

            p.proofMethod = json.getString("proof_method")
            p.ongoingUsersCount = json.getInt("ongoing_users_count")

//            내 진행상태는 null이 아닐 때만 파싱하자.
            if (!json.isNull("my_last_status")) {
//                파싱 진행
                p.myLastStatus = json.getString("my_last_status")

                p.completeDays = json.getInt("complete_days")

//                내 인증 횟수는 목록을 불러올 떄는 안 내려줌. => null인지 체크하고나서 파싱
                if (!json.isNull("proof_count")) {
                    p.proofCount = json.getInt("proof_count")
                }

            }


//            완성된 p를 리턴
            return p
        }

    }

}