fun solveDay03() {
    val result = readInput("Day03").mapNotNull { it.getItem() }.sum()
    println(result)

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

