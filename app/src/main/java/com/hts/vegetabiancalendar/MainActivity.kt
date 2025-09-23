package com.hts.vegetabiancalendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hts.vegetabiancalendar.factory.MyViewModelFactory
import com.hts.vegetabiancalendar.route.MyRoute
import com.hts.vegetabiancalendar.service.MyNotificationService
import com.hts.vegetabiancalendar.ui.screen.HomeScreen
import com.hts.vegetabiancalendar.ui.screen.ProfileScreen
import com.hts.vegetabiancalendar.ui.screen.SettingScreen
import com.hts.vegetabiancalendar.ui.theme.VegetabianCalendarTheme
import com.hts.vegetabiancalendar.view_model.MyViewModel
class MainActivity : ComponentActivity() {
    @SuppressLint("ScheduleExactAlarm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VegetabianCalendarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val activity =this
                    LaunchedEffect(1) {
                         val service =   MyNotificationService()
                        service .requestPermission(activity)
                        service.scheduleTimerToShowNotification(context )

                    }
                    val viewModel: MyViewModel = viewModel(
                        factory = MyViewModelFactory(context)
                    )
                    ScreenMain(innerPadding = innerPadding, viewModel = viewModel, mainActivity= this)
                }
            }
        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain(innerPadding: PaddingValues, viewModel: MyViewModel, mainActivity: MainActivity = MainActivity()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyRoute.Home.route) {
        // Home
        composable(MyRoute.Home.route) {
            // pass the navController
            HomeScreen(navController = navController, viewModel = viewModel,innerPadding = innerPadding, mainActivity = mainActivity)
        }
        // Profile
        composable(MyRoute.Profile.route) {
            ProfileScreen(viewModel = viewModel, innerPadding = innerPadding)
        }
        // Settings
        // "/{id}" - its the argument passed down from homeScreen
        composable(MyRoute.Settings.route + "/{id}") { navBackStack ->
            // Extracting the argument
            val counter = navBackStack.arguments?.getString("id")
            SettingScreen(counter = counter, viewModel= viewModel,innerPadding = innerPadding)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    VegetabianCalendarTheme {
//        Greeting("Android")
//    }
//}