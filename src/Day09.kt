import java.awt.Point

fun solveDay09() {
    val input = readInput("Day09")
    solvePart1(input)
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
                        println(tailPosition)
                        val entry = tailPosition.x to tailPosition.y
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
    println(visitedPositions.size)
}

private fun Point.isTouching(other: Point) = distance(other) < 2

private fun Point.moveRight() = translate(1, 0)
private fun Point.moveLeft() = translate(-1, 0)
private fun Point.moveUp() = translate(0, 1)
private fun Point.moveDown() = translate(0, -1)

