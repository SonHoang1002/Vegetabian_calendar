package com.hts.vegetabiancalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hts.vegetabiancalendar.factory.MyViewModelFactory
import com.hts.vegetabiancalendar.route.MyRoute
import com.hts.vegetabiancalendar.ui.screen.HomeScreen
import com.hts.vegetabiancalendar.ui.screen.ProfileScreen
import com.hts.vegetabiancalendar.ui.screen.SettingScreen
import com.hts.vegetabiancalendar.ui.theme.VegetabianCalendarTheme
import com.hts.vegetabiancalendar.view_model.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VegetabianCalendarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val viewModel: MyViewModel = viewModel(
                        factory = MyViewModelFactory(context)
                    )
                    ScreenMain(innerPadding = innerPadding, viewModel = viewModel)
                }
            }
        }
    }
}
@Composable
fun ScreenMain(innerPadding: PaddingValues, viewModel: MyViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MyRoute.Home.route) {
        // Home
        composable(MyRoute.Home.route) {
            // pass the navController
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        // Profile
        composable(MyRoute.Profile.route) {
            ProfileScreen(viewModel = viewModel)
        }
        // Settings
        // "/{id}" - its the argument passed down from homeScreen
        composable(MyRoute.Settings.route + "/{id}") { navBackStack ->
            // Extracting the argument
            val counter = navBackStack.arguments?.getString("id")
            SettingScreen(counter = counter, viewModel= viewModel)
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