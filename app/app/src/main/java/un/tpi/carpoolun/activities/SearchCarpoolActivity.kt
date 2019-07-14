package un.tpi.carpoolun.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import un.tpi.carpoolun.R
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import un.tpi.carpoolun.fragments.CarpoolFormFragment


class SearchCarpoolActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.SearchCarpool"
    }


    private fun loadFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_carpool)
        val searchButton : FloatingActionButton = findViewById(R.id.searchCarpoolActivity_searchButton)
        setTitle(R.string.searchCarpoolActivity_title)

        val fragment = CarpoolFormFragment()
        loadFragment(fragment)

        val activity = this

        searchButton.setOnClickListener{
            val carpool = fragment.getCarpool()
            val time = carpool.time
            val neighbourhood = carpool.neighbourhood
            val type = carpool.type
            Log.d(TAG, "User selected time=$time, neighbourhood=$neighbourhood, type=$type")

            if ( time == null ) {
                runOnUiThread {
                    Toast.makeText(activity, R.string.must_select_time_and_date, Toast.LENGTH_SHORT).show()
                }
            } else if ( neighbourhood == null || neighbourhood == "") {
                runOnUiThread{
                    Toast.makeText(activity, R.string.must_select_neighbourhood, Toast.LENGTH_SHORT).show()
                }
            } else if ( type == null ) {
                runOnUiThread{
                    Toast.makeText(activity, R.string.must_select_type, Toast.LENGTH_SHORT).show()
                }
            } else {
                val intent = Intent(this, SearchResultsActivity::class.java)
                intent.putExtra(SearchResultsActivity.EXTRA_TIME, time)
                intent.putExtra(SearchResultsActivity.EXTRA_TYPE, type)
                intent.putExtra(SearchResultsActivity.EXTRA_NEIGHBOURHOOD, neighbourhood)
                startActivity(intent)
            }
        }
    }
}
