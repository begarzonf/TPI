package un.tpi.carpoolun.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import un.tpi.carpoolun.R
import android.widget.*
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

        searchButton.setOnClickListener{
            fragment.getCarpool()
        }
    }
}
