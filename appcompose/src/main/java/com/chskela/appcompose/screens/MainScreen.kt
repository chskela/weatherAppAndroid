package com.chskela.appcompose.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.appcompose.R
import com.chskela.appcompose.data.UIData
import com.chskela.appcompose.ui.components.UICard
import com.chskela.appcompose.ui.components.UIIcon
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@Composable
fun MainScreen() {
    val scrollState = rememberScrollState()
    WeatherAppAndroidTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.sky_monitor),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "menu",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "person",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
            )
        }) {


            Surface(
//                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                color = Color.Blue

            ) {
                Column(
                    Modifier
                        .verticalScroll(scrollState)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        thickness = 3.dp,
                        modifier = Modifier
                            .width(120.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    UIIcon(
                        imageVector = Icons.Outlined.Room,
                        contentDescription = "location",
                        modifier = Modifier
                            .padding(top = 32.dp, start = 32.dp)
                            .size(48.dp)
                            .align(Alignment.Start)
                    )/**/
                    Text(text = "dfgdfijodfhijongidfigdfiugnd")

                    val uiData = UIData("10", "20:00", R.drawable.ic__clear_sky_01, "sky")
                    UICard(
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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}