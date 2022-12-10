import java.awt.Point
import kotlin.math.sign

fun solveDay09() {
    val input = readInput("Day09")
    val part1 = solve(input, (1 .. 2).map { Point(1, 1) })
    println("Part 1 : $part1")
    val part2 = solve(input, (1 .. 10).map { Point(1, 1) })
    println("Part 2 : $part2")
}

private fun solve(input: List<String>, points: List<Point>): Int {
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()
    input.forEach { line ->
        val (direction, moves) = line.split(" ")
        for (move in 0 until moves.toInt()) {
            points.moveHead(direction)
            points.forEachIndexed { index, point ->
                if (index != 0) {
                    val leading = points[index - 1]
                    point.follow(leading)
                    if (index == points.lastIndex) {
                        visitedPositions.add(point.toPair())
                    }
                }
            }
        }
    }
    return visitedPositions.size
}
private fun List<Point>.moveHead(direction: String) {
    when (direction) {
        "R" -> first().moveRight()
        "L" -> first().moveLeft()
        "U" -> first().moveUp()
        "D" -> first().moveDown()
    }
}

private fun Point.follow(other: Point) {
    if (distance(other) >= 2) {
        translate((other.x - x).sign, (other.y - y).sign)
    }
}

private fun Point.moveRight() = translate(1, 0)
private fun Point.moveLeft() = translate(-1, 0)
private fun Point.moveUp() = translate(0, 1)
private fun Point.moveDown() = translate(0, -1)
private fun Point.toPair() = x to y
