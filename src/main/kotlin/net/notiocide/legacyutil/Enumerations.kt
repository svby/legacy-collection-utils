package net.notiocide.legacyutil

import java.util.*

internal class IteratorEnumeration<E>(private val iterator: Iterator<E>) : Enumeration<E> {

    override fun hasMoreElements() = iterator.hasNext()
    override fun nextElement() = iterator.next()

}

/**
 * Returns an [Enumeration] that traverses the remaining elements covered by this [Iterable].
 *
 * Traversal is undefined if any methods are called on this iterable after the call to asEnumeration.
 */
fun <T> Iterable<T>.asEnumeration(): Enumeration<T> = IteratorEnumeration(iterator())
