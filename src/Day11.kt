fun solveDay11() {
    val input = readInputString("Day11")
    val monkeys = parseMonkeys(input)

    solve(
        monkeys = parseMonkeys(input),
        rounds = 20,
        message = "Solution for part 1: ",
        worryLevelOperation = { it / 3 },
    )

    val gcd = monkeys.map { it.testDivisibleBy }.reduce { acc, l -> acc*l }
    solve(
        monkeys = parseMonkeys(input),
        rounds = 10_000,
        message = "Solution for part 2: ",
        worryLevelOperation = { it % gcd },
    )
}

private fun solve(
    monkeys: List<Monkey>,
    rounds: Int,
    message: String,
    worryLevelOperation: (Long) -> Long,
) {
    repeat(rounds) {
        for (monkey in monkeys) {
            while (monkey.items.isNotEmpty()) {
                monkey.inspectedItems++
                val item = monkey.items.removeFirst()
                val operateWith = if (monkey.operationNumber == "old") item else monkey.operationNumber.toLong()
                val result = when(monkey.operation) {
                    "*" -> worryLevelOperation(item * operateWith)
                    "+" -> worryLevelOperation(item + operateWith)
                    else -> error("unexpected operation $operateWith")
                }
                if (result % monkey.testDivisibleBy == 0L) {
                    monkeys[monkey.ifTrueThrowTo].items.add(result)
                } else {
                    monkeys[monkey.ifFalseThrowTo].items.add(result)
                }
            }
        }
    }
    val sortedMonkeys = monkeys.sortedByDescending { it.inspectedItems }
    val monkeyBusiness = sortedMonkeys[0].inspectedItems * sortedMonkeys[1].inspectedItems
    println(message + monkeyBusiness)
}

private fun parseMonkeys(input: String) = input.split("\n\n").map { monkeyInput ->
    val monkey = Monkey()
    val lines = monkeyInput.split("\n")
    lines.forEach { line ->
        if (line.contains("Monkey")) {
            val monkeyNumber = line.substringAfter("Monkey ").removeSuffix(":").toInt()
            monkey.number = monkeyNumber
        } else if (line.contains("Starting")) {
            val startingItems = line.substringAfter("Starting items: ").split(", ")
            startingItems.forEach { monkey.items.add(it.toLong()) }
        } else if (line.contains("Operation")) {
            val (operation, number) = line.substringAfter("Operation: new = old ").split(" ")
            monkey.operation = operation
            monkey.operationNumber = number
        } else if (line.contains("Test")) {
            val divisibleBy = line.substringAfter("Test: divisible by ").toLong()
            monkey.testDivisibleBy = divisibleBy
        } else if (line.contains("If true")) {
            val toMonkey = line.substringAfter("If true: throw to monkey ").toInt()
            monkey.ifTrueThrowTo = toMonkey
        } else if (line.contains("If false")) {
            val toMonkey = line.substringAfter("If false: throw to monkey ").toInt()
            monkey.ifFalseThrowTo = toMonkey
        }
    }
    monkey
}

data class Monkey(
    var number: Int = -1,
    var inspectedItems: Long = 0,
    val items: MutableList<Long> = mutableListOf(),
    var operation: String = "",
    var operationNumber: String = "",
    var testDivisibleBy: Long = 0,
    var ifTrueThrowTo: Int = 0,
    var ifFalseThrowTo: Int = 0,
)
