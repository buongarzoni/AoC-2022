fun solveDay10() {
    val input = readInput("Day10")
    solve(input)
}

private fun solve(input: List<String>) {
    var x = 1
    var cycle = 1
    var sum = 0

    fun processCycle() {
        if ((cycle - 20) % 40 == 0) {
            sum += cycle * x
        }
        if (cycle % 40 in x..x + 2) {
            print("[]")
        } else {
            print("  ")
        }
        cycle++

        if ((cycle - 1) % 40 == 0) {
            println()
        }
    }

    input.forEach { line ->
        if (line == "noop") {
            processCycle()
        } else {
            processCycle()
            processCycle()
            x += line.substringAfter(" ").toInt()
        }
    }

    println(sum)
}