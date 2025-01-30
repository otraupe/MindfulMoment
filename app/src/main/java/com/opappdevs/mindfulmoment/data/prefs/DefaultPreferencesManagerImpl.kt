package com.opappdevs.mindfulmoment.data.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class DefaultPreferencesManagerImpl @Inject constructor(
    private val context: Context
) /*: PreferencesManager*/ {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

//    override fun <T> set(preference: Preference<T>, value: T) {
//        with(prefs.edit()) {
//            when (preference) {
//                is Preference.BooleanPreference -> putBoolean(preference.key, value as Boolean)
//                is Preference.IntPreference -> putInt(preference.key, value as Int)
//                is Preference.LongPreference -> putLong(preference.key, value as Long)
//                is Preference.FloatPreference -> putFloat(preference.key, value as Float)
//                is Preference.StringPreference -> putString(preference.key, value as String)
//                //is Preference.StringSetPreference -> putStringSet(preference.key, value as Set<String>)
//            }
//            apply()
//        }
//    }
//
//    override fun <T> get(preference: Preference<T>): T {
//        return when (preference) {
//            is Preference.BooleanPreference -> prefs.getBoolean(preference.key, preference.defaultValue) as T
//            is Preference.IntPreference -> prefs.getInt(preference.key, preference.defaultValue) as T
//            is Preference.LongPreference -> prefs.getLong(preference.key, preference.defaultValue) as T
//            is Preference.FloatPreference -> prefs.getFloat(preference.key, preference.defaultValue) as T
//            is Preference.StringPreference -> prefs.getString(preference.key, preference.defaultValue) as T
//            //is Preference.StringSetPreference -> prefs.getStringSet(preference.key, preference.defaultValue) as T
//        }
//    }

//    // Type-specific inline functions for getting preferences
//    @Suppress("OVERRIDE_BY_INLINE")
//    inline fun <reified T> get(preference: Preference<T>): T {
//        return when (preference) {
//            is Preference.BooleanPreference -> prefs.getBoolean(preference.key, preference.defaultValue) as T
//            is Preference.IntPreference -> prefs.getInt(preference.key, preference.defaultValue) as T
//            is Preference.LongPreference -> prefs.getLong(preference.key, preference.defaultValue) as T
//            is Preference.FloatPreference -> prefs.getFloat(preference.key, preference.defaultValue) as T
//            is Preference.StringPreference -> prefs.getString(preference.key, preference.defaultValue) as T
//            is Preference.StringSetPreference -> {
//                prefs.getStringSet(preference.key, preference.defaultValue)
//                    ?.filterNotNull()
//                    ?.toSet() as T
//            }
//        }
//    }
//
//    // Type-specific inline functions for setting preferences
//    @Suppress("OVERRIDE_BY_INLINE")
//    inline fun <reified T> set(preference: Preference<T>, value: T) {
//        with(prefs.edit()) {
//            when (preference) {
//                is Preference.BooleanPreference -> putBoolean(preference.key, value as Boolean)
//                is Preference.IntPreference -> putInt(preference.key, value as Int)
//                is Preference.LongPreference -> putLong(preference.key, value as Long)
//                is Preference.FloatPreference -> putFloat(preference.key, value as Float)
//                is Preference.StringPreference -> putString(preference.key, value as String)
//                is Preference.StringSetPreference -> putStringSet(preference.key, value as Set<String>)
//            }
//            apply()
//        }
//    }
}
