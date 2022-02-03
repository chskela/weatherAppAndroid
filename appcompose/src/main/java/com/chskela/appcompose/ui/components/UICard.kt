package com.chskela.appcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.appcompose.R
import com.chskela.appcompose.data.UIData
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@Composable
fun UICard(title: String = "hourly", list: List<UIData>) {
    val scrollState = rememberLazyListState()
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(3.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        UIText(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(state = scrollState) {
            items(list) { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 16.dp)
                ) {
                    // time
                    UIText(text = item.dt)
                    // weather icon
                    UIIcon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.description,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 16.dp)
                            .size(36.dp),
                    )
                    // temperature
                    UIText(text = "${item.temp}\u00B0")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    WeatherAppAndroidTheme {
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
                uiData.copy(temp = "12")
            )
        )
    }
}