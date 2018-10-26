import java.util.*

fun main(args : Array<String>) {
    val tempArray = arrayOf(1,34,3,5,6,7,3,5,343)
    val binaryTreeEntity = BinaryTree(tempArray)

    println(binaryTreeEntity.isHere(3))
    binaryTreeEntity.add(200)
    println(binaryTreeEntity.isHere(200))
}

class Node (var rNode: Node?, var lNode: Node?, val value: Int)

class BinaryTree (private val elements: Array<Int>) {

    private val head = Node(null, null, elements[0])
    private val listOfElements = Vector<Int>(elements[0])

    init {
        elements.drop(1)
        for (item in elements) add(item)
    }

    fun add (element: Int, parent: Node? = head) {
        when {
            element > parent!!.value && parent.rNode == null -> parent.rNode = Node(null, null, element)
            element > parent.value -> add(element, parent.rNode)
            parent.lNode == null -> parent.lNode = Node(null, null, element)
            else -> add(element, parent.lNode)
        }
        listOfElements.addElement(element)
    }

    fun isHere (element: Int) = element in listOfElements
}