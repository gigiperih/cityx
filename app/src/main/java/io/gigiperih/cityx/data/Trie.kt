package io.gigiperih.cityx.data


class Trie {

    data class Node(
        var word: String? = null,
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
        currentNode.word = word
    }

    fun search(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.city != null
    }

    fun startsWith(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.city == null
    }

    fun startsNode(word: String): Node? {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return null
            }
            currentNode = currentNode.childNodes[char]!!
        }

        return currentNode
    }

    fun traverse(node: Node?): List<String> {
        val list = mutableListOf<String>()
        if (node?.city != null) {
            list.add("${node.word}:${node.city}")
        }

        if (!node?.childNodes.isNullOrEmpty()) {
            node?.childNodes?.forEach {
                list += traverse(it.value)
            }
        }

        return list
    }
}