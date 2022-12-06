fun solveDay06() {
    val input = readInput("Day06")
    solvePart1(input)
}

private fun solvePart1(input: List<String>) {
    input.map {
        for (i in 0 ..it.length-4) {
            val substring = it.substring(i, i + 4)
            //println(substring)
            if (
                !substring.drop(1).contains(substring[0]) &&
                !substring.drop(2).contains(substring[1]) &&
                !substring.drop(3).contains(substring[2])
            ) {
                println(substring)
                println(i+4)
                break
            }

        }
    }
}