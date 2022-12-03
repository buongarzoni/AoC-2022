fun solveDay03() {
    val result = readInput("Day03").mapNotNull { it.getItem() }.sum()
    println(result)

    val result2 = readInput("Day03")
        .chunked(3)
        .mapNotNull { it.getChunkPoints() }
        .sum()
    println(result2)
}

private fun List<String>.getChunkPoints(): Int? {
    for (character in component1()) {
        if (component2().contains(character) && component3().contains(character)) {
            return if (character.isLowerCase()) {
                character - 'a' + 1
            } else {
                character - 'A' + 27
            }
        }
    }
    return null
}

private fun String.getItem(): Int? {
    val part1 = substring(0, length / 2)
    val part2 = substring(length / 2, length)
    println("$part1 $part2")
    for (character in part1) {
        if (part2.contains(character)) {
            return if (character.isLowerCase()) {
                character - 'a' + 1
            } else {
                character - 'A' + 27
            }
        }
    }
    return null
}

