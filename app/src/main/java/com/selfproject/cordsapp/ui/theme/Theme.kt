package com.selfproject.cordsapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


/*
* surface is for bottom card view and top bar as well as background of search bar
*
* onSurfaceVariant for most of the thing on surface
*
* onSurface for selected things on surface distinguishing from surface variant
*
* */

private val DarkColorScheme = darkColorScheme(
    primary = lightBlue,
    secondary = white,
    tertiary = Pink80,

    surface = lightBlack,

    onSurface = darkBlue,
    onSurfaceVariant = white,

    primaryContainer = darkBlack,
    onPrimaryContainer = white,
    onPrimary = white

)

private val LightColorScheme = lightColorScheme(
    primary = darkBlue,
    secondary = PurpleGrey80,
    tertiary = Pink80,

    surface = veryLightBlue,
    onSurfaceVariant = grayShade,


    primaryContainer = white,
    onPrimaryContainer = darkBlue,
    onPrimary = white

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CordsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}