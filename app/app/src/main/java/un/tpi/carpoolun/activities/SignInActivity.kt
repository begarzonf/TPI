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
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.R

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

    fun login(username: String, password: String) {

        /*val context = this
        val loginCallback = object : ApolloCall.Callback<CreateNewUserSessionMutation.Data> (){
            override fun onFailure(e: ApolloException) {
                showLoginFailed(R.string.login_failed)
            }

            override fun onResponse(response: Response<CreateNewUserSessionMutation.Data>) {
                val r = response.data()?.createNewUserSession()
                val userId =  r?.id()!!
                val token = r?.jwt()
                if ( r == null || token == null ) {
                    showLoginFailed(R.string.login_failed)
                }
                else {
                    Client.reset(token)
                    ElsaPreferences.setUserId(context, userId)
                    ElsaPreferences.setSessionJwt(context, token)
                    showLogginSuccessful()
                    goToMainActivity()
                }
            }
        }

        Client.createUserSession(email=usergname, password = password, callback = loginCallback  )*/
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
            val welcome = getString(R.string.welcome)
            Toast.makeText(activity, "$welcome", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Log.d(TAG, "Login failed")
        val activity = this
        this.runOnUiThread {
            Toast.makeText(activity, activity.resources.getString(errorString), Toast.LENGTH_LONG).show()
        }
        loading?.visibility = View.INVISIBLE
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
