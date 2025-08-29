package com.hts.vegetabiancalendar.model

data class BodhisattvaDay(
    val lunarDay: Int,
    val lunarMonth: Int,
    val reason: MyReason,
)

data class BodhisattvasDay(
    var lunarMonth: Int,
    val listBodhisattvaDay: List<BodhisattvaDay>,
)