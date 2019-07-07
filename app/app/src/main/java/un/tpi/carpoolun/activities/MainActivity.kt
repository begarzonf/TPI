package un.tpi.carpoolun.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.R
import un.tpi.carpoolun.fragments.CarpoolListFragment
import un.tpi.carpoolun.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.Main"
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profile -> {
                loadFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_carpoolList-> {
                loadFragment(CarpoolListFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun goToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        return true
    }

    private fun goToCarpoolList() {
        val carpoolListFragment= CarpoolListFragment()
        loadFragment(carpoolListFragment)
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
            Log.d(TAG, "LoggedUser.name ${AppPreferences.getUserName(this)}")
            Log.d(TAG, "LoggedUser.mail ${AppPreferences.getUserEmail(this)}")
            Log.d(TAG, "LoggedUser.token ${AppPreferences.getUserToken(this)}")
            val navView: BottomNavigationView = findViewById(R.id.nav_view)
            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            goToCarpoolList()
        }
    }
}
