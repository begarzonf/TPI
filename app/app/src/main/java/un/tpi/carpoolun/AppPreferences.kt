package un.tpi.carpoolun

import android.content.Context
import un.tpi.carpoolun.model.LoggedUser

object AppPreferences {


    fun getUserId(context: Context) : Int {
        val preferences = context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
        return preferences.getInt(Constants.Preferences.USER_ID, 0)
    }

    fun getUserToken(context: Context): String {
        val preferences = context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
        return preferences.getString(Constants.Preferences.USER_TOKEN, "")!!
    }

    fun getUserName(context: Context): String {
        val preferences = context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
        return preferences.getString(Constants.Preferences.USER_NAME, "")!!
    }

    fun getUserEmail(context: Context): String {
        val preferences = context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
        return preferences.getString(Constants.Preferences.USER_EMAIL, "")!!
    }

    fun saveUserSession(context: Context, user: LoggedUser) {
        context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
            .edit()
            .putInt(Constants.Preferences.USER_ID, user.id!!)
            .putString(Constants.Preferences.USER_NAME, user.name!!)
            .putString(Constants.Preferences.USER_EMAIL, user.email!!)
            .putString(Constants.Preferences.USER_TOKEN, user.token!!)
            .commit()
    }

    fun deleteUserSession(context: Context) {
        context.getSharedPreferences(Constants.APP_PACKAGE, Context.MODE_PRIVATE)
            .edit()
            .remove(Constants.Preferences.USER_ID)
            .remove(Constants.Preferences.USER_NAME)
            .remove(Constants.Preferences.USER_EMAIL)
            .remove(Constants.Preferences.USER_TOKEN)
            .commit()
    }
}