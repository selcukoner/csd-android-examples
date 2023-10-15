package org.cso.android.app.hilt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.cso.android.app.hilt.datetime.DateInfo
import org.cso.android.app.hilt.datetime.DateTimeInfo
import org.cso.android.app.hilt.datetime.TimeInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo:TimeInfo

    private fun showDateTime()
    {

        var sb = StringBuilder()

        sb.append("DateTime:").append(dateTimeInfo.toString()).append("\n")
            .append("Date:").append(dateInfo.toString()).append("\n")
            .append("Time:").append(timeInfo.toString()).append("\n")

        Toast.makeText(this, sb, Toast.LENGTH_LONG).show()
    }

    private fun initialize()
    {
        setContentView(R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

    }

    override fun onResume() {
        showDateTime()
        super.onResume()
    }
}