package un.tpi.carpoolun.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import un.tpi.carpoolun.Client
import un.tpi.carpoolun.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Client.listUsers()
    }
}
