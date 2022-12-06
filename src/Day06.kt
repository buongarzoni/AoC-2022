fun solveDay06() {
    val input = readInput("Day06")
    solvePart1(input)
    solvePart2(input)
}

private fun solvePart1(input: List<String>) {
    input.forEach { line ->
        val result = line.charactersUntilUniqueStringOfSize(4)
        println("CRB solution part 1 : $result")
    }
}

private fun solvePart2(input: List<String>) {
    input.forEach { line ->
        val result = line.charactersUntilUniqueStringOfSize(14)
        println("CRB solution part 2 : $result")
    }
}

private fun String.charactersUntilUniqueStringOfSize(size: Int) = windowed(size)
    .indexOfFirst { it.toSet().size == size } + size