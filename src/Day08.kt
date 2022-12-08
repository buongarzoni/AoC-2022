fun solveDay08() {
    val input = readInput("Day08")
    solvePart1(input)
    solvePart2(input)
}
private var trees: List<List<Int>> = emptyList()
private val visibleTrees: MutableList<MutableList<Byte>> = mutableListOf()
private fun solvePart1(input: List<String>) {
    trees  = input.map { line -> line.map { it.digitToInt() } }
    initVisibleTrees(input)
    fillVisibleRows(input)
}
private fun initVisibleTrees(input: List<String>) {
    input.forEach {
         val row : MutableList<Byte> = MutableList(it.length) { 0 }
        visibleTrees.add(row)
    }
}

private fun fillVisibleRows(input: List<String>) {
    val rows = visibleTrees.size
    val updatedVisibleTrees: MutableList<MutableList<Byte>> = mutableListOf()
    visibleTrees.forEachIndexed { row, bytes ->
        if (row == 0 || row == rows - 1) {
            val updatedRow: MutableList<Byte> = bytes.map { 1.toByte() }.toMutableList()
            updatedVisibleTrees.add(updatedRow)
        } else {
            val updatedRow: MutableList<Byte> = mutableListOf()
            val columns = bytes.size
            bytes.forEachIndexed { column, byte ->
                if (column == 0 || column == columns - 1) {
                    updatedRow.add(1)
                } else {
                    if (
                        isVisibleFromTop(row, column) ||
                        isVisibleFromLeft(row, column) ||
                        isVisibleFromRight(row, column) ||
                        isVisibleFromBottom(row, column)
                    ) {
                        updatedRow.add(1)
                    } else {
                        updatedRow.add(0)
                    }
                }
            }
            updatedVisibleTrees.add(updatedRow)
        }
    }
    val res = updatedVisibleTrees.sumOf { it.count { byte -> byte == 1.toByte() } }
    println("solution 1 : $res")
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

private fun solvePart2(input: List<String>) {
    input.forEach { line ->
    }
}
