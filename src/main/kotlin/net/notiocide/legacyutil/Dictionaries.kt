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

fun <K : Any, V : Any> dictionaryOf(): Dictionary<K, V> =
    @Suppress("UNCHECKED_CAST") (emptyDictionary as Dictionary<K, V>)

fun <K : Any, V : Any> dictionaryOf(vararg pairs: Pair<K, V>): Dictionary<K, V> =
    MapDictionary(mapOf(*pairs))

fun <K : Any, V : Any> mutableDictionaryOf(): Dictionary<K, V> =
    hashtableOf()

fun <K : Any, V : Any> mutableDictionaryOf(vararg pairs: Pair<K, V>): Dictionary<K, V> =
    hashtableOf(*pairs)

fun <K : Any, V : Any> Map<K, V>.toDictionary(): Dictionary<K, V> =
    MapDictionary(this)

fun <K : Any, V : Any> Map<K, V>.toMutableDictionary(): Dictionary<K, V> =
    toHashtable()
