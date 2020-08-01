package kr.co.tjoeun.daily10minutes_20200719.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class TimeUtil {

    companion object {

//        시간을 재료로 넣으면 시차를 보정해서 => 상황에 맞는 문구로 알려주는 기능

        fun getTimeAgoStringFromCalendar(cal : Calendar) : String {

//            cal 을 => 핸드폰에 설정된 시간에 맞는 시차 계산
//            cal 내부에는, 어느 시간대 (서울, 영국, 뉴욕 등) 인지 정보가 저장되어 있다.
//            시간 값만 서버에서 준 그대로 받아적어둔 상태.

//            핸드폰의 시간대와 => 표준시간대의 시차를 구해서
//            cal의 시간을 보정해주자.

            val myPhoneTimeZone = cal.timeZone  //한국 폰: 한국 시간대, 영국: 영국 시간대

//            표준 시간대 (UTC / GMT +00:00) 과의 몇시간 차이가 나는지 계산.
//            rawOffset : 시차를 밀리초 단위까지 기록. => 시간단위로 변환
            val timeOffSet = myPhoneTimeZone.rawOffset / 1000 / 60 / 60

//            별도로 시차를 계산하기 위한 Calendar 변수를 따로 쓰자
            val offsetCalendar = Calendar.getInstance()
            offsetCalendar.time = cal.time

//            시차를 계산하기 위한 변수에게 시간값을(시차만큼) 증가시키자
            offsetCalendar.add(Calendar.HOUR, timeOffSet)

//            재료로 들어온 cal에게 시간값을 시차만큼 증가시키자.
            cal.add(Calendar.HOUR, timeOffSet)

//            양식을 가공해서 String으로 리턴
//            상황에 따라 다른 문구를 리턴하자.
//            10초 이내 : 방금 전
//            1분 이내 : ?초 전
//            1시간 이내 : ?분 전
//            12시간 이내 : ?시간 전
//            그 이상 : 양식으로 가공

//            현재 시간이 몇 시인지 알아야 함. => Calendar를 새로 만들면 => 현재 시간이 기록되어 있다.
            val now = Calendar.getInstance()

//            현재시간 - 재료로 들어오는 시간 => 간격이 얼마나 되는가?
//            게산결과 : 1800000 등의 숫자로 나옴.
//            1800000ms : 1800s : 30m
            val msDiff = now.timeInMillis - offsetCalendar.timeInMillis

            if (msDiff < 10 * 1000 ) {  // 10초 이내
                return "방금 전"
            }
            else if (msDiff < 1 * 60 * 1000) { //1분 이내
                val second = msDiff / 1000
                return "${second}초 전"
            }
            else if (msDiff < 1 * 60 * 60 * 1000 ) {
                val minute = msDiff / 1000 / 60
                return "${minute}분 전"
            }
            else if (msDiff < 12 * 60 * 60 * 1000 ) {
                val hour = msDiff / 1000 / 60 / 60
                return "${hour}시간 전"
            }
            else {
                val sdf = SimpleDateFormat("yyyy년 M월 d일 a h시 m분")
                return sdf.format(offsetCalendar.time)
            }
        }
    }
}