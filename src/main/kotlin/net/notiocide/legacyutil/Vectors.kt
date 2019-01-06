package net.notiocide.legacyutil

import java.util.*

/** Returns an empty new [Vector]. */
fun <E> vectorOf(): Vector<E> =
    Vector()

/** Returns an empty new [Vector] with the given [elements]. */
fun <E> vectorOf(vararg elements: E): Vector<E> =
    Vector(listOf(*elements))

/** Returns a new [Vector] containing all elements from the original list. */
fun <E> List<E>.toVector() =
    Vector(this)
