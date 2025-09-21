package com.hts.vegetabiancalendar.model

data class MyReason (
    val reason: String
)
{
    companion object {
        val None = MyReason(reason = "Ngày bình thường")
        val VegetabianFullMonth = MyReason(reason = "Ngày ăn chay (theo lịch ăn chay hàng tháng (đủ))")
        val VegetabianLackMonth = MyReason(reason = "Ngày ăn chay (theo lịch ăn chay hàng tháng (thiếu))")
    }
}

