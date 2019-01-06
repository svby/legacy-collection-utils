package net.notiocide.legacyutil

import java.util.*

/** Returns an empty new [Properties]. */
fun propertiesOf(): Properties =
    Properties()

/**
 * Returns a new [Properties] with the specified contents, given as a list of [Pair]s where the first component is the
 * key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting properties will contain the value from the last of those pairs.
 */
fun propertiesOf(vararg pairs: Pair<String, String>): Properties =
    Properties().apply { for ((k, v) in pairs) this.setProperty(k, v) }

/** Returns a new [Properties] containing all key-value pairs from the original map. */
fun Map<String, String>.toProperties() =
    Properties().apply { for ((k, v) in this@toProperties.entries) this.setProperty(k, v) }
