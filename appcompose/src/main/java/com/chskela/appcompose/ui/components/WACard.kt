package com.chskela.appcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.appcompose.data.UIData
import com.chskela.appcompose.ui.theme.WeatherAppAndroidTheme

@Composable
fun WACard(title: String = "hourly") {
    val scrollState = rememberLazyListState()
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(16.dp)

            .fillMaxWidth()


    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        LazyRow(state = scrollState) {
            items(listOf<UIData>()) { item ->
                Column() {
                    Text(text = item.dt)
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.description
                    )
                    Text(text = item.temp)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WACardPreview() {
    WeatherAppAndroidTheme {
        WACard()
    }
}