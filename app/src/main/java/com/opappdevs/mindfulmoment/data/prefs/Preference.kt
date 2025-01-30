package com.opappdevs.mindfulmoment.data.prefs

//sealed class Preference<T>(val key: String, val defaultValue: T) {
//    class BooleanPreference(key: String, defaultValue: Boolean) : Preference<Boolean>(key, defaultValue)
//    class IntPreference(key: String, defaultValue: Int) : Preference<Int>(key, defaultValue)
//    class LongPreference(key: String, defaultValue: Long) : Preference<Long>(key, defaultValue)
//    class FloatPreference(key: String, defaultValue: Float) : Preference<Float>(key, defaultValue)
//    class StringPreference(key: String, defaultValue: String) : Preference<String>(key, defaultValue)
//    class StringSetPreference(key: String, defaultValue: Set<String>) : Preference<Set<String>>(key, defaultValue)
//}

sealed interface Preference<T> {
    val key: String
    val defaultValue: T

    data class BooleanPreference(
        override val key: String,
        override val defaultValue: Boolean = false
    ) : Preference<Boolean>

    data class IntPreference(
        override val key: String,
        override val defaultValue: Int = 0
    ) : Preference<Int>

    data class LongPreference(
        override val key: String,
        override val defaultValue: Long = 0L
    ) : Preference<Long>

    data class FloatPreference(
        override val key: String,
        override val defaultValue: Float = 0f
    ) : Preference<Float>

    data class StringPreference(
        override val key: String,
        override val defaultValue: String = ""
    ) : Preference<String>

//    data class StringSetPreference(
//        override val key: String,
//        override val defaultValue: Set<String> = emptySet()
//    ) : Preference<Set<String>>
}