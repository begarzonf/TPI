package un.tpi.carpoolun.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import un.tpi.carpoolun.R
import un.tpi.carpoolun.adapters.CarpoolAdapter
import un.tpi.carpoolun.models.MockData

class CarpoolListFragment : Fragment() {

    companion object {
        const val TAG = "CarpoolUN.CarpoolListF"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View  =  inflater.inflate(R.layout.fragment_carpool_list,null)
        val recyclerView : RecyclerView = v.findViewById(R.id.carpoolsRecyclerView)

        val activity = activity

        activity ?: return v
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = CarpoolAdapter(activity, MockData.carpoolList())
        return v
    }
}