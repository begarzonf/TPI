package un.tpi.carpoolun.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import un.tpi.carpoolun.R

class CarpoolListFragment : Fragment() {

    companion object {
        const val TAG = "CarpoolUN.CarpoolListF"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View  =  inflater.inflate(R.layout.fragment_carpool_list,null)


        return v
    }
}