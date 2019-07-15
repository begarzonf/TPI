package un.tpi.carpoolun.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import un.tpi.carpoolun.R

import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.fragments.CarpoolFormFragment
import un.tpi.carpoolun.models.carpool.Carpool

class CreateCarpoolActivity : AppCompatActivity() {

    private fun loadFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        return true
    }

    fun showToast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }

    fun gotToMainActivity() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.createCarpoolActivity_title)
        setContentView(R.layout.activity_create_carpool)

        val createButton : FloatingActionButton = findViewById(R.id.createCarpoolActivity_createButton)

        val fragment = CarpoolFormFragment()
        val bundle = Bundle()
        bundle.putInt(CarpoolFormFragment.TYPE, CarpoolFormFragment.TYPE_CREATE)
        fragment.arguments = bundle
        loadFragment(fragment)
        val activity = this

        val callback = object : Callback<Carpool>{
            override fun onFailure(call: Call<Carpool>, t: Throwable) {
                runOnUiThread { showToast(R.string.problem_connecting_to_server) }
            }

            override fun onResponse(call: Call<Carpool>, response: Response<Carpool>) {
                runOnUiThread {
                    showToast(R.string.carpool_created_successfully)
                    gotToMainActivity()
                }
            }
        }

        createButton.setOnClickListener{
            val carpool = fragment.getCarpool()

            if ( carpool.time == null ) {
                runOnUiThread {  showToast(R.string.must_select_time_and_date)  }
            } else if ( carpool.neighbourhood == null || carpool.neighbourhood == "") {
                runOnUiThread{ showToast(R.string.must_select_neighbourhood) }
            } else if ( carpool.type == null ) {
                runOnUiThread{  showToast(R.string.must_select_type) }
            } else if ( carpool.capacity == null ) {
                runOnUiThread{  showToast(R.string.must_specify_capacity) }
            } else if ( carpool.fee == null ) {
                runOnUiThread{  showToast(R.string.must_specify_fee) }
            } else {
                val newCarpool = Carpool(
                    driverId = AppPreferences.getUserId(activity),
                    driverName = AppPreferences.getUserName(activity),
                    time = carpool.time,
                    capacity = carpool.capacity,
                    capacityLeft = carpool.capacity - 1,
                    neighbourhood = carpool.neighbourhood,
                    type = carpool.type,
                    fee = carpool.fee
                )
                Client.Carpools.create(newCarpool, callback)
            }
        }
    }
}
