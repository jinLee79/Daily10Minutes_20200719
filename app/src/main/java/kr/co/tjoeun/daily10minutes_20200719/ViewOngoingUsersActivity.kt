package kr.co.tjoeun.daily10minutes_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewOngoingUsersActivity : BaseActivity() {

    var mProjectId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ongoing_users)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {
        mProjectId = intent.getIntExtra("projectId", 0)
    }
}