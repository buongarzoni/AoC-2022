import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val ROW = 2_000_000

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
    val sensors = mutableSetOf<Position>()
    val beacons = mutableSetOf<Position>()
    val cantExist = mutableSetOf<Position>()

    lines.forEach { line ->
        val sensor = line.getSensor()
        val beacon = line.getBeacon()
        if (sensor.y == ROW) {
            sensors.add(sensor)
        }
        if (beacon.y == ROW) {
            beacons.add(beacon)
        }
        val distance = sensor distance beacon
        if (ROW in (sensor.y - distance)..(sensor.y + distance)) {
            for (x in (sensor.x - distance + abs(sensor.y - ROW))..(sensor.x + distance - abs(sensor.y - ROW))) {
                cantExist.add(Position(x, ROW))
            }
        }
    }

    println(cantExist.size - beacons.size - sensors.size)

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
