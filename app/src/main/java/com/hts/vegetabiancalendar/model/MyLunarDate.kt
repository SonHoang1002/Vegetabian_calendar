package com.hts.vegetabiancalendar.model

class MyLunarDate {
    var day = 1
    var month = 1
    var year = ""
    var can = ""
    var chi = ""

    constructor(day: Int, month: Int, can: String, chi: String) {
        this.day = day
        this.month = month
        this.year = "%s %s".format(can, chi)
        this.can = can
        this.chi = chi
    }

    override fun toString(): String {
        var result = ""
        var strNgay = "Ngày %d".format(day)
        if (day < 10) {
            strNgay = "Mùng %d".format(day)
        }
        var strThang = "tháng %d".format(month)
        if (month == 1) {
            strThang = "tháng Giêng";
        }
        else if (month == 12) {
            strThang = "tháng Chạp";
        }

        if (month == 1 && day < 4) {
            result = "%s Tết %s".format(strNgay, year)
        }
        else {
            result = "%s, %s, năm %s".format(strNgay, strThang, year)
        }
        return result
    }
}

