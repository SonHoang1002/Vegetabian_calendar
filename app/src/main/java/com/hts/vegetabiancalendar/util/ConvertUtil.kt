package com.hts.vegetabiancalendar.util

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hts.vegetabiancalendar.model.ModelDhammapada
import com.hts.vegetabiancalendar.model.ModelVerseDhammapada
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date
@RequiresApi(Build.VERSION_CODES.O)
class ConvertUtil {
    fun convertToDayOfWeekValue(value :Int):String{
       return when(value){
            1 -> "Thứ Hai"
            2 -> "Thứ Ba"
            3 -> "Thứ Tư"
            4 -> "Thứ Năm"
            5 -> "Thứ Sáu"
            6 -> "Thứ Bảy"
            7 -> "Chủ Nhật"
            else -> "Không phải ngày trong tuần"
        }
    }

    fun convertLocalTimeToDate(
        localDateTime: LocalDateTime,
        zoneId: ZoneId = ZoneId.systemDefault(),
        additionalDay: Int = 0,
    ): Date {
        val today = LocalDate.now().plusDays(additionalDay.toLong())
        val dateTime = LocalDateTime.of(today, localDateTime.toLocalTime())
        val instant = dateTime.atZone(zoneId).toInstant()
        return Date.from(instant)
    }

    fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }


    fun parseDhammapadaJsonToModel(context: Context): ModelDhammapada? {
        val json = loadJsonFromAsset(context, "Dhammapada.json")
        var data = json?.let {
            Gson().fromJson(it, ModelDhammapada::class.java)
        }
        Log.d("TAG", "parseDhammapadaJsonToModel: $data")
        return data
    }

}

