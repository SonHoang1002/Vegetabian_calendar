package com.hts.vegetabiancalendar.model

import MyCalendarService
import MyLunarDate
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
data class EventDay(
    val solarDateTime: LocalDateTime,
    val event: String
) {
    fun getLunarDateTime(): MyLunarDate =
        MyCalendarService(solarDateTime).myLunarDate
    fun getDate(): LocalDate = solarDateTime.toLocalDate()
    fun getHour(): Int = solarDateTime.hour
    fun getMinute(): Int = solarDateTime.minute
    fun getSecond(): Int = solarDateTime.second

    fun getFormattedTime(pattern: String = "HH:mm dd-MM-yyyy"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return solarDateTime.format(formatter)
    }

    fun isToday(): Boolean {
        return solarDateTime.toLocalDate() == LocalDate.now()
    }

    fun isPast(): Boolean {
        return solarDateTime.isBefore(LocalDateTime.now())
    }

    fun isFuture(): Boolean {
        return solarDateTime.isAfter(LocalDateTime.now())
    }
}