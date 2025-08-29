package com.hts.vegetabiancalendar.ui.screen

import MyCalendarService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hts.vegetabiancalendar.model.ModelVerseDhammapada
import com.hts.vegetabiancalendar.model.MyLunarDate
import com.hts.vegetabiancalendar.model.MyReason
import com.hts.vegetabiancalendar.util.ConvertUtil
import com.hts.vegetabiancalendar.util.getDescriptionsForText
import com.hts.vegetabiancalendar.util.isVegetabianDayWithSolarLocalDateTime
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
    val currentLocalDateTime: LocalDateTime = viewModel.currentDay
    val localConfig = LocalConfiguration.current
    val screenWidthDp = localConfig.screenWidthDp
    Log.d(TAG, "HomeScreen: $screenWidthDp")
    val lunarStatusDayData = currentLocalDateTime
        .isVegetabianDayWithSolarLocalDateTime()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                BuildSolarDayOfWeek(viewModel, currentLocalDateTime)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                BuildSolar(currentLocalDateTime)
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .width(10.dp)
                        .background(Color.Gray)
                )
                BuildLunar(
                    currentLocalDateTime,
                    lunarStatusDayData
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
//                    .background(Color.Yellow)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    lunarStatusDayData.second.reason,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f),
                contentAlignment = Alignment.Center
            ) {
                BuildSampleDhammapadaItem(viewModel)
            }
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSolarDayOfWeek(viewModel: MyViewModel, currentLocalDateTime: LocalDateTime) {
    val currentSolarDayOfWeek =
        ConvertUtil().convertToDayOfWeekValue(currentLocalDateTime.dayOfWeek.value)
    Text(
        currentSolarDayOfWeek,
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSolar(currentLocalDateTime: LocalDateTime) {
    val currentSolarDayOfMonth = currentLocalDateTime.dayOfMonth
    val currentSolarMonth = currentLocalDateTime.monthValue
    Column {
        Text(
            "$currentSolarDayOfMonth/$currentSolarMonth",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Dương lịch",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildLunar(localDateTime: LocalDateTime, lunarStatusDayData: Pair<Boolean, MyReason>) {
    val currentLunarDate: MyLunarDate =
        MyCalendarService(localDateTime).myLunarDate
    Column {
        Text(
            "${currentLunarDate.day}/${currentLunarDate.month}",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = if (lunarStatusDayData.first) Color.Red else Color.Unspecified
        )
        Text(
            "Âm lịch",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

        )
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun BuildSampleDhammapadaItem(viewModel: MyViewModel) {
    val pagerState =
        remember { mutableIntStateOf(viewModel.currentVerseIndex) }
    val modelVerseDhammapada: ModelVerseDhammapada? =
        viewModel.getCurrentVerseValue(pagerState.intValue)
    val sampleString: List<String>? = modelVerseDhammapada?.getTextAndOrder()
    val currentChapter = viewModel.getChapterByVerseIndex(pagerState.intValue)
    if (sampleString?.isNotEmpty() == true) {
        Column(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        pagerState.intValue = pagerState.intValue - 1
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = com.hts.vegetabiancalendar.R.drawable.arrow_left),
                        contentDescription = "Back",
                        modifier = Modifier
                            .height(30.dp)
                            .width(80.dp)
                    )
                }

                Button(
                    onClick = {
                        pagerState.intValue = pagerState.intValue + 1
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = com.hts.vegetabiancalendar.R.drawable.arrow_right),
                        contentDescription = "Next",
                        modifier = Modifier
                            .height(30.dp)
                            .width(80.dp)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                // Hiển thị verse text
                items(sampleString) { text ->
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )
                }

                // Hiển thị descriptions nếu có ký hiệu [số]
                val descriptionsToShow =
                    getDescriptionsForText(sampleString, currentChapter?.descriptions)
                if (descriptionsToShow.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 1.dp,
                            color = Color.Gray
                        )
                    }

                    items(descriptionsToShow) { description ->
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            style = TextStyle(lineHeight = 24.sp)
                        )
                    }
                }
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