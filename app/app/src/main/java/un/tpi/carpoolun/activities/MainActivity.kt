package un.tpi.carpoolun.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.R

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.Main"
    }

    fun goToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Client.Users.list()
        if ( AppPreferences.getUserToken(this) == "" ) {
            goToSignInActivity()
        }
        else {
            Log.d(TAG, "LoggedUser.id ${AppPreferences.getUserId(this)}")
            Log.d(TAG, "LoggedUser.name ${AppPreferences.getUserId(this)}")
            Log.d(TAG, "LoggedUser.mail ${AppPreferences.getUserId(this)}")
            Log.d(TAG, "LoggedUser.token ${AppPreferences.getUserId(this)}")
        }
    }
}
