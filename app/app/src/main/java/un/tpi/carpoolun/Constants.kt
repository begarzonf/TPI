package un.tpi.carpoolun

object Constants {
    const val APP_PACKAGE = "un.tpi.carpoolun"
    const val SERVER_URL = "http://10.0.2.2:5005"
    const val MIN_PASSWORD_LENGTH = 6

    const val DATE_FORMAT = "yyyy-MM-dd"
    const val TIME_FORMAT = "HH:mm"
    const val FULL_FORMAT = "$DATE_FORMAT $TIME_FORMAT"

    object Preferences {
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_TOKEN = "user_token"
    }
}