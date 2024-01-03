package com.rentwiz.app.coreui.utils

import android.os.Build

object Utils {

    fun isAndroidPOrLater() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
}