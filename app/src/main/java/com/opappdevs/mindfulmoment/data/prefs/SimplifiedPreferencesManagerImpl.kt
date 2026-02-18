package com.opappdevs.mindfulmoment.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SimplifiedPreferencesManagerImpl @Inject constructor(
    context: Context
) : PreferencesManager {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    //TODO: perform in coroutine scope?

    override fun <T : Any> set(preference: Preference<T>, value: T) {
        when (preference) {
            is Preference.BooleanPreference -> setBooleanPreference(preference, value as Boolean)
            is Preference.IntPreference -> setIntPreference(preference, value as Int)
            is Preference.LongPreference -> setLongPreference(preference, value as Long)
            is Preference.FloatPreference -> setFloatPreference(preference, value as Float)
            is Preference.StringPreference -> setStringPreference(preference, value as String?)
            //is Preference.StringSetPreference -> setStringSetPreference(preference, value as Set<String>)
        }
    }

    @Suppress("UNCHECKED_CAST")
    //TODO: ignored for now
    // ...as we will switch to KMP compatible solution anyway
    override fun <T : Any> get(preference: Preference<T>): T {
        return when (preference) {
            is Preference.BooleanPreference -> getBooleanPreference(preference)
            is Preference.IntPreference -> getIntPreference(preference)
            is Preference.LongPreference -> getLongPreference(preference)
            is Preference.FloatPreference -> getFloatPreference(preference)
            is Preference.StringPreference -> getStringPreference(preference)
            //is Preference.StringSetPreference -> getStringSetPreference(preference)
            //else -> throw IllegalArgumentException("Unsupported preference type: ${preference::class.simpleName}")
        } as T
    }

    // Private type-specific functions with explicit casts and smart casts
    private fun setBooleanPreference(preference: Preference.BooleanPreference, value: Boolean) {
        prefs.edit { putBoolean(preference.key, value) }
    }

    private fun getBooleanPreference(preference: Preference.BooleanPreference): Boolean {
        return prefs.getBoolean(preference.key, preference.defaultValue)
    }

    private fun setIntPreference(preference: Preference.IntPreference, value: Int) {
        prefs.edit { putInt(preference.key, value) }
    }

    private fun getIntPreference(preference: Preference.IntPreference): Int {
        return prefs.getInt(preference.key, preference.defaultValue)
    }

    private fun setLongPreference(preference: Preference.LongPreference, value: Long) {
        prefs.edit { putLong(preference.key, value) }
    }

    private fun getLongPreference(preference: Preference.LongPreference): Long {
        return prefs.getLong(preference.key, preference.defaultValue)
    }

    private fun setFloatPreference(preference: Preference.FloatPreference, value: Float) {
        prefs.edit { putFloat(preference.key, value) }
    }

    private fun getFloatPreference(preference: Preference.FloatPreference): Float {
        return prefs.getFloat(preference.key, preference.defaultValue)
    }

    private fun setStringPreference(preference: Preference.StringPreference, value: String?) {
        prefs.edit { putString(preference.key, value) }
    }

    private fun getStringPreference(preference: Preference.StringPreference): String? {
        return prefs.getString(preference.key, preference.defaultValue)
    }

//    private fun setStringSetPreference(preference: Preference.StringSetPreference, value: Set<String>) {
//        prefs.edit { putStringSet(preference.key, value) }
//    }
//
//    private fun getStringSetPreference(preference: Preference.StringSetPreference): Set<String> {
//        return prefs.getStringSet(preference.key, preference.defaultValue) ?: emptySet()
//    }

    companion object {
        private const val PREFS_NAME = "app_prefs"
    }
}