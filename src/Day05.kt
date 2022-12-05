fun solveDay05() {
    val input = readInput("Day05")
    println(makeHardcodedMap())
    solvePart1(input)
    solvePart2(input)
}

private fun solvePart1(input: List<String>) {
    val hardcodedMap = makeHardcodedMap()
    input.map { line ->
        val splitted = line.split(" ")
        val quantity = splitted[1].toInt()
        val from = splitted[3].toInt()
        val target = splitted[5].toInt()

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
        val splitted = line.split(" ")
        val quantity = splitted[1].toInt()
        val from = splitted[3].toInt()
        val target = splitted[5].toInt()

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
    first.apply {
        add("W")
        add("M")
        add("L")
        add("F")
    }
    val second: ArrayDeque<String> = ArrayDeque()
    second.apply {
        add("B")
        add("Z")
        add("V")
        add("M")
        add("F")
    }
    val third: ArrayDeque<String> = ArrayDeque()
    third.apply {
        add("H")
        add("V")
        add("R")
        add("S")
        add("L")
        add("Q")
    }

    val fourth: ArrayDeque<String> = ArrayDeque()
    fourth.apply {
        add("F")
        add("S")
        add("V")
        add("Q")
        add("P")
        add("M")
        add("T")
        add("J")
    }
    val five: ArrayDeque<String> = ArrayDeque()
    five.apply {
        add("L")
        add("S")
        add("W")
    }

    val six: ArrayDeque<String> = ArrayDeque()
    six.apply {
        add("F")
        add("V")
        add("P")
        add("M")
        add("R")
        add("J")
        add("W")
    }

    val seven: ArrayDeque<String> = ArrayDeque()
    seven.apply {
        add("J")
        add("Q")
        add("C")
        add("P")
        add("N")
        add("R")
        add("F")
    }

    val eight: ArrayDeque<String> = ArrayDeque()
    eight.apply {
        add("V")
        add("H")
        add("P")
        add("S")
        add("Z")
        add("W")
        add("R")
        add("B")
    }

    val nine: ArrayDeque<String> = ArrayDeque()
    nine.apply {
        add("B")
        add("M")
        add("J")
        add("C")
        add("G")
        add("H")
        add("Z")
        add("W")
    }
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