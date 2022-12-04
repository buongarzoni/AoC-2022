fun solveDay04() {
    val part1 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.split(',')
            firstRange isFullyContainedIn secondRange || secondRange isFullyContainedIn firstRange
        }
    println(part1)
    val part2 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.split(',')
            firstRange overlapsWith secondRange || secondRange overlapsWith firstRange
        }
    println(part2)
}

private infix fun String.isFullyContainedIn(other: String) = lowLimit in other.range() && upperLimit in other.range()
private infix fun String.overlapsWith(other: String) = lowLimit in other.range() || upperLimit in other.range()

private val String.lowLimit get(): Int = substringBefore('-').toInt()
private val String.upperLimit get(): Int = substringAfter('-').toInt()

private fun String.range() = lowLimit..upperLimit
