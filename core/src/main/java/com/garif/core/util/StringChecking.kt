package com.garif.core.util

import android.text.TextUtils
import java.util.regex.Pattern

fun String.isValidName(): Boolean {
    return Pattern.compile("[А-я]+").matcher(this)
        .matches()
}

fun String.isValidPhoneNumber(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Pattern.compile("[+]7 [0-9]{3} [0-9]{3}-[0-9]{2}-[0-9]{2}").matcher(this)
                .matches()
}