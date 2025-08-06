package com.hts.vegetabiancalendar.service

import MyCalendarService
import android.os.Build
import androidx.annotation.RequiresApi
import isFullMonth
import java.time.LocalDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
class VegetabianDayService {
    companion object {
        val LIST_VEGATABIAN_DAY_IN_MONTH_FULL  = listOf(1, 8, 14, 15, 18, 23, 24, 28, 29, 30)
        val LIST_VEGATABIAN_DAY_IN_MONTH_LACK  = listOf(1, 8, 14, 15, 18, 23, 24, 27, 28, 29)
    }

    fun getLunarDayFromSunDayInMonth(solarLocalDateTime: LocalDateTime):Int {
        return MyCalendarService(solarLocalDateTime).myLunarDate.day
    }

    fun isFullLunarMonth(solarLocalDateTime: LocalDateTime):Boolean{
        return MyCalendarService(solarLocalDateTime).isCurrentLunarMonthFull()
    }

    fun isVegetabianDay(solarLocalDateTime: LocalDateTime): Boolean {
        val isFullMonth = isFullLunarMonth(solarLocalDateTime)
        val lunarDayInMonth = getLunarDayFromSunDayInMonth(solarLocalDateTime)
        if(isFullMonth){
            return LIST_VEGATABIAN_DAY_IN_MONTH_FULL.contains(lunarDayInMonth)
        }else{
            return LIST_VEGATABIAN_DAY_IN_MONTH_LACK.contains(lunarDayInMonth)
        }
    }
}