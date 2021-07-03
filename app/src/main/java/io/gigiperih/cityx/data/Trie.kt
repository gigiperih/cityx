package io.gigiperih.cityx.data


class Trie {

    data class Node(
        var word: String? = null,
        var id: Int? = null,
        val childNodes: MutableMap<Char, Node> = mutableMapOf(),
    )

    private val root = Node()

    fun insert(word: String, id: Int?) {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.id = id
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
        return currentNode.id != null
    }

    fun startsWith(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.id == null
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
        if (node?.id != null) {
            list.add("${node.word}:${node.id}")
        }

        if (!node?.childNodes.isNullOrEmpty()) {
            node?.childNodes?.forEach {
                list += traverse(it.value)
            }
        }

        return list
    }
}