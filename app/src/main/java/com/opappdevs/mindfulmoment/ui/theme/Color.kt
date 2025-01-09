package com.opappdevs.mindfulmoment.ui.theme

import androidx.compose.ui.graphics.Color

object MindfulColorTheme {
//    val md_theme_light_primary = Color(0xFF8B5000) // Hauptfarbe für wichtige UI-Elemente
//    val md_theme_light_onPrimary = Color(0xFFFFFFFF) // Textfarbe auf primären Elementen
//    val md_theme_light_primaryContainer = Color(0xFFFFDCC2) // Hintergrund für primäre Container
//    val md_theme_light_onPrimaryContainer = Color(0xFF2D1600) // Text auf primären Containern
//    val md_theme_light_secondary = Color(0xFF006E1C) // Sekundäre Akzentfarbe
//    val md_theme_light_onSecondary = Color(0xFFFFFFFF) // Text auf sekundären Elementen
//    val md_theme_light_secondaryContainer = Color(0xFF95F990) // Hintergrund für sekundäre Container
//    val md_theme_light_onSecondaryContainer = Color(0xFF002204) // Text auf sekundären Containern
//    val md_theme_light_tertiary = Color(0xFF006E08) // Tertiäre Akzentfarbe
//    val md_theme_light_onTertiary = Color(0xFFFFFFFF) // Text auf tertiären Elementen
//    val md_theme_light_tertiaryContainer = Color(0xFF95F984) // Hintergrund für tertiäre Container
//    val md_theme_light_onTertiaryContainer = Color(0xFF002201) // Text auf tertiären Containern
//    val md_theme_light_error = Color(0xFFBA1A1A) // Farbe für Fehlermeldungen
//    val md_theme_light_errorContainer = Color(0xFFFFDAD6) // Hintergrund für Fehlermeldungen
//    val md_theme_light_onError = Color(0xFFFFFFFF) // Text auf Fehlerelementen
//    val md_theme_light_onErrorContainer = Color(0xFF410002) // Text auf Fehler-Containern
//    val md_theme_light_background = Color(0xFFFFFBFF) // Hintergrundfarbe der App
//    val md_theme_light_onBackground = Color(0xFF201A17) // Text auf dem Hintergrund
//    val md_theme_light_surface = Color(0xFFFFFBFF) // Oberflächenfarbe für Karten und Dialoge
//    val md_theme_light_onSurface = Color(0xFF201A17) // Text auf Oberflächen
//    val md_theme_light_surfaceVariant = Color(0xFFF3DFD2) // Variante der Oberflächenfarbe
//    val md_theme_light_onSurfaceVariant = Color(0xFF52443B) // Text auf Oberflächenvarianten
//    val md_theme_light_outline = Color(0xFF85746B) // Umrissfarbe für Elemente
//    val md_theme_light_inverseOnSurface = Color(0xFFFBEEE8) // Invertierter Text auf Oberflächen
//    val md_theme_light_inverseSurface = Color(0xFF362F2B) // Invertierte Oberflächenfarbe
//    val md_theme_light_inversePrimary = Color(0xFFFFB77D) // Invertierte Primärfarbe
//
//    val md_theme_dark_primary = Color(0xFFFFB77D) // Primärfarbe im Dunkelmodus
//    val md_theme_dark_onPrimary = Color(0xFF4B2800) // Text auf Primärelementen im Dunkelmodus
//    val md_theme_dark_primaryContainer = Color(0xFF6B3B00) // Primäre Container im Dunkelmodus
//    val md_theme_dark_onPrimaryContainer = Color(0xFFFFDCC2) // Text auf primären Containern im Dunkelmodus
//    val md_theme_dark_secondary = Color(0xFF7ADC77) // Sekundärfarbe im Dunkelmodus
//    val md_theme_dark_onSecondary = Color(0xFF00390A) // Text auf Sekundärelementen im Dunkelmodus
//    val md_theme_dark_secondaryContainer = Color(0xFF005313) // Sekundäre Container im Dunkelmodus
//    val md_theme_dark_onSecondaryContainer = Color(0xFF95F990) // Text auf sekundären Containern im Dunkelmodus
//    val md_theme_dark_tertiary = Color(0xFF7ADC6B) // Tertiärfarbe im Dunkelmodus
//    val md_theme_dark_onTertiary = Color(0xFF003A01) // Text auf Tertiärelementen im Dunkelmodus
//    val md_theme_dark_tertiaryContainer = Color(0xFF005303) // Tertiäre Container im Dunkelmodus
//    val md_theme_dark_onTertiaryContainer = Color(0xFF95F984) // Text auf tertiären Containern im Dunkelmodus
//    val md_theme_dark_error = Color(0xFFFFB4AB) // Fehlerfarbe im Dunkelmodus
//    val md_theme_dark_errorContainer = Color(0xFF93000A) // Fehler-Container im Dunkelmodus
//    val md_theme_dark_onError = Color(0xFF690005) // Text auf Fehlerelementen im Dunkelmodus
//    val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6) // Text auf Fehler-Containern im Dunkelmodus
//    val md_theme_dark_background = Color(0xFF201A17) // Hintergrundfarbe im Dunkelmodus
//    val md_theme_dark_onBackground = Color(0xFFECE0DA) // Text auf dem Hintergrund im Dunkelmodus
//    val md_theme_dark_surface = Color(0xFF201A17) // Oberflächenfarbe im Dunkelmodus
//    val md_theme_dark_onSurface = Color(0xFFECE0DA) // Text auf Oberflächen im Dunkelmodus
//    val md_theme_dark_surfaceVariant = Color(0xFF52443B) // Oberflächenvariante im Dunkelmodus
//    val md_theme_dark_onSurfaceVariant = Color(0xFFD7C2B8) // Text auf Oberflächenvarianten im Dunkelmodus
//    val md_theme_dark_outline = Color(0xFF9F8D84) // Umrissfarbe im Dunkelmodus
//    val md_theme_dark_inverseOnSurface = Color(0xFF201A17) // Invertierter Text auf Oberflächen im Dunkelmodus
//    val md_theme_dark_inverseSurface = Color(0xFFECE0DA) // Invertierte Oberflächenfarbe im Dunkelmodus
//    val md_theme_dark_inversePrimary = Color(0xFF8B5000) // Invertierte Primärfarbe im Dunkelmodus

