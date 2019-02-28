package dsm.android.v3.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


private fun getPref(context: Context): SharedPreferences {
    return context.getSharedPreferences("pref", Context.MODE_PRIVATE)
}

fun saveToken(context: Context, token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTEzNjA1ODIsIm5iZiI6MTU1MTM2MDU4MiwianRpIjoiYzhkYWJhYWEtNTM0MC00Yzc0LTlkZjctMTAwZjZkMWNkZjJkIiwiZXhwIjoxNTUxMzY0MTgyLCJpZGVudGl0eSI6ImhlbGxvIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.OIpWRhI1d99emnmb39kcT189wXni6XF65c2AXMLbT1w", isAccess: Boolean = true) {
    getPref(context).edit().let {
        it.putString(getKey(isAccess), token)
        it.apply()
    }
}

fun removeToken(context: Context, isAccess: Boolean = true) {
    getPref(context).edit().let {
        it.remove(getKey(isAccess))
        it.apply()
    }
}

fun getToken(context: Context, isAccess: Boolean = true): String {
    return "" + getPref(context).getString(getKey(isAccess), "")
}

private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"
