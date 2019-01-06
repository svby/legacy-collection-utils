package net.notiocide.legacyutil

import java.util.*

/** Returns an empty new [Hashtable]. */
fun <K : Any, V : Any> hashtableOf(): Hashtable<K, V> =
    Hashtable()

/**
 * Returns a new [Hashtable] with the specified contents, given as a list of [Pair]s where the first component is the
 * key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 */
fun <K : Any, V : Any> hashtableOf(vararg pairs: Pair<K, V>): Hashtable<K, V> =
    Hashtable(mapOf(*pairs))

/** Returns a new [Hashtable] containing all key-value pairs from the original map. */
fun <K : Any, V : Any> Map<K, V>.toHashtable() =
    Hashtable(this)
