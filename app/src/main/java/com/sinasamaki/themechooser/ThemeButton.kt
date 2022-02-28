package com.sinasamaki.themechooser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeButton(
    theme: CustomTheme,
    currentTheme: CustomTheme,
    text: String,
    onClick: (Offset) -> Unit,
) {
    val isSelected = theme == currentTheme
    var offset: Offset = remember { Offset(0f, 0f) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .onGloballyPositioned {
                    offset = Offset(
                        x = it.positionInWindow().x + it.size.width / 2,
                        y = it.positionInWindow().y + it.size.height / 2
                    )
                }
                .size(110.dp)
                .border(
                    4.dp,
                    color = if (isSelected) theme.primaryColor else Color.Transparent,
                    shape = CircleShape
                )
                .padding(8.dp)
                .background(color = theme.primaryColor, shape = CircleShape)
                .clip(CircleShape)
                .clickable {
                    onClick(offset)
                }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = theme.image),
                contentDescription = "themeImage",
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = text.uppercase(),
            modifier = Modifier
                .alpha(if (isSelected) 1f else .5f)
                .padding(2.dp),
            color = currentTheme.textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}