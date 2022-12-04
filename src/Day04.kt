fun solveDay04() {
    val values = readInput("Day04")
        .map { line -> line.split("-", ",").map { it.toInt() } }
    val part1 = values.count { (a, b, c, d) -> (a <= c && b >= d) || (a >= c && b <= d) }
    println(part1)
    val part2 = values.count { (a, b, c, d) -> (c in a..b) || (a in c..d) }
    println(part2)

    val myPart1 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.getRanges()
            firstRange in secondRange || secondRange in firstRange
        }
    println(myPart1)
    val myPart2 = readInput("Day04")
        .count {
            val (firstRange, secondRange) = it.getRanges()
            firstRange overlaps secondRange
        }
    println(myPart2)
}

private operator fun IntRange.contains(other: IntRange) = first in other && last in other
private infix fun IntRange.overlaps(other: IntRange) =
    first in other || last in other || other.first in this || other.last in this

private val String.lowLimit get(): Int = substringBefore('-').toInt()
private val String.upperLimit get(): Int = substringAfter('-').toInt()

private fun String.getRanges() = split(',').map { it.range() }
private fun String.range() = lowLimit..upperLimit
