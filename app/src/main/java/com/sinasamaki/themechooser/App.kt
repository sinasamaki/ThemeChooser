package com.sinasamaki.themechooser

import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun App() {
    var theme by remember { mutableStateOf(lightTheme) }
    var animationOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    AnimatedContent(
        targetState = theme,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        transitionSpec = {
            fadeIn(
                initialAlpha = 0f,
                animationSpec = tween(100)
            ) with fadeOut(
                targetAlpha = .9f,
                animationSpec = tween(800)
            ) + scaleOut(
                targetScale = .95f,
                animationSpec = tween(800)
            )
        }
    ) { currentTheme ->
        val revealSize = remember { Animatable(1f) }
        LaunchedEffect(key1 = "reveal", block = {
            if (animationOffset.x > 0f) {
                revealSize.snapTo(0f)
                revealSize.animateTo(1f, animationSpec = tween(800))
            } else {
                revealSize.snapTo(1f)
            }
        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CirclePath(revealSize.value, animationOffset))
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = currentTheme.background
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        Image(
                            painter = painterResource(id = currentTheme.image),
                            contentDescription = "headerImage",
                            contentScale = ContentScale.Crop,
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            currentTheme.background.copy(alpha = .2f),
                                            currentTheme.background
                                        )
                                    )
                                )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        ThemeButton(
                            theme = lightTheme,
                            currentTheme = currentTheme,
                            text = "Light",
                        ) {
                            animationOffset = it
                            theme = lightTheme
                        }

                        ThemeButton(
                            theme = darkTheme,
                            currentTheme = currentTheme,
                            text = "Dark",
                        ) {
                            animationOffset = it
                            theme = darkTheme
                        }

                        ThemeButton(
                            theme = pinkTheme,
                            currentTheme = currentTheme,
                            text = "Pink",
                        ) {
                            animationOffset = it
                            theme = pinkTheme
                        }
                    }
                }
            }
        }
    }
}