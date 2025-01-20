package com.opappdevs.mindfulmoment.ui.view.base.permission

import android.Manifest
import com.opappdevs.mindfulmoment.R

enum class Permission(
    val id: String,
    val rationaleRes: Int
) {
    NOTIFICATION (
        id = Manifest.permission.POST_NOTIFICATIONS, //TODO: ?
        rationaleRes = R.string.ui_permissions_notifications_rationale
    ),

    ;
}