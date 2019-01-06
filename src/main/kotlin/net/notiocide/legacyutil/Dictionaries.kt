package net.notiocide.legacyutil

import java.util.*
import kotlin.collections.LinkedHashMap

private class MapDictionary<K : Any, V : Any>(private val map: Map<K, V>) : Dictionary<K, V>() {

    override fun put(key: K, value: V) = throw UnsupportedOperationException()
    override fun remove(key: Any?) = throw UnsupportedOperationException()

    // NPE feels wrong (IllegalArgumentException) but it is specified by Dictionary's contract.
    override fun get(key: Any?) = map[key ?: throw NullPointerException()]

    override fun size() = map.size
    override fun isEmpty() = size() == 0

    override fun keys() = map.keys.iterator().asEnumeration()
    override fun elements() = map.values.iterator().asEnumeration()

    override fun hashCode() = map.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dictionary<*, *>
        if (other.size() != size()) return false

        try {
            for ((k, v) in map) if (other[k] != v) return false
        } catch (e: Exception) {
            return false
        }

        return true
    }

    override fun toString() = map.toString()

}

private val emptyDictionary = MapDictionary(emptyMap())

/** Creates a [Sequence] instance that wraps the original dictionary's entries. */
fun <K : Any, V : Any> Dictionary<K, V>.asSequence() =
    sequence {
        for (key in keys()) {
            try {
                yield(Pair(key, this@asSequence[key]))
            } catch (_: NullPointerException) {
            }
        }
    }

/** Returns a [List] containing all key-value [Pair]s. */
fun <K : Any, V : Any> Dictionary<K, V>.toList(): List<Pair<K, V>> =
    asSequence().toList()

/** Returns a new [Map] containing all key-value pairs from the original [Dictionary]. */
fun <K : Any, V : Any> Dictionary<K, V>.toMap(): Map<K, V> =
    Collections.unmodifiableMap(toMutableMap())

/** Returns a new mutable [Map] containing all key-value pairs from the original [Dictionary]. */
fun <K : Any, V : Any> Dictionary<K, V>.toMutableMap(): MutableMap<K, V> =
    LinkedHashMap<K, V>().apply {
        for (key in keys()) {
            try {
                put(key, this@toMutableMap[key])
            } catch (_: NullPointerException) {
            }
        }
    }

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

/** Returns a new read-only [Dictionary] containing all key-value pairs from the original [Map]. */
fun <K : Any, V : Any> Map<K, V>.toDictionary(): Dictionary<K, V> =
    MapDictionary(this)

/** Returns a new read-only [Dictionary] containing all key-value pairs from the original dictionary. */
fun <K : Any, V : Any> Dictionary<K, V>.toDictionary(): Dictionary<K, V> =
    MapDictionary(toMap())

/** Returns a new [Dictionary] containing all key-value pairs from the original [Map]. */
fun <K : Any, V : Any> Map<K, V>.toMutableDictionary(): Dictionary<K, V> =
    toHashtable()

/** Returns a new read-only [Dictionary] containing all key-value pairs from the given collection of [Pair]s. */
fun <K : Any, V : Any> Iterable<Pair<K, V>>.toDictionary(): Dictionary<K, V> =
    MapDictionary(toMap())

/** Returns a new [Dictionary] containing all key-value pairs from the given collection of [Pair]s. */
fun <K : Any, V : Any> Iterable<Pair<K, V>>.toMutableDictionary(): Dictionary<K, V> =
    toHashtable()

/** Returns a new read-only [Dictionary] containing all key-value pairs from the given collection of [Pair]s. */
fun <K : Any, V : Any> Sequence<Pair<K, V>>.toDictionary(): Dictionary<K, V> =
    MapDictionary(toMap())

/** Returns a new [Dictionary] containing all key-value pairs from the given collection of [Pair]s. */
fun <K : Any, V : Any> Sequence<Pair<K, V>>.toMutableDictionary(): Dictionary<K, V> =
    toHashtable()
