fun main() {
    val testInput = readInput("Day01_test")

    val carriedCaloriesPerElve: List<List<String>> = testInput.toCarriedCaloriesPerElve()
    val caloriesIntakePerElve = carriedCaloriesPerElve.toCaloriesIntakePerElve()
    val maxCalorieIntake = caloriesIntakePerElve.max()
    println("Max calorie intake of an Elve: $maxCalorieIntake")
    val top3Sum = caloriesIntakePerElve.sortedDescending().take(3).sum()
    println("Sum of top 3 total calories : $top3Sum")
}

fun List<String>.toCarriedCaloriesPerElve(): List<List<String>> = fold(emptyList()) { acc, s ->
    if(acc.isEmpty()) {
        listOf(listOf(s))
    }else if(s.isBlank()) {
        acc + listOf(listOf())
    } else {
        acc.take(acc.size - 1) + listOf(acc.last() + s)
    }
}

fun List<List<String>>.toCaloriesIntakePerElve() = map { calories ->
    calories.fold(0) { acc, s ->
        s.toInt() + acc
    }
}