    val light_primary = Color(0xFFFF9F00) // dark_orange
    val light_onPrimary = Color(0xFFFFFFFF) // Contrasting white for text/icons on primary
    val light_primaryContainer = Color(0xFFFFCE5C) // light_orange
    val light_onPrimaryContainer = Color(0xFF4A2800) // Contrast dark brown

    val light_secondary = Color(0xFF50C15A) // dark_green
    val light_onSecondary = Color(0xFFFFFFFF) // Contrasting white for text/icons on secondary
    val light_secondaryContainer = Color(0xFF65DF72) // medium_green
    val light_onSecondaryContainer = Color(0xFF003D1A) // Contrast dark green

    val light_tertiary = Color(0xFFFF5B0A) // heart_red
    val light_onTertiary = Color(0xFFFFFFFF) // Contrasting white for text/icons on tertiary
    val light_tertiaryContainer = Color(0xFFFE926D) // light_red
    val light_onTertiaryContainer = Color(0xFF3B0A00) // Contrast dark red

    val light_background = Color(0xFFFFF8EF) // warm_white
    val light_onBackground = Color(0xFF1B1B1B) // Neutral dark for text/icons on background

    val light_surface = Color(0xFFFFF8EF) // warm_white (matching background)
    val light_onSurface = Color(0xFF1B1B1B) // Neutral dark for text/icons on surface
    val light_surfaceVariant = Color(0xFF90EC95) // light_green
    val light_onSurfaceVariant = Color(0xFF003A19) // Contrast dark green

    val light_outline = Color(0xFF807264) // Muted outline, could be a grayish-brown tone
    val light_error = Color(0xFFBA1A1A) // Default Material3 error color
    val light_onError = Color(0xFFFFFFFF) // Contrasting white for text/icons on error
    val light_errorContainer = Color(0xFFFFDAD4) // Default Material3 error container
    val light_onErrorContainer = Color(0xFF410002) // Default Material3 on error container

    val light_inverseSurface = Color(0xFF303030) // Dark mode for inverse surfaces
    val light_inverseOnSurface = Color(0xFFECECEC) // Light contrast text/icons on inverse
    val light_inversePrimary = Color(0xFFFFCE5C) // light_orange

    val light_surfaceTint = Color(0xFFFF9F00) // Used for surface tinting
    val light_outlineVariant = Color(0xFFD9C2B2) // Lighter outline
    val light_scrim = Color(0xFF000000)

    /**
     * Der Zweck der seed Farbe ist es, als Ausgangspunkt für die Generierung eines vollständigen Farbschemas zu dienen. Hier sind einige Erläuterungen dazu:
     *
     *     Farbschema-Generierung: Die seed Farbe wird normalerweise verwendet, wenn man ein Farbschema mit einem Tool wie dem Material Theme Builder oder der Material Color Utility generiert. Diese Tools nehmen eine einzelne Farbe als Eingabe und erzeugen daraus ein vollständiges, harmonisches Farbschema.
     *     Konsistenz: Die seed Farbe dient als Referenzpunkt, um sicherzustellen, dass alle generierten Farben gut miteinander harmonieren und auf einem gemeinsamen Ausgangspunkt basieren.
     *     Anpassbarkeit: Wenn Sie das Farbschema Ihrer App in Zukunft anpassen möchten, können Sie einfach die seed Farbe ändern und das gesamte Farbschema neu generieren, anstatt jede einzelne Farbe manuell anzupassen.
     *     Dokumentation: Die seed Farbe kann als Dokumentation dienen, um den Ursprung des Farbschemas festzuhalten.
     */
    //val seed = Color(0xFFFF9800) // Ausgangsfarbe für die Farbpalette
}