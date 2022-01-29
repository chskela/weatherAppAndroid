package com.chskela.appcompose.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.appcompose.R
import com.chskela.appcompose.ui.theme.Gray
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme
import com.chskela.appcompose.ui.theme.White

@Composable
fun SplashScreen() {
    Scaffold {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier

                    .padding(top = 16.dp),
                    text = stringResource(id = R.string.hows_today_weather),
                    fontSize = 34.sp,
                    color = Gray

                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_girl),
                    contentDescription = "splash screen",
                    contentScale = ContentScale.Crop
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Surface(shape = RectangleShape) {
                        Text(
                            text = stringResource(id = R.string.today_weather),
                            fontSize = 24.sp,
                            color = White,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(50))
                                .background(color = Gray)
                                .padding(16.dp, 8.dp)

                        )
                    }

                }

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppAndroidTheme {
        SplashScreen()
    }
}