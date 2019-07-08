package un.tpi.carpoolun.activities

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.R
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.models.user.LoggedUser
import un.tpi.carpoolun.models.user.NewUser


class SignUpActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.SignUp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val userNameET                 = findViewById<EditText>(R.id.signUp_user_name)
        val userEmailET                = findViewById<EditText>(R.id.signUp_user_email)
        val userPasswordET             = findViewById<EditText>(R.id.signUp_user_password)
        val userPasswordConfirmationET = findViewById<EditText>(R.id.signUp_user_passwordConfirmation)

        val signUpButton = findViewById<Button>(R.id.signUp_button)
        val goToSignIn = findViewById<Button>(R.id.goToSignIn_button)
        val context = this

        val callback = object : Callback<LoggedUser> {
            override fun onFailure(call: Call<LoggedUser>, t: Throwable) {
                Log.d(TAG, "Failed to create user")
                runOnUiThread {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.failed_to_create_user),
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<LoggedUser>, response: Response<LoggedUser>) {
                Log.d(TAG, "Created user successfully" )
                val loggedUser = response.body()
                Log.d(TAG, "loggedUser= ${loggedUser?.toString()}")
                if ( loggedUser == null || !loggedUser.isGood()) {
                    Log.d(TAG, "Failed to create user")
                    runOnUiThread {
                        Toast.makeText(
                            context,
                            resources.getString(R.string.failed_to_create_user),
                            Toast.LENGTH_SHORT).show()
                    }
                    return
                }

                AppPreferences.saveUserSession(context, loggedUser)

                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        resources.getString(R.string.user_created_successfully),
                        Toast.LENGTH_SHORT).show()
                }

                goToMainActivity()
            }
        }

        goToSignIn.setOnClickListener { goToSignInActivity() }
        signUpButton.setOnClickListener {
            val name = userNameET.text.toString()
            val email = userEmailET.text.toString()
            val password = userPasswordET.text.toString()
            val passwordConfirmation = userPasswordConfirmationET.text.toString()
            if ( password != passwordConfirmation ) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        resources.getString(R.string.passwords_do_not_match),
                        Toast.LENGTH_SHORT).show()
                }

            }
            val user = NewUser(
                name = name,
                email = email,
                password = password
            )
            Client.Users.create(user, callback)
        }
    }

    private fun goToSignInActivity() {
        val intent = Intent (this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    /**
     * Disables going back to other activity from SignUpActivity with the back key.
     */
    override fun onBackPressed() {
        moveTaskToBack(true);
    }

}
