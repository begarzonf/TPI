package un.tpi.carpoolun.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.R
import un.tpi.carpoolun.models.carpool.Carpool
import java.text.SimpleDateFormat
import java.util.*

class CarpoolFormFragment : Fragment() {

    companion object {
        const val TAG = "EagleUN.CarpoolForm"
        const val TYPE = "EagleUN.CarpoolForm.Type"
        const val TYPE_SEARCH = 0
        const val TYPE_CREATE = 1
    }

    private var dateTextView : TextView? = null
    private var timeTextView: TextView? = null
    private var spinner : Spinner? = null
    private var neighbourhood : EditText? = null

    private var year  : Int = -1
    private var month : Int = -1
    private var day   : Int = -1

    private var hour   : Int = -1
    private var minute : Int = -1

    private val lock = Object()

    private val dateFormat = SimpleDateFormat(Constants.DATE_FORMAT)
    private val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT)
    private val fullFormat = SimpleDateFormat(Constants.FULL_FORMAT)

    private fun getTime() : String? {
        synchronized(lock) {
            val y = year
            val M = month
            val d = day
            val H = hour
            val m = minute
            if ( y == -1 || M == -1 || d == -1 || H == -1 || m == -1 ) return null
            val t = GregorianCalendar(y, M, d, H, m).time
            return fullFormat.format(t)
        }
    }

    private fun getType() : Int? {
        return spinner?.selectedItemPosition
    }

    private fun getneighbourhood() : String? {
        return neighbourhood?.text?.toString()
    }

    fun getCarpool() : Carpool {
        val c = Carpool(
            time = getTime(),
            type = getType(),
            neighbourhood = getneighbourhood()
        )
        Log.d(TAG, "Carpool is : $c")
        return c
    }


    private fun updateTextViews() {
        val t = GregorianCalendar(year, month, day, hour, minute).time
        dateTextView?.text = dateFormat.format(t)
        timeTextView?.text = timeFormat.format(t)
    }

    private fun showDatePicker() {
        synchronized(lock) {
            val activity = activity
            activity ?: return
            val listener =  object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
                    year = selectedYear
                    month = selectedMonth
                    day = selectedDay
                    activity.runOnUiThread{ updateTextViews()  }
                }
            }
            val dialog = DatePickerDialog(activity, R.style.datePickerTheme, listener, year, month, day)
            dialog.show()
        }
    }

    private fun showTimePicker() {
        synchronized(lock) {
            val activity = activity
            activity ?: return
            val listener =  object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourSelected: Int, minuteSelected: Int) {
                    hour = hourSelected
                    minute = minuteSelected
                    activity.runOnUiThread{ updateTextViews()  }
                }
            }
            val dialog = TimePickerDialog(activity, R.style.datePickerTheme, listener, hour, minute, true)
            dialog.show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_carpool_form, null)


        val activity = activity
        activity ?: return v

        val createCarpoolPart : View = v.findViewById(R.id.carpoolFormFragment_createCarpoolPart)

        val fragmentType  = arguments!!.getInt(TYPE)
        if ( fragmentType.equals(TYPE_CREATE) ) createCarpoolPart.visibility = View.VISIBLE
        else createCarpoolPart.visibility = View.GONE

        dateTextView = v.findViewById(R.id.fragmentCarpoolForm_date)
        timeTextView = v.findViewById(R.id.searchCarpoolActivity_time)
        spinner = v.findViewById(R.id.fragmentCarpoolForm_spinner)
        neighbourhood = v.findViewById(R.id.fragmentCarpoolForm_neighbourhood)

        val array = resources.getStringArray(R.array.createCarpoolActivity_comeOrGoesList)
        val adapter : ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, array)
        spinner?.adapter = adapter

        val cal = Calendar.getInstance()
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)

        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)

        updateTextViews()

        dateTextView?.setOnClickListener{ showDatePicker() }
        timeTextView?.setOnClickListener{ showTimePicker() }
        return v
    }
}