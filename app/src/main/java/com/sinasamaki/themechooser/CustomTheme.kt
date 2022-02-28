package com.sinasamaki.themechooser

import androidx.compose.ui.graphics.Color

data class CustomTheme(
    val primaryColor: Color,
    val background: Color,
    val textColor: Color,
    val image: Int,
)

val darkTheme = CustomTheme(
    primaryColor = Color(0xFFE9B518),
    background = Color(0xFF111111),
    textColor = Color(0xffFFFFFF),
    image = R.drawable.dark,
)

val lightTheme = CustomTheme(
    primaryColor = Color(0xFF2CB6DA),
    background = Color(0xFFF1F1F1),
    textColor = Color(0xff000000),
    image = R.drawable.light,
)

val pinkTheme = CustomTheme(
    primaryColor = Color(0xFFF01EE5),
    background = Color(0xFF110910),
    textColor = Color(0xFFEE8CE1),
    image = R.drawable.pink,
)