package com.dicoding.asclepius.helper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun showToast(activity: AppCompatActivity, message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun setDateFromMillis(millis: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd | HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    return sdf.format(calendar.time)
}