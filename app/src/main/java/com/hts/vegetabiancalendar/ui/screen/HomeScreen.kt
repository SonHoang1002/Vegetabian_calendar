package com.hts.vegetabiancalendar.ui.screen

import MyCalendarService
import MyLunarDate
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hts.vegetabiancalendar.util.ConvertUtil
import com.hts.vegetabiancalendar.view_model.MyViewModel
import java.time.LocalDateTime

var TAG = "HomeScreen"
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: MyViewModel,
    innerPadding: PaddingValues
) {
    var currentLocalDateTime: LocalDateTime = viewModel.currentDay
    val currentDayValue =
        ConvertUtil().convertToDayOfWeekValue(viewModel.currentDay.dayOfWeek.value)
    val localConfig = LocalConfiguration.current
    val screenWidthDp = localConfig.screenWidthDp
    Log.d(TAG, "HomeScreen: $screenWidthDp")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.background(Color.Red).weight(1f)
            ) {
                BuildSolarDayOfWeek(viewModel, currentLocalDateTime)
            }
//            Box(
//                modifier = Modifier
//                    .height(20.dp)
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth().background(Color.Blue).weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                BuildSolar(currentLocalDateTime)
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .width(10.dp)
                )
                BuildLunar(
                    currentLocalDateTime
                )
            }
            Box(
                modifier = Modifier
                    .height(20.dp).weight(3f)
            )
//            Box(
//                modifier = Modifier.fillMaxSize()
//            ) {
                BuildSampleDhammapadaItem(viewModel)
//            }
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSolarDayOfWeek(viewModel: MyViewModel, currentLocalDateTime: LocalDateTime) {
    val currentSolarDayOfWeek = ConvertUtil().convertToDayOfWeekValue(currentLocalDateTime.dayOfWeek.value)
    Text(
        currentSolarDayOfWeek.toString(),
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSolar(currentLocalDateTime: LocalDateTime) {
    val currentSolarDayOfMonth = currentLocalDateTime.dayOfMonth
    val currentSolarMonth = currentLocalDateTime.monthValue
    Text(
        "$currentSolarDayOfMonth/$currentSolarMonth",
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildLunar(localDateTime: LocalDateTime) {
    val currentLunarDate: MyLunarDate =
        MyCalendarService(localDateTime).myLunarDate
    Text(
        "${currentLunarDate.day}/${currentLunarDate.month}",
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold
    )
}
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSampleDhammapadaItem(viewModel: MyViewModel) {
    val sampleDhammapadaItem = viewModel.dhammapadaData
    val sampleString:List<String>? = sampleDhammapadaItem?.chapter[0]?.verses[0]?.text
    if(sampleString!=null){
        for(text in sampleString){
            Text(
                text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )
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