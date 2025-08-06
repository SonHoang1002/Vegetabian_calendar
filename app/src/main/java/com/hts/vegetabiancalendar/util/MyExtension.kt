package com.hts.vegetabiancalendar.util

import android.os.Build
import androidx.annotation.RequiresApi
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


