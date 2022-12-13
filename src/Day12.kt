import java.util.*

fun solveDay12() {
    val input = readInput("Day12_test")
    solve(input)
}

private fun solve(
    input: List<String>,
) {
    val grid = Grid.parse(input)
    println("part 1 solution: ${grid.findShortedPath('S')}")
    println("part 2 solution: ${grid.findShortedPath('a')}")
}

@JvmInline
value class Grid private constructor(
    val points: List<List<Point>>,
) {
    private val columns
        get() = points[0].lastIndex
    private val rows
        get() = points.lastIndex

    companion object {
        fun parse(lines: List<String>) = Grid(
            points = lines
                .mapIndexed { y, line ->
                    line.mapIndexed { x, char ->
                        Point(x, y, char)
                    }
                }
        )
    }

    fun findShortedPath(target: Char): Int? {
        val startingPoint = points.flatten().single { it.char == 'E' }
        val pointsToVisit = ArrayDeque(listOf(startingPoint to 0))
        val seen = mutableSetOf(startingPoint)
        while (pointsToVisit.isNotEmpty()) {
            val (point, distance) = pointsToVisit.removeFirst()
            if (point isAtLevel target) return distance
            point
                .neighbors()
                .filter { it isClimbable point }
                .forEach { if (seen.add(it)) pointsToVisit.add(it to distance + 1) }
        }
        return null
    }

    private fun Point.neighbors() = listOfNotNull(
        getPoint(x, y - 1), getPoint(x, y + 1),
        getPoint(x - 1, y), getPoint(x + 1, y),
    )

    private fun getPoint(x: Int, y: Int) =
        if (x < 0 || x > columns || y < 0 || y > rows) null else points[y][x]
}

data class Point(
    val x: Int,
    val y: Int,
    val char: Char,
) {
    private val height = when (char) {
        'S' -> 0
        'E' -> 26
        else -> char - 'a'
    }

    infix fun isAtLevel(level: Char) = char == level
    infix fun isClimbable(other: Point) = height + 1 >= other.height
}
