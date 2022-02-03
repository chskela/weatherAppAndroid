package com.chskela.appcompose.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chskela.appcompose.R
import com.chskela.appcompose.data.UIData
import com.chskela.appcompose.screens.main.TopAppBar
import com.chskela.appcompose.ui.components.UICard
import com.chskela.appcompose.ui.components.UIIcon
import com.chskela.appcompose.ui.components.UIText
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val scrollState = rememberScrollState()
    WeatherAppAndroidTheme {
        Scaffold(
            topBar = { TopAppBar() },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Surface(
                modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                shadowElevation = 3.dp,
            ) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        thickness = 3.dp,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 32.dp)
                            .width(120.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 16.dp)
                            .align(Alignment.Start)
                    ) {
                        UIIcon(
                            imageVector = Icons.Outlined.Room,
                            contentDescription = "location",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp)
                        ) {
                            UIText(
                                text = "Moscow",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            UIText(
                                text = "18:00",
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }

                    }
                    UIText(
                        text = "15°",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    UIIcon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic__mist_50),
                        contentDescription = "",
                        modifier = Modifier
                            .size(72.dp)
                            .padding(bottom = 16.dp)
                    )
                    UIText(
                        text = "rain",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    val uiData = UIData("10", "20:00", R.drawable.ic__clear_sky_01, "sky")
                    UICard(
                        title = stringResource(id = R.string.hourly),
                        list = listOf(
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData.copy(temp = "12")
                        )
                    )

                    Divider(thickness = 16.dp, color = MaterialTheme.colorScheme.onPrimary)

                    UICard(
                        title = stringResource(id = R.string.daily),
                        list = listOf(
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData,
                            uiData.copy(temp = "12")
                        )
                    )
                }


            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}