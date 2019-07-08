package un.tpi.carpoolun.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import un.tpi.carpoolun.R

import kotlinx.android.synthetic.main.activity_create_carpool.*

class CreateCarpool : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_carpool)
        setSupportActionBar(toolbar)
        setTitle(R.string.createCarpoolActivity_title)

    }

}
