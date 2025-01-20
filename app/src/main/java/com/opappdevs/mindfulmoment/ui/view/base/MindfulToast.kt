package com.opappdevs.mindfulmoment.ui.view.base

import android.content.Context
import android.widget.Toast

//TODO: create custom composable Toast with centered multi-line text and icon at the top

class MindfulToast{
    companion object {
        enum class Duration(val duration: Int) {
            SHORT (Toast.LENGTH_SHORT),
            LONG (Toast.LENGTH_LONG),
        }
        fun showMindfulToast(context: Context, messageRes: Int, duration: Duration) {
            Toast.makeText(context, messageRes, duration.duration).show()
        }
    }
}