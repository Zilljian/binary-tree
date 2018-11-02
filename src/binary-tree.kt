/*
Launch the program, enter initial numbers through Space.
* Next, pick one of the following actions:
* add {Finite list of numbers through Space} - Add this numbers to tree.
* printElements - Print entire tree as list.
* isHere {number} - Check, is certain number contained by tree.
* printRange {left value} {right value} - Print every number from the tree included into [left value, right value]
* break - Shut the program down.
* */

fun main(args : Array<String>) {
    println("Please, enter initial numbers through space >>")
    var buffer = readLine()!!.split(' ')
    val binaryTreeEntity = BinaryTree(buffer.map(String::toInt))
    println(binaryTreeEntity.getTreeList())

    while(true) {
        buffer = readLine()!!.split(' ')
        if (buffer[0] == "break") break
        when (buffer[0]) {
           "printElements" -> println(binaryTreeEntity.getTreeList())
           "add" -> binaryTreeEntity.add(buffer.drop(1).map(String::toInt).slice(1 until buffer.size-1))
           "isHere" -> println(binaryTreeEntity.isHere(buffer[1].toInt()))
           "printRange" -> println(binaryTreeEntity.getInRange(buffer[1].toInt(), buffer[2].toInt()))
        }
    }
}

class Node(var rNode: Node?, var lNode: Node?, val value: Int)

class BinaryTree(elements: List<Int>) {

    private val head = Node(null, null, elements[0])
    private var minElement: Int = elements.min()!!
    private var maxElement: Int = elements.max()!!

    init { for (item in elements.drop(1)) addNode(item) }

    fun add(element: List<Int>) { for(item in element) addNode(item) }

    private fun addNode(element: Int, parent: Node? = head) {
        when {
            element > parent!!.value && parent.rNode == null -> parent.rNode = Node(null, null, element)
            element > parent.value -> addNode(element, parent.rNode)
            parent.lNode == null -> parent.lNode = Node(null, null, element)
            else -> addNode(element, parent.lNode)
        }
        maxElement = if(parent.value > maxElement) parent.value else maxElement
        minElement = if(parent.value < minElement) parent.value else minElement
    }

    fun isHere(element: Int) = element in getInRange()

    fun getTreeList() = getInRange()

    fun getInRange(lValue: Int = minElement, rValue: Int = maxElement, parent: Node? = head) : List<Int> = when {
        parent == null -> listOf()
        parent.value in lValue..rValue -> listOf(parent.value).plus(getInRange(lValue, rValue, parent.lNode)).plus(getInRange(lValue, rValue, parent.rNode))
        parent.value > rValue  -> getInRange(lValue, rValue, parent.lNode)
        parent.value < lValue -> getInRange(lValue, rValue, parent.rNode)
        else -> listOf()
    }
}