package un.tpi.carpoolun.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import un.tpi.carpoolun.R
import un.tpi.carpoolun.activities.SearchCarpoolActivity
import un.tpi.carpoolun.adapters.CarpoolAdapter
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.models.MockData
import un.tpi.carpoolun.models.carpool.Carpool

class CarpoolListFragment : Fragment() {

    companion object {
        const val TAG = "CarpoolUN.CarpoolListF"
    }

    private fun goToSearchActivity() {
        val activity = activity
        activity?.let {
            val intent = Intent(activity, SearchCarpoolActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View  =  inflater.inflate(R.layout.fragment_carpool_list,null)
        val recyclerView : RecyclerView = v.findViewById(R.id.carpoolListFragment_recyclerView)
        val serachCarpoolButton : FloatingActionButton = v.findViewById(R.id.carpoolListFragment_searchFAB)

        val activity = activity

        activity ?: return v

        activity.setTitle(R.string.carpoolListFragment_title)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        serachCarpoolButton.setOnClickListener{ goToSearchActivity() }

        val callback = object : Callback<List<Carpool>> {
            override fun onFailure(call: Call<List<Carpool>>, t: Throwable) {
                activity.runOnUiThread{
                    Toast.makeText(activity, R.string.problem_connecting_to_server, Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call<List<Carpool>>, response: Response<List<Carpool>>) {
                activity.runOnUiThread{
                    val data = response.body()
                    if ( data == null )
                        Toast.makeText(activity, R.string.problem_connecting_to_server, Toast.LENGTH_LONG).show()
                    else
                        recyclerView.adapter = CarpoolAdapter(activity, data)

                }
            }
        }
        Client.Carpools.list(callback)

        return v
    }
}