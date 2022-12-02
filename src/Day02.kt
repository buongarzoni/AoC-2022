fun solveDay02() {
    val input = readInput("Day02").map { ElveMatch.parse(it) }
    println(input.sumOf { it.points })
    val input2 = readInput("Day02").map { NewElveMatch.parse(it) }
    println(input2.sumOf { it.points })
}

data class ElveMatch(val first: String, val second: String) {
    val points = getResult() + pointsForElection()
    companion object {
        fun parse(string: String) = ElveMatch(
            first = string.take(1),
            second = string.drop(2),
        )
    }

    private fun getResult() = when(first) {
        "A" -> when(second) {
            "X" -> 3
            "Y" -> 6
            else -> 0
        }
        "B" -> when(second) {
            "X" -> 0
            "Y" -> 3
            else -> 6
        }
        else -> when(second) {
            "X" -> 6
            "Y" -> 0
            else -> 3
        }
    }

    private fun pointsForElection() = when(second) {
        "X" -> 1
        "Y" -> 2
        else -> 3
    }

}

data class NewElveMatch(val first: String, val second: String) {
    val points = getResult() + pointsForElection()
    companion object {
        fun parse(string: String) = NewElveMatch(
            first = string.take(1),
            second = string.drop(2),
        )
    }

    private fun getResult() = when(second) {
        "X" -> 0
        "Y" -> 3
        else -> 6
    }

    private fun pointsForElection() = when(first) {
        "A" -> when(second) {
            "X" -> 3
            "Y" -> 1
            else -> 2
        }
        "B" -> when(second) {
            "X" -> 1
            "Y" -> 2
            else -> 3
        }
        else -> when(second) {
            "X" -> 2
            "Y" -> 3
            else -> 1
        }
    }
}


