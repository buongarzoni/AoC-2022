import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

private const val ROW = 2_000_000
private const val TUNING_FREQUENCY_MULTIPLIER = 4_000_000
fun solveDay15() {
    val lines = readInput("Day15")
    solve(lines)
}

var minX = 0
var minY = 0
var maxX = 0
var maxY = 0
private fun solve(
    lines: List<String>,
) {
    val sensorsRadius = mutableMapOf<Position, Int>()
    val sensors = mutableSetOf<Position>()
    val beacons = mutableSetOf<Position>()
    val invalidPositions = mutableSetOf<Position>()

    lines.forEach { line ->
        val sensor = line.getSensor()
        val beacon = line.getBeacon()
        if (sensor.y == ROW) sensors.add(sensor)
        if (beacon.y == ROW) beacons.add(beacon)

        val distance = sensor distance beacon
        sensorsRadius[sensor] = distance +1
        if (ROW in (sensor.y - distance)..(sensor.y + distance)) {
            for (x in (sensor.x - distance + abs(sensor.y - ROW))..(sensor.x + distance - abs(sensor.y - ROW))) {
                invalidPositions.add(Position(x, ROW))
            }
        }
    }
    val part1 = invalidPositions.size - beacons.size - sensors.size
    println("Solution part 1 : $part1")

    val range = 0 .. TUNING_FREQUENCY_MULTIPLIER
    val distress = sensorsRadius.flatMap { (sensor, distance) ->
        (-distance .. distance).flatMap { xDelta ->
            val yDelta = distance -xDelta.absoluteValue
            listOf(
                Position(sensor.x + xDelta, sensor.y - yDelta),
                Position(sensor.x + xDelta, sensor.y + yDelta),
            ).filter { (it.x in range && (it.y in range)) }
        }
    }.first { point -> sensorsRadius.all { it.key distance point >= it.value } }

    val part2 = distress.x.toLong() * TUNING_FREQUENCY_MULTIPLIER + distress.y
    println("Solution part 2 : $part2")

}

data class Position(
    val x: Int,
    val y: Int,
) {
    infix fun distance(other: Position) = abs(x - other.x) + abs(y - other.y)
}

private fun String.getSensor(): Position {
    val (x, y) = substringAfter("Sensor at x=")
        .substringBefore(":")
        .split(", y=")
        .map(String::toInt)
    maxX = max(maxX, x)
    minX = min(minX, x)

    maxY = max(maxY, y)
    minY = min(minY, y)
    return Position(x, y)
}

private fun String.getBeacon(): Position {
    val (x, y) = substringAfter("closest beacon is at x=")
        .split(", y=")
        .map(String::toInt)
    minX = min(minX, x)
    maxX = max(maxX, x)

    minY = min(minY, y)
    maxY = max(maxY, y)
    return Position(x, y)
}
