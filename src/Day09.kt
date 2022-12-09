import java.awt.Point
import java.lang.Math.abs
import kotlin.math.sign

fun solveDay09() {
    val input = readInput("Day09")

    solvePart1(input)
    solvePart2(input)
}


private fun solvePart1(input: List<String>) {
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()
    val headPosition = Point(1, 1)
    val tailPosition = Point(1, 1)
    visitedPositions.add(tailPosition.x to tailPosition.y)
    input.forEach { line ->
        val (direction, moves) = line.split(" ")
        for (move in 0 until moves.toInt()) {
            when(direction) {
                "R" -> {
                    headPosition.moveRight()
                    if (!tailPosition.isTouching(headPosition)) {
                        tailPosition.move(headPosition.x - 1, headPosition.y)
                        visitedPositions.add(tailPosition.x to tailPosition.y)
                    }
                }
                "L" -> {
                    headPosition.moveLeft()
                    if (!tailPosition.isTouching(headPosition)) {
                        tailPosition.move(headPosition.x + 1 , headPosition.y)
                        visitedPositions.add(tailPosition.x to tailPosition.y)
                    }
                }
                "U" -> {
                    headPosition.moveUp()
                    if (!tailPosition.isTouching(headPosition)) {
                        tailPosition.move(headPosition.x, headPosition.y - 1)
                        visitedPositions.add(tailPosition.x to tailPosition.y)
                    }
                }
                "D" -> {
                    headPosition.moveDown()
                    if (!tailPosition.isTouching(headPosition)) {
                        tailPosition.move(headPosition.x, headPosition.y + 1)
                        visitedPositions.add(tailPosition.x to tailPosition.y)
                    }
                }
            }
        }
    }
    println("Part 1 : ${visitedPositions.size}")
}

private fun solvePart2(input: List<String>) {
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()
    val points = listOf(
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
        Point(1, 1),
    )
    visitedPositions.add(points[9].x to points[9].y)
    input.forEach { line ->
        val (direction, moves) = line.split(" ")
        for (move in 0 until moves.toInt()) {
            when(direction) {
                "R" -> {
                    points[0].moveRight()
                    points.forEachIndexed { index, point ->
                        if (index != 0) {
                            val anterior = points[index - 1]
                            point.follow(anterior)

                            if (index == 9) {
                                visitedPositions.add(point.x to point.y)
                            }
                        }
                    }
                }
                "L" -> {
                    points[0].moveLeft()
                    points.forEachIndexed { index, point ->
                        if (index != 0) {
                            val anterior = points[index - 1]
                            point.follow(anterior)

                            if (index == 9) {
                                visitedPositions.add(point.x to point.y)
                            }
                        }
                    }
                }
                "U" -> {
                    points[0].moveUp()
                    points.forEachIndexed { index, point ->
                        if (index != 0) {
                            val anterior = points[index - 1]
                            point.follow(anterior)

                            if (index == 9) {
                                visitedPositions.add(point.x to point.y)
                            }
                        }
                    }
                }
                "D" -> {
                    points[0].moveDown()
                    points.forEachIndexed { index, point ->
                        if (index != 0) {
                            val anterior = points[index - 1]
                            point.follow(anterior)

                            if (index == 9) {
                                visitedPositions.add(point.x to point.y)
                            }

                        }
                    }
                }
            }
        }
    }
    println("Part 2 : ${visitedPositions.size}")
}

private fun Point.follow(other: Point) {
    if (other.x == x) {
        if (other.y > y + 1) {
            y++
        } else if (other.y < y - 1) {
            y--
        }
    } else if (other.y == y) {
        if (other.x > x + 1) {
            x++
        } else if (other.x < x - 1) {
            x--
        }
    } else {  // diagonal
        val xDiff = other.x - x
        val yDiff = other.y - y
        if (kotlin.math.abs(xDiff) > 1 || kotlin.math.abs(yDiff) > 1) {
            x += xDiff.sign
            y += yDiff.sign
        }
    }
}
private fun Point.isTouching(other: Point) = distance(other) < 2

private fun Point.moveRight() = translate(1, 0)
private fun Point.moveLeft() = translate(-1, 0)
private fun Point.moveUp() = translate(0, 1)
private fun Point.moveDown() = translate(0, -1)

