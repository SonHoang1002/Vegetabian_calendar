package com.hts.vegetabiancalendar.util
import com.hts.vegetabiancalendar.model.BodhisattvaDay
import com.hts.vegetabiancalendar.model.BodhisattvasDay
import com.hts.vegetabiancalendar.model.MyReason


class MyConstant {
    companion object {


        val LIST_VEGATABIAN_DAY_IN_MONTH_FULL  = listOf(1, 8, 14, 15, 18, 23, 24, 28, 29, 30)

        val LIST_VEGATABIAN_DAY_IN_MONTH_LACK  = listOf(1, 8, 14, 15, 18, 23, 24, 27, 28, 29)

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_1 = BodhisattvasDay(
            lunarMonth = 1,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 1,
                    lunarMonth = 1,
                    reason = MyReason(
                        reason = "Ngày vía Đức Di Lặc"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 15,
                    lunarMonth = 1,
                    reason = MyReason(
                        reason = "Ngày Lễ Thượng Nguyên"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_2 = BodhisattvasDay(
            lunarMonth = 2,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 8,
                    lunarMonth = 2,
                    reason = MyReason(
                        reason = "Ngày Phật Thích Ca xuất gia"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 15,
                    lunarMonth = 2,
                    reason = MyReason(
                        reason = "Ngày Phật Thích Ca nhập Niết Bàn"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 19,
                    lunarMonth = 2,
                    reason = MyReason(
                        reason = "Ngày vía Quan Thế Âm giáng sanh"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 21,
                    lunarMonth = 2,
                    reason = MyReason(
                        reason = "Ngày vía Phổ Hiền giáng sanh"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_3 = BodhisattvasDay(
            lunarMonth = 3,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 6,
                    lunarMonth = 3,
                    reason = MyReason(
                        reason = "Ngày vía Ca Diếp Tôn Giả"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 16,
                    lunarMonth = 3,
                    reason = MyReason(
                        reason = "Ngày Phật Mẫu Chuẩn Đề"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_4 = BodhisattvasDay(
            lunarMonth = 4,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 4,
                    lunarMonth = 4,
                    reason = MyReason(
                        reason = "Ngày vía Văn Thù Bồ Tát"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 8,
                    lunarMonth = 4,
                    reason = MyReason(
                        reason = "Ngày vía Phật Thích Ca Đản Sanh"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 20,
                    lunarMonth = 4,
                    reason = MyReason(
                        reason = "Ngày vía Bồ Tát Quảng Đức vị pháp thiêu thân"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 23,
                    lunarMonth = 4,
                    reason = MyReason(
                        reason = "Ngày vía Phổ Hiền Thành Đạo"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 28,
                    lunarMonth = 4,
                    reason = MyReason(
                        reason = "Ngày vía Dược Sư Đản Sanh"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_5 = BodhisattvasDay(
            lunarMonth = 5,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 13,
                    lunarMonth = 5,
                    reason = MyReason(
                        reason = "Ngày vía Già Lam Thánh Chúng"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_6 = BodhisattvasDay(
            lunarMonth = 6,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 3,
                    lunarMonth = 6,
                    reason = MyReason(
                        reason = "Ngày vía Hộ Pháp"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 19,
                    lunarMonth = 6,
                    reason = MyReason(
                        reason = "Ngày vía Quan Thế Âm Thành Đạo"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_7 = BodhisattvasDay(
            lunarMonth = 7,
            listBodhisattvaDay = listOf (
                BodhisattvaDay(
                    lunarDay = 13,
                    lunarMonth = 7,
                    reason = MyReason(
                        reason = "Ngày vía Đại Thế Chí"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 15,
                    lunarMonth = 7,
                    reason = MyReason(
                        reason = "Ngày Vu Lan Bồn (Đại Hiếu Mục Kiền Liên Bồ Tát)"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 30,
                    lunarMonth = 7,
                    reason = MyReason(
                        reason = "Ngày vía Địa Tạng Bồ Tát"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_8 = BodhisattvasDay(
            lunarMonth = 8,
            listBodhisattvaDay = listOf(
                BodhisattvaDay(
                    lunarDay = 6,
                    lunarMonth = 8,
                    reason = MyReason(
                        reason = "Ngày Huệ Viễn Tuệ Sư Sơ Tổ Tịnh Độ Tông"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 8,
                    lunarMonth = 8,
                    reason = MyReason(
                        reason = "Ngày vía Tôn Giả A Nan Đà"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_9 = BodhisattvasDay(
            lunarMonth = 9,
            listBodhisattvaDay = listOf(
                BodhisattvaDay(
                    lunarDay = 19,
                    lunarMonth = 9,
                    reason = MyReason(
                        reason = "Ngày vía Quan Thế Âm xuất gia"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 29,
                    lunarMonth = 9,
                    reason = MyReason(
                        reason = "Ngày vía Dược Sư thành đạo"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_10 = BodhisattvasDay(
            lunarMonth = 10,
            listBodhisattvaDay = listOf(
                BodhisattvaDay(
                    lunarDay = 5,
                    lunarMonth = 10,
                    reason = MyReason(
                        reason = "Ngày vía Đạt Ma Tổ Sư"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 8,
                    lunarMonth = 10,
                    reason = MyReason(
                        reason = "Ngày Phóng sanh"
                    )
                ),
                BodhisattvaDay(
                    lunarDay = 15,
                    lunarMonth = 10,
                    reason = MyReason(
                        reason = "Ngày lễ Hạ Nguyên"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_11 = BodhisattvasDay(
            lunarMonth = 11,
            listBodhisattvaDay = listOf(
                BodhisattvaDay(
                    lunarDay = 17,
                    lunarMonth = 11,
                    reason = MyReason(
                        reason = "Ngày vía Phật A Di Đà"
                    )
                ),
            ),
        )

        val LIST_BODHISSATTVAS_DAY_IN_MONTH_12 = BodhisattvasDay(
            lunarMonth = 12,
            listBodhisattvaDay = listOf(
                BodhisattvaDay(
                    lunarDay = 8,
                    lunarMonth = 12,
                    reason = MyReason(
                        reason = "Ngày vía Phật Thích Ca Thành Đạo"
                    )
                ),
            ),
        )

    }
}