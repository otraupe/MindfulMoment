package com.opappdevs.mindfulmoment.data.prefs

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

    //TODO: not working
//    data class StringSetPreference(
//        override val key: String,
//        override val defaultValue: Set<String> = emptySet()
//    ) : Preference<Set<String>>
}