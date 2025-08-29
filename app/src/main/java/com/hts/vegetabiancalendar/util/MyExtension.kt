package com.hts.vegetabiancalendar.util

import MyCalendarService
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Paint
import com.hts.vegetabiancalendar.model.BodhisattvasDay
import com.hts.vegetabiancalendar.model.MyLunarDate
import com.hts.vegetabiancalendar.model.MyReason
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_1
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_10
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_11
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_12
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_2
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_3
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_4
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_5
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_6
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_7
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_8
import com.hts.vegetabiancalendar.util.MyConstant.Companion.LIST_BODHISSATTVAS_DAY_IN_MONTH_9
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toDate(zoneId: ZoneId = ZoneId.systemDefault()): Date {
    val instant = this.atZone(zoneId).toInstant()
    return Date.from(instant)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return this.toInstant().atZone(zoneId).toLocalDateTime()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toZonedDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime {
    return this.toInstant().atZone(zoneId)
}

@RequiresApi(Build.VERSION_CODES.O)
fun ZonedDateTime.toDate(): Date {
    return Date.from(this.toInstant())
}

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return this.toInstant().atZone(zoneId).toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Instant.toDate(): Date = Date.from(this)

@RequiresApi(Build.VERSION_CODES.O)
fun Date.toInstant(): Instant = this.toInstant()

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.isVegetabianDayWithSolarLocalDateTime(): Pair<Boolean, MyReason> {
    val calendarService = MyCalendarService(this)
    val currentLunarDate: MyLunarDate = calendarService.myLunarDate

    val currentLunarDay = currentLunarDate.day
    val currentLunarMonth = currentLunarDate.month


    when (currentLunarMonth) {
        1 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_1.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        2 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_2.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        3 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_3.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        4 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_4.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        5 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_5.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        6 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_6.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        7 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_7.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        8 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_8.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        9 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_9.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        10 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_10.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        11 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_11.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        12 -> {
            for (day in LIST_BODHISSATTVAS_DAY_IN_MONTH_12.listBodhisattvaDay) {
                if (day.lunarDay == currentLunarDay) {
                    return Pair(
                        true,
                        day.reason
                    )
                }
            }
        }

        else -> {
            return Pair(
                false,
                MyReason.None
            )
        }
    }
    val isFullMonth = calendarService.isCurrentLunarMonthFull()

    if (isFullMonth) {
        if (MyConstant.LIST_VEGATABIAN_DAY_IN_MONTH_FULL.contains(currentLunarDay)) {
            return Pair(
                true,
                MyReason.VegetabianFullMonth
            )
        }
    } else {
        if (MyConstant.LIST_VEGATABIAN_DAY_IN_MONTH_LACK.contains(currentLunarDay)) {
            return Pair(
                true,
                MyReason.VegetabianLackMonth
            )
        }
    }
    return Pair(
        false,
        MyReason.None
    )
}

fun BodhisattvasDay.toListDay(): List<Int> {
    return this.listBodhisattvaDay.map { day -> day.lunarDay }
}

