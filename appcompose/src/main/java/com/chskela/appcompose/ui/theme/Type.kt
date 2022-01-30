package com.chskela.appcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chskela.appcompose.R

// Set of Material typography styles to start with
//val Typography = Typography(
//    body1 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
//)

private val Neris = FontFamily(
    Font(R.font.neris_thin, FontWeight.W200),
    Font(R.font.neris_light,  FontWeight.W300),
    Font(R.font.neris_semibold, FontWeight.W900),
)

val Typography = Typography(
    h4 = TextStyle(
        fontFamily = Neris,
        fontWeight = FontWeight.W300,
        fontSize = 34.sp
    ),
//    h5 = TextStyle(
//        fontFamily = Neris,
//        fontWeight = FontWeight.W300,
//        fontSize = 24.sp
//    ),
//    h6 = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.W600,
//        fontSize = 20.sp
//    ),
//    subtitle1 = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.W600,
//        fontSize = 16.sp
//    ),
//    subtitle2 = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    body1 = TextStyle(
//        fontFamily = Domine,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    ),
//    body2 = TextStyle(
//        fontFamily = Montserrat,
//        fontSize = 14.sp
//    ),
    button = TextStyle(
        fontFamily = Neris,
        fontWeight = FontWeight.W300,
        fontSize = 24.sp
    ),
//    caption = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    ),
//    overline = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.W500,
//        fontSize = 12.sp
//    )
)