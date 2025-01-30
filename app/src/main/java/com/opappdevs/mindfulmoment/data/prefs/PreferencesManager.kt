package com.opappdevs.mindfulmoment.data.prefs

//interface PreferencesManager {
//    fun <T> set(preference: Preference<T>, value: T)
//    fun <T> get(preference: Preference<T>): T
//}

interface PreferencesManager {
    fun <T : Any> set(preference: Preference<T>, value: T)
    fun <T : Any> get(preference: Preference<T>): T
}