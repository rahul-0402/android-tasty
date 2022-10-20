package com.rahulghag.tasty.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val RaisinBlack = Color(0xFF212125)
val DarkCharcoal = Color(0xFF2D2D32)
val WildSand = Color(0xFFF4F4F4)

val Colors.bottomBarBackgroundColor: Color
    @Composable
    get() = background

val Colors.bottomBarSelectedContentColor: Color
    @Composable
    get() = if (isLight) primary else onBackground

val Colors.bottomBarUnselectedContentColor: Color
    @Composable
    get() = Color.Gray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else surface

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) primary else onSurface

val Colors.searchBarBackgroundColor: Color
    @Composable
    get() = if (isLight) WildSand else DarkCharcoal

val Colors.preparationSectionBackgroundColor: Color
    @Composable
    get() = if (isLight) WildSand else DarkCharcoal

val Colors.preparationItemBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else RaisinBlack