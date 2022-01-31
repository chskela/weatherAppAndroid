package com.chskela.appcompose.screens


import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.appcompose.R
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@Composable
fun MainScreen() {
    WeatherAppAndroidTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.sky_monitor),
                        color= MaterialTheme.colorScheme.onPrimary,
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

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}