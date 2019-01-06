package net.notiocide.legacyutil

import java.util.*

private class MapDictionary<K : Any, V : Any>(private val map: Map<K, V>) : Dictionary<K, V>() {

    override fun put(key: K, value: V) = throw UnsupportedOperationException()
    override fun remove(key: Any?) = throw UnsupportedOperationException()

    // NPE feels wrong (IllegalArgumentException) but it is specified by Dictionary's contract.
    override fun get(key: Any?) = map[key ?: throw NullPointerException()]

    override fun size() = map.size
    override fun isEmpty() = size() == 0

    override fun keys() = map.keys.asEnumeration()
    override fun elements() = map.values.asEnumeration()

}

private val emptyDictionary = MapDictionary(emptyMap())

/** Returns an empty read-only [Dictionary]. */
fun <K : Any, V : Any> dictionaryOf(): Dictionary<K, V> =
    @Suppress("UNCHECKED_CAST") (emptyDictionary as Dictionary<K, V>)

/**
 * Returns a new read-only [Dictionary] with the specified contents, given as a list of [Pair]s where the first
 * component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting dictionary will contain the value from the last of those
 * pairs.
 */
fun <K : Any, V : Any> dictionaryOf(vararg pairs: Pair<K, V>): Dictionary<K, V> =
    MapDictionary(mapOf(*pairs))

/** Returns an empty new [Dictionary]. */
fun <K : Any, V : Any> mutableDictionaryOf(): Dictionary<K, V> =
    hashtableOf()

/**
 * Returns a new [Dictionary] with the specified contents, given as a list of [Pair]s where the first component is the
 * key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting dictionary will contain the value from the last of those pairs.
 */
fun <K : Any, V : Any> mutableDictionaryOf(vararg pairs: Pair<K, V>): Dictionary<K, V> =
    hashtableOf(*pairs)

/**
 * Returns a new read-only [Dictionary] containing all key-value pairs from the original map.
 */
fun <K : Any, V : Any> Map<K, V>.toDictionary(): Dictionary<K, V> =
    MapDictionary(this)

/**
 * Returns a new [Dictionary] containing all key-value pairs from the original map.
 */
fun <K : Any, V : Any> Map<K, V>.toMutableDictionary(): Dictionary<K, V> =
    toHashtable()
