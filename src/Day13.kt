fun solveDay13() {
    val lines = readInput("Day13")
    solve(lines)
}
private fun solve(
    input: List<String>,
) {
    val packets = input
        .chunked(3)
        .flatMap { line ->
            line
                .take(2)
                .map { it.parsePacketData() } }
    val part1 = packets
        .chunked(2)
        .mapIndexed { zeroIndex, (first, second) ->
            if (first < second) zeroIndex + 1 else 0
        }.sum()
    println("Solution part 1 : $part1")

    val firstDivider = "[[2]]".parsePacketData()
    val secondDivider = "[[6]]".parsePacketData()
    val sortedPackets = (packets + listOf(firstDivider, secondDivider)).sorted()
    val part2 = (sortedPackets.indexOf(firstDivider) + 1) * (sortedPackets.indexOf(secondDivider) + 1)
    println("Solution part 2 : $part2")
}

sealed interface PacketData : Comparable<PacketData>

@JvmInline
value class Values(val values: List<PacketData>) : PacketData {
    override operator fun compareTo(other: PacketData): Int = when (other) {
        is Values -> values.zip(other.values).asSequence()
            .map { (a, b) -> a.compareTo(b) }
            .firstOrNull { it != 0 }
            ?: values.size.compareTo(other.values.size)
        else -> compareTo(Values(listOf(other)))
    }
}

@JvmInline
value class Value(val int: Int) : PacketData {
    override operator fun compareTo(other: PacketData) = when (other) {
        is Value -> int.compareTo(other.int)
        else -> Values(listOf(this)).compareTo(other)
    }
}

fun String.parsePacketData(): PacketData {
    if (!startsWith("[")) return Value(toInt())
    val innerData = mutableListOf<PacketData>()
    val remaining = ArrayDeque(drop(1).toList())
    while (remaining.size > 1) {
        val innerLine = mutableListOf<Char>()
        var braceCounter = 0
        while (true) {
            val character = remaining.removeFirst()
            when (character) {
                '[' -> braceCounter++
                ']' -> {
                    braceCounter--
                    if (braceCounter < 0) break
                }
                ',' -> if (braceCounter == 0) break
            }
            innerLine.add(character)
        }
        innerData.add(innerLine.joinToString("").parsePacketData())
    }
    return Values(innerData)
}
