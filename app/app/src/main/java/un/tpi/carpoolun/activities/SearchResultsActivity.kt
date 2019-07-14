package un.tpi.carpoolun.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import un.tpi.carpoolun.R
import un.tpi.carpoolun.adapters.CarpoolAdapter
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.models.carpool.Carpool

class SearchResultsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.SearchResults."
        const val EXTRA_TYPE = "CarpoolUN.extras.type"
        const val EXTRA_TIME = "CarpoolUN.extras.time"
        const val EXTRA_NEIGHBOURHOOD = "CarpoolUN.extras.neighbourhood"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.searchResultsActivity_title)
        setContentView(R.layout.activity_search_results)

        val intent = getIntent()
        val type = intent.getIntExtra(EXTRA_TYPE, 0)
        val neighbourhood = intent.getStringExtra(EXTRA_NEIGHBOURHOOD) ?: ""
        val time = intent.getStringExtra(EXTRA_TIME) ?: ""
        Log.d(SearchCarpoolActivity.TAG, "Searching for carpools with time=$time, neighbourhood=$neighbourhood, type=$type")
        val recyclerView : RecyclerView = findViewById(R.id.searchResultsActivity_recyclerView)


        setTitle(R.string.carpoolListFragment_title)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val activity = this
        val callback = object : Callback<List<Carpool>> {
            override fun onFailure(call: Call<List<Carpool>>, t: Throwable) {
                runOnUiThread{
                    Toast.makeText(activity, R.string.problem_connecting_to_server, Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call<List<Carpool>>, response: Response<List<Carpool>>) {
                runOnUiThread{
                    val data = response.body()
                    if ( data == null )
                        Toast.makeText(activity, R.string.problem_connecting_to_server, Toast.LENGTH_LONG).show()
                    else
                        recyclerView.adapter = CarpoolAdapter(activity, data)

                }
            }
        }

        Client.Carpools.find(type, neighbourhood, time, callback)
    }
}
