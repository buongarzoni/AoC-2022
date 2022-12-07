private const val TOTAL_MEMORY = 70_000_000L
private const val NEEDED_MEMORY = 30_000_000L

fun solveDay07() {
    val input = readInput("Day07_test")
    execute(input)
}

private val currentPath = ArrayDeque<String>()
private val sizesByPath = mutableMapOf<String, Long>()
private var usedMemory = 0L

private fun execute(input: List<String>) {
    input.forEach { line ->
        if (line.contains("$ cd")) {
            changeDirectory(command = line)
        } else if (line.isFile()){
            val size = line.getSize()
            addFileSizeToDirectoryAndParents(size)
            usedMemory += size
        }
    }

    val availableMemory = TOTAL_MEMORY - usedMemory
    val neededMemoryToFree = NEEDED_MEMORY - availableMemory

    val solution1 = sizesByPath.values.filter { it <= 100000 }.sum()
    println("Part 1 solution : $solution1")
    val solution2 = sizesByPath.values.filter { it >= neededMemoryToFree }.min()
    println("Part 2 solution : $solution2")
}

private fun changeDirectory(command: String) {
    val moveTo = command.split(" ")[2]
    if (moveTo == "..") {
        currentPath.removeLast()
    } else {
        currentPath.add(moveTo)
    }
}

private fun addFileSizeToDirectoryAndParents(size: Int) {
    var dir = "/"
    for (e in currentPath) {
        if (!dir.endsWith("/")) dir += "/"
        dir += e
        sizesByPath.compute(dir) { _, prev -> (prev ?: 0L) + size }
    }
}

private fun String.isFile() = split(" ").first().toIntOrNull() != null
private fun String.getSize() = split(" ").first().toInt()
