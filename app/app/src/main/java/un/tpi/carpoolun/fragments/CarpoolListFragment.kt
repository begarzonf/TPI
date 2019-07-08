package un.tpi.carpoolun.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import un.tpi.carpoolun.R
import un.tpi.carpoolun.activities.SearchCarpoolActivity
import un.tpi.carpoolun.adapters.CarpoolAdapter
import un.tpi.carpoolun.models.MockData

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
        recyclerView.adapter = CarpoolAdapter(activity, MockData.carpoolList())
        serachCarpoolButton.setOnClickListener{ goToSearchActivity() }
        return v
    }
}