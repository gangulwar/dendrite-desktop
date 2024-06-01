package utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

class FontLoader {
    fun loadInterFontFamily(): FontFamily {
        return FontFamily(
            Font("font/inter_bold.ttf", FontWeight.Bold),
            Font("font/inter_medium.ttf", FontWeight.Medium),
            Font("font/inter_regular.ttf", FontWeight.Normal),
            Font("font/inter_semibold.ttf", FontWeight.SemiBold),
            Font("font/inter_light.ttf", FontWeight.Light),
            Font("font/inter_extralight.ttf", FontWeight.ExtraLight)
        )
    }

    fun loadEloquiaFontFamily(): FontFamily {
        return FontFamily(
            Font("font/eloquia_extra_bold.otf", FontWeight.ExtraBold),
            Font("font/eloquia_extra_light.otf", FontWeight.Light)
        )
    }
}