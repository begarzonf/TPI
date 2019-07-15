package un.tpi.carpoolun.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.R
import un.tpi.carpoolun.activities.CreateCarpoolActivity
import un.tpi.carpoolun.activities.SignInActivity

class ProfileFragment : Fragment() {

    companion object {
        const val TAG = "CarpoolUN.CarpoolListF"
    }

    private fun logOut() {
        val context = activity
        context?.let{
            AppPreferences.deleteUserSession(context)
            val intent = Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun goToCreateCarpoolActivity() {
        val activity = activity
        activity?.let {
            val intent = Intent(activity, CreateCarpoolActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View =  inflater.inflate(R.layout.fragment_profile,null)

        val emailTV : TextView          = v.findViewById(R.id.profileFragment_email_textView)
        val nameTV : TextView           = v.findViewById(R.id.profileFragment_name_textView)
        val logoutButton : Button       = v.findViewById(R.id.profileFragment_logOut_button)
        val createCarpool : FloatingActionButton = v.findViewById(R.id.fragmentProfile_FAB)

        logoutButton.setOnClickListener{ logOut() }

        val activity = activity
        activity?.let {
            activity.setTitle(R.string.profileFragment_title)
            nameTV.text =  AppPreferences.getUserName(activity)
            emailTV.text = AppPreferences.getUserEmail(activity)
        }

        createCarpool.setOnClickListener{ goToCreateCarpoolActivity() }
        return v
    }
}