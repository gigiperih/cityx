package io.gigiperih.cityx.data.structure

import io.gigiperih.cityx.data.City

/**
 * Trie implementation to tackle faster runtime search problems
 *
 * references:
 * - https://www.youtube.com/watch?v=zIjfhVPRZCg
 * - https://gist.github.com/sagar-viradiya/891cf7d08b6ac13bb1fbdc411b76f6a5
 * - https://github.com/kennycason/trie_kotlin
 */
class Trie {
    data class Node(
        var city: City? = null,
        val childNodes: MutableMap<Char, Node> = mutableMapOf(),
    )

    private val root = Node()

    fun insert(word: String, city: City?) {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.city = city
    }

    fun filterPrefix(word: String): Node? {
        // do not waste time by traversing with empty keywords
        // just return original sorted list
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return null
            }
            currentNode = currentNode.childNodes[char]!!
        }

        return currentNode
    }
}