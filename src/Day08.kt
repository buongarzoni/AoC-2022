fun solveDay08() {
    val input = readInput("Day08_test")
    solvePart1(input)
}
private var trees: List<List<Int>> = emptyList()
private val visibleTrees: MutableList<MutableList<Boolean>> = mutableListOf()
private fun solvePart1(input: List<String>) {
    trees  = input.map { line -> line.map { it.digitToInt() } }
    initVisibleTrees(input)
    fillVisibleRows(input)
}
private fun initVisibleTrees(input: List<String>) {
    input.forEach {
         val row  = MutableList(it.length) { false }
        visibleTrees.add(row)
    }
}

private fun fillVisibleRows(input: List<String>) {
    var maxValue = 0L
    val rows = visibleTrees.size
    val updatedVisibleTrees: MutableList<MutableList<Boolean>> = mutableListOf()
    visibleTrees.forEachIndexed { row, bytes ->
        if (row == 0 || row == rows - 1) {
            val updatedRow = bytes.map { true }.toMutableList()
            updatedVisibleTrees.add(updatedRow)
        } else {
            val updatedRow: MutableList<Boolean> = mutableListOf()
            val columns = bytes.size
            bytes.forEachIndexed { column, byte ->
                if (column == 0 || column == columns - 1) {
                    updatedRow.add(true)
                } else {
                    val result = visibleTrees(row, column)
                    if (result> maxValue) {
                        maxValue = result
                    }
                    if (
                        isVisibleFromTop(row, column) ||
                        isVisibleFromLeft(row, column) ||
                        isVisibleFromRight(row, column) ||
                        isVisibleFromBottom(row, column)
                    ) {
                        updatedRow.add(true)
                    } else {
                        updatedRow.add(false)
                    }
                }
            }
            updatedVisibleTrees.add(updatedRow)
        }
    }
    val res = updatedVisibleTrees.sumOf { it.count() }
    println(updatedVisibleTrees)
    println("solution 1 : $res")
    println("solution 2 : $maxValue")
}

private fun isVisibleFromTop(rowP: Int, columnP: Int) : Boolean {
    val height = trees[rowP][columnP]
    for (row in 0 until rowP) {
        if (trees[row][columnP] >= height) return false
    }
    return true
}

private fun isVisibleFromLeft(rowP: Int, columnP: Int) : Boolean {
    val height = trees[rowP][columnP]
    for (column in 0 until columnP) {
        if (trees[rowP][column] >= height) return false
    }
    return true
}

private fun isVisibleFromRight(rowP: Int, columnP: Int) : Boolean {
    val height = trees[rowP][columnP]
    for (column in columnP + 1 until trees[rowP].size) {
        if (trees[rowP][column] >= height) return false
    }
    return true
}

private fun isVisibleFromBottom(rowP: Int, columnP: Int) : Boolean {
    val height = trees[rowP][columnP]
    for (row in rowP +1 until trees.size) {
        if (trees[row][columnP] >= height) return false
    }
    return true
}

private fun visibleTrees(rowP: Int, columnP: Int) : Long {
    val height = trees[rowP][columnP]
    var top = 0L
    var right = 0L
    var left = 0L
    var bottom = 0L
    for (row in rowP + 1 until trees.size) {
        bottom++
        if (trees[row][columnP] >= height) break
    }
    for (column in columnP + 1 until trees[rowP].size) {
        right++
        if (trees[rowP][column] >= height) break
    }
    for (column in (0 until columnP).reversed()) {
        left++
        if (trees[rowP][column] >= height) break
    }
    for (row in (0 until rowP).reversed()) {
        top++
        if (trees[row][columnP] >= height) break
    }
    return top * right * left * bottom
}
