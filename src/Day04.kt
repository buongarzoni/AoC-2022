fun solveDay04() {
    val result = readInput("Day04").map { line ->
        line.split(',')
    }.sumOf {
        val (first, second) = it
        if (
            first.range().first in second.range() && first.range().last in second.range() ||
            second.range().first in first.range() && second.range().last in first.range()
        ) {
            1L
        } else {
            0L
        }
    }
    println(result)

    val result2 = readInput("Day04").map { line ->
        line.split(',')
    }.sumOf {
        val (first, second) = it
        if (
            first.range().first in second.range() || first.range().last in second.range() ||
            second.range().first in first.range() || second.range().last in first.range()
        ) {
            1L
        } else {
            0L
        }
    }
    println(result2)
}

private fun String.range(): IntRange {
    val (first, last) = split('-')
    return first.toInt() .. last.toInt()
}