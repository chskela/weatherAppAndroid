package com.chskela.appcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.appcompose.R
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@Composable
fun SplashScreen() {
    WeatherAppAndroidTheme {
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
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primary
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
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(50)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.today_weather),
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
    SplashScreen()
}