fun solveDay04() {
    val part1 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.getRanges()
            firstRange in secondRange || secondRange in firstRange
        }
    println(part1)
    val part2 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.getRanges()
            firstRange overlaps secondRange
        }
    println(part2)
}

private operator fun IntRange.contains(other: IntRange) = first in other && last in other
private infix fun IntRange.overlaps(other: IntRange) = first in other || last in other || other.first in this || other.last in this

private val String.lowLimit get(): Int = substringBefore('-').toInt()
private val String.upperLimit get(): Int = substringAfter('-').toInt()

private fun String.getRanges() = split(',').map { it.range() }
private fun String.range() = lowLimit..upperLimit
