package com.eliasdolinsek.simpletodo.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.asFormattedDate(): String = SimpleDateFormat.getDateInstance().format(time)