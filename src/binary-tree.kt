fun main(args : Array<String>) {
    val tempArray = mutableListOf(1,34,3,5,6,7,343)
    val binaryTreeEntity = BinaryTree(tempArray)

    for (item in binaryTreeEntity.getInRange(1,7)) print("$item ")
}

class Node (var rNode: Node?, var lNode: Node?, val value: Int)

class BinaryTree (elements: MutableList<Int>) {

    private val head = Node(null, null, elements[0])
    private var minElement: Int = elements.min()!!
    private var maxElement: Int = elements.max()!!

    init {
        elements.remove(1)
        for (item in elements) add(item)
    }

    fun add (element: Int, parent: Node? = head) {
        when {
            element > parent!!.value && parent.rNode == null -> parent.rNode = Node(null, null, element)
            element > parent.value -> add(element, parent.rNode)
            parent.lNode == null -> parent.lNode = Node(null, null, element)
            else -> add(element, parent.lNode)
        }
        maxElement = if(parent.value > maxElement) parent.value else maxElement
        minElement = if(parent.value < minElement) parent.value else minElement
    }

    fun isHere (element: Int) = element in getInRange()

    fun getInRange (lValue: Int = minElement, rValue: Int = maxElement, parent: Node? = head) : Array<Int> = when {
        parent == null -> arrayOf()
        parent.value in lValue..rValue -> arrayOf(parent.value).plus(getInRange(lValue, rValue, parent.rNode)).plus(getInRange(lValue, rValue, parent.lNode))
        else -> getInRange(lValue, rValue, parent.rNode).plus(getInRange(lValue, rValue, parent.lNode))
    }
}