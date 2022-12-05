fun solveDay05() {
    val input = readInput("Day05")
    println(makeHardcodedMap())
    solvePart1(input)
    solvePart2(input)
}

private fun solvePart1(input: List<String>) {
    val hardcodedMap = makeHardcodedMap()
    input.map { line ->
        val strings = line.split(" ")
        val quantity = strings[1].toInt()
        val from = strings[3].toInt()
        val target = strings[5].toInt()

        repeat(quantity) {
            hardcodedMap[target]!!.add(hardcodedMap[from]!!.removeLast())
        }
    }
    println("Part 1")
    println(hardcodedMap)
}

private fun solvePart2(input: List<String>) {
    val hardcodedMap = makeHardcodedMap()
    input.map { line ->
        val strings = line.split(" ")
        val quantity = strings[1].toInt()
        val from = strings[3].toInt()
        val target = strings[5].toInt()

        val removedItems: ArrayDeque<String> = ArrayDeque()
        repeat(quantity) {
            removedItems.add(hardcodedMap[from]!!.removeLast())
        }
        repeat(quantity) {
            hardcodedMap[target]!!.add(removedItems.removeLast())
        }
    }
    println("Part 2")
    println(hardcodedMap)
}

private fun makeHardcodedMap(): Map<Int, ArrayDeque<String>> {
    val first: ArrayDeque<String> = ArrayDeque()
    first.fillWith("WMLF")

    val second: ArrayDeque<String> = ArrayDeque()
    second.fillWith("BZVMF")

    val third: ArrayDeque<String> = ArrayDeque()
    third.fillWith("HVRSLQ")

    val fourth: ArrayDeque<String> = ArrayDeque()
    fourth.fillWith("FSVQPMTJ")

    val five: ArrayDeque<String> = ArrayDeque()
    five.fillWith("LSW")

    val six: ArrayDeque<String> = ArrayDeque()
    six.fillWith("FVPMRJW")

    val seven: ArrayDeque<String> = ArrayDeque()
    seven.fillWith("JQCPNRF")

    val eight: ArrayDeque<String> = ArrayDeque()
    eight.fillWith("VHPSZWRB")

    val nine: ArrayDeque<String> = ArrayDeque()
    nine.fillWith("BMJCGHZW")

    return mapOf(
        1 to first,
        2 to second,
        3 to third,
        4 to fourth,
        5 to five,
        6 to six,
        7 to seven,
        8 to eight,
        9 to nine,
    )
}

private fun ArrayDeque<String>.fillWith(string: String) {
    for (char in string) {
        this.add(char.toString())
    }
}
