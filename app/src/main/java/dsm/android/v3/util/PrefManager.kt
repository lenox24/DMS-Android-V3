package dsm.android.v3.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


private fun getPref(context: Context): SharedPreferences {
    return context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    Log.e("ASDFASDF", "ㅁㄻㄴㄻㄴㅇㄹ")
}

fun saveToken(context: Context, token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTExODY4MzEsIm5iZiI6MTU1MTE4NjgzMSwianRpIjoiN2Q1MzNmYjAtMjFmNS00ZjEyLWIxMWUtYjRkMTFlYzlhNjllIiwiZXhwIjoxNTUxMTkwNDMxLCJpZGVudGl0eSI6ImhlbGxvIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.PjsPmjSqO2jkQf4XZ22VObY4l2jXsYp98AraMGnp6yg", isAccess: Boolean = true) {
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
