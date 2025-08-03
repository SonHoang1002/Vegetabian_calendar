package com.hts.vegetabiancalendar.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hts.vegetabiancalendar.service.VegetabianDayService
import com.hts.vegetabiancalendar.ui.components.CDhammapadaList
import com.hts.vegetabiancalendar.util.ConvertUtil
import com.hts.vegetabiancalendar.view_model.MyViewModel
import java.time.LocalDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MyViewModel) {
    val calendarService = VegetabianDayService()
    val currentDayValue =
        ConvertUtil().convertToDayOfWeekValue(viewModel.currentDay.dayOfWeek.value)
    val currentSolarDayOfMonth = viewModel.currentDay.dayOfMonth
    val currentSolarMonth = viewModel.currentDay.monthValue
    val currentLunarDayOfMonth =
        calendarService.getLunarDayFromSunDayInMonth(ConvertUtil().convertLocalTimeToDate(viewModel.currentDay))
    val currentLunarMonth = 0
    val localConfig = LocalConfiguration.current
    val screenWidthDp = localConfig.screenWidthDp
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                currentDayValue,
                fontSize = 65.sp,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(screenWidthDp.dp)
                    .background(Color.Red)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    "$currentSolarDayOfMonth/$currentSolarMonth",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .width(10.dp)
                        .background(Color.Blue)
                )
                Text(
                    "$currentLunarDayOfMonth/${currentLunarMonth}",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//@Composable
//fun HomeScreen(navController: NavHostController) {
//    // Basic counter to display on screen
//    var counter by remember {
//        mutableIntStateOf(0)
//    }
//    // Box to center Items
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White), contentAlignment = Alignment.Center
//    ) {
//        Column {
//            // Text to show counter on Screen
//            Text(text = "Home, Counter is $counter", color = Color.Black)
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Button increases the counter
//            Button(onClick = { counter++ }) {
//                Text(text = "Increment Counter", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Navigate to Profile Screen
//            Button(onClick = {
//                navController.navigate(MyRoute.Profile.route)
//            }) {
//                Text(text = "Navigate to Profile", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // Navigate to Settings Screen
//            Button(onClick = {
//                navController.navigate(MyRoute.Settings.route + "/$counter")
//            }) {
//                Text(text = "Navigate to Settings", color = Color.White)
//            }
//        }
//    }
//}