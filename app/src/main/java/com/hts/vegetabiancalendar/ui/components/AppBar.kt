package com.hts.vegetabiancalendar.ui.components

import android.graphics.Color
import android.os.Build
import android.provider.CalendarContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Colors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hts.vegetabiancalendar.MainActivity
import com.hts.vegetabiancalendar.R
import com.hts.vegetabiancalendar.service.MyNotificationService
import com.hts.vegetabiancalendar.ui.theme.Black
import com.hts.vegetabiancalendar.view_model.MyViewModel


@Composable
fun BuildAppBar(viewModel: MyViewModel, mainActivity : MainActivity) {
    val height = 51
    var context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
//            .padding(vertical = 8.dp)
//            .background(color = Color.Red)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.setting_icon),
            contentDescription = "Setting Icon",
            modifier = Modifier
                .height(24.dp)
                .width(24.dp).clickable{
                    MyNotificationService().showHeadUpNotification(context = context)
                },
            tint = Black
        )

        // Title chiếm toàn bộ khoảng giữa
        BuildTitle(
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search Icon",
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .clickable {
                    Log.d("MyNotificationService","MyNotificationService search call")
                    MyNotificationService().showStatusNotification(context = context, mainActivity = mainActivity)
             },
            tint = Black
        )
    }
}

@Composable
fun BuildTitle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
//            modifier = Modifier.background(Color.Blue),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Black
//            fontFamily = FontFamily(Font(R.font.inknut_antiqua_medium))
        )
    }
}


