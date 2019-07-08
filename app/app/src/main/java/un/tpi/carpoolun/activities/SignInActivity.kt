package un.tpi.carpoolun.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import un.tpi.carpoolun.AppPreferences
import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.R
import un.tpi.carpoolun.client.Client
import un.tpi.carpoolun.models.user.LoggedUser
import un.tpi.carpoolun.models.user.SignInData

class SignInActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CarpoolUN.SignIn"
    }

    data class LoginViewModel(val email: String, val password: String, val login : Button) {
        fun loginDataChanged(username: String, password: String) {
            if (!isEmailValid(username)) {
                login.isEnabled = false
            } else {
                login.isEnabled = isPasswordValid(password)
            }
        }

        // A placeholder username validation check
        private fun isEmailValid(username: String): Boolean {
            return if (username.contains('@')) {
                Patterns.EMAIL_ADDRESS.matcher(username).matches()
            } else {
                username.isNotBlank()
            }
        }

        // A placeholder password validation check
        private fun isPasswordValid(password: String): Boolean {
            return password.length >= Constants.MIN_PASSWORD_LENGTH;
        }
    }

    private lateinit var loginViewModel: LoginViewModel
    private var loading : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_sign_in)

        loading = findViewById(R.id.loading)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login_button)
        var goToSignUp = findViewById<Button>(R.id.goToSignUp_button)
        loginViewModel = LoginViewModel("","",login)
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading?.visibility = View.VISIBLE
                login(username.text.toString(), password.text.toString())
            }
        }

        goToSignUp.setOnClickListener { goToSignUpActivity() }
    }

    fun login(email: String, password: String) {

        val activity= this

        val callback = object : Callback<LoggedUser> {
            override fun onFailure(call: Call<LoggedUser>, t: Throwable) {
                showLoginFailed()
            }

            override fun onResponse(call: Call<LoggedUser>, response: Response<LoggedUser>) {
                val loggedUser = response.body()
                Log.d(TAG, "Logged user is $loggedUser")
                if ( loggedUser == null || !loggedUser.isGood()) {
                    showLoginFailed()
                }
                else {
                    Log.d(TAG, "Successfuly")
                    runOnUiThread {
                        AppPreferences.saveUserSession(activity, loggedUser)
                        showLogginSuccessful()
                        goToMainActivity()
                    }
                }
            }
        }

        Client.Users.signIn(SignInData(email = email, password = password), callback)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun goToSignUpActivity() {
        val intent = Intent (this, SignUpActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun showLogginSuccessful() {
        val activity = this
        this.runOnUiThread {
            Toast.makeText(activity, R.string.login_successful, Toast.LENGTH_SHORT).show()
        }

    }

    private fun showLoginFailed() {
        val activity = this
        this.runOnUiThread {
            Toast.makeText(activity, R.string.login_failed, Toast.LENGTH_LONG).show()
            loading?.visibility = View.INVISIBLE
        }
    }

    /**
     * Disables going back to other activity from SignInActivity with the back key.
     */
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
