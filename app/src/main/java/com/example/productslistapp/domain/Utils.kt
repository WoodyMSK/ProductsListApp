package com.example.productslistapp.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun parseAndFormatDate(inputDate: String): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy");
    val calendar = Calendar.getInstance();
    calendar.timeInMillis = inputDate.toLong();
    return formatter.format(calendar.time)
}