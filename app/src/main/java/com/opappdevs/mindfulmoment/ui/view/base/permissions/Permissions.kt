package com.opappdevs.mindfulmoment.ui.view.base.permissions

import android.Manifest
import com.opappdevs.mindfulmoment.R

enum class Permissions(
    val id: String,
    val rationaleRes: Int
) {
    POST_NOTIFICATION (
        id = Manifest.permission.POST_NOTIFICATIONS,
        rationaleRes = R.string.ui_permissions_notifications_rationale
    ),;
}