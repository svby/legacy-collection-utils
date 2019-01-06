package net.notiocide.legacyutil

import java.util.*

fun propertiesOf(): Properties =
    Properties()

fun propertiesOf(vararg pairs: Pair<String, String>): Properties =
    Properties().apply { for ((k, v) in pairs) this.setProperty(k, v) }

fun Map<String, String>.toProperties() =
    Properties().apply { for ((k, v) in this@toProperties.entries) this.setProperty(k, v) }
