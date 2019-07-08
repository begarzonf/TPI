package un.tpi.carpoolun.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.R
import java.text.SimpleDateFormat
import java.util.*
import android.util.TypedValue
import android.widget.TimePicker

class SearchCarpoolActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.SearchCarpool"
    }

    private var dateTextView : TextView? = null
    private var timeTextView: TextView? = null

    private var year  : Int = -1
    private var month : Int = -1
    private var day   : Int = -1

    private var hour   : Int = -1
    private var minute : Int = -1

    private val lock = Object()

    private val dateFormat = SimpleDateFormat(Constants.DATE_FORMAT)
    private val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT)

    private fun updateTextViews() {
        val t = GregorianCalendar(year, month, day, hour, minute).time
        dateTextView?.text = dateFormat.format(t)
        timeTextView?.text = timeFormat.format(t)
    }

    private fun showDatePicker(color: Int) {
        synchronized(lock) {
            val listener =  object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    runOnUiThread{ updateTextViews()  }
                }
            }
            val dialog = DatePickerDialog(this, R.style.datePickerTheme, listener, year, month, day)
            dialog.show()
        }
    }

    private fun showTimePicker(color: Int) {
        synchronized(lock) {
            val listener =  object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourSelected: Int, minuteSelected: Int) {
                    hour = hourSelected
                    minute = minuteSelected
                    runOnUiThread{ updateTextViews()  }
                }
            }
            val dialog = TimePickerDialog(this, R.style.datePickerTheme, listener, hour, minute, true)
            dialog.show()
        }
    }

    private fun searchCarpool() {
        // TODO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_carpool)
        setTitle(R.string.searchCarpoolActivity_title)

        dateTextView = findViewById(R.id.searchCarpoolActivity_date)
        timeTextView = findViewById(R.id.searchCarpoolActivity_time)
        val searchButton : Button = findViewById(R.id.searchCarpoolActivity_searchButton)

        val cal = Calendar.getInstance()
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)

        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)

        updateTextViews()

        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
        val primaryColor = typedValue.data


        dateTextView?.setOnClickListener{ showDatePicker(primaryColor) }
        timeTextView?.setOnClickListener{ showTimePicker(primaryColor) }
        searchButton.setOnClickListener{ searchCarpool() }

    }
}
