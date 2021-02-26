package com.narawit.todo.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun String.isEmailValid(): Boolean {
    val expression = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.passwordValid(): Boolean {
    val expression = "^(?=.*[0-9]+.*)(?=.*[a-z]+.*)(?=.*[A-Z]+.*)[0-9a-zA-Z]{8,20}\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}


fun String.toDate(format: String = Constants.DEFAULT_FORMAT): Date? {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.parse(this)
}

fun Date.toString(format: String = Constants.DEFAULT_FORMAT): String? {
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}


