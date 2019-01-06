package net.notiocide.legacyutil

import java.util.*

fun <E> vectorOf(): Vector<E> =
    Vector()

fun <E> vectorOf(vararg elements: E): Vector<E> =
    Vector(listOf(*elements))

fun <E> List<E>.toVector() =
    Vector(this)
