package net.notiocide.legacyutil

import java.util.*

fun <K : Any, V : Any> hashtableOf(): Hashtable<K, V> =
    Hashtable()

fun <K : Any, V : Any> hashtableOf(vararg pairs: Pair<K, V>): Hashtable<K, V> =
    Hashtable(mapOf(*pairs))

fun <K : Any, V : Any> Map<K, V>.toHashtable() =
    Hashtable(this)
