package com.example.contacts_list.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),        // Softer purple
    onPrimary = Color(0xFF381E72),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),

    background = Color(0xFF1C1B1F),     // Near-black background
    onBackground = Color(0xFFE6E1E5),   // Light gray text

    surface = Color(0xFF1C1B1F),        // Dark surface
    onSurface = Color(0xFFE6E1E5),

    surfaceVariant = Color(0xFF49454F), // Muted dark gray-purple
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410)
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),       // Deep purple
    onPrimary = Color.White,
    secondary = Color(0xFF625B71),     // Muted violet-gray
    onSecondary = Color.White,
    tertiary = Color(0xFF7D5260),      // Muted red-violet
    onTertiary = Color.White,

    background = Color(0xFFFFFBFE),    // Near-white background
    onBackground = Color(0xFF1C1B1F),  // Almost black text

    surface = Color(0xFFFFFBFE),       // White surfaces
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = Color(0xFFE7E0EC), // Soft gray with purple tint
    onSurfaceVariant = Color(0xFF49454F),

    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),

    error = Color(0xFFB3261E),
    onError = Color.White
)


@Composable
fun Contacts_listTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}