package un.tpi.carpoolun.activities

import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import un.tpi.carpoolun.R

import kotlinx.android.synthetic.main.activity_create_carpool.*
import un.tpi.carpoolun.fragments.CarpoolFormFragment

class CreateCarpool : AppCompatActivity() {

    private fun loadFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.createCarpoolActivity_title)
        setContentView(R.layout.activity_create_carpool)

        val createButton : FloatingActionButton = findViewById(R.id.createCarpoolActivity_createButton)

        val fragment = CarpoolFormFragment()
        loadFragment(fragment)

        createButton.setOnClickListener{
            fragment.getCarpool()
        }
    }
}
