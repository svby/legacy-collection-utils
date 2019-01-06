package net.notiocide.legacyutil

import java.util.*

internal class IteratorEnumeration<E>(private val iterator: Iterator<E>) : Enumeration<E> {

    override fun hasMoreElements() = iterator.hasNext()
    override fun nextElement() = iterator.next()

}

fun <T> Iterable<T>.asEnumeration(): Enumeration<T> = IteratorEnumeration(iterator())
