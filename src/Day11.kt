fun solveDay11() {
    val input = readInputString("Day11")
    solve(input)
}

private fun solve(input: String) {
    val monkeysInput = input.split("\n\n")
    val monkeys = mutableListOf<Monkey>()
    monkeysInput.forEach { monkeyInput ->
        val monkey = Monkey()
        val lines = monkeyInput.split("\n")
        lines.forEach { line ->
            if (line.contains("Monkey")) {
                val monkeyNumber = line.substringAfter("Monkey ").removeSuffix(":").toInt()
                monkey.number = monkeyNumber
            } else if (line.contains("Starting")) {
                val startingItems = line.substringAfter("Starting items: ").split(", ")
                startingItems.forEach { monkey.items.add(it.toInt()) }
            } else if (line.contains("Operation")) {
                val (operation, number) = line.substringAfter("Operation: new = old ").split(" ")
                monkey.operation = operation
                monkey.operationNumber = number
            } else if (line.contains("Test")) {
                val divisibleBy = line.substringAfter("Test: divisible by ").toInt()
                monkey.testDivisibleBy = divisibleBy
            } else if (line.contains("If true")) {
                val toMonkey = line.substringAfter("If true: throw to monkey ").toInt()
                monkey.ifTrueThrowTo = toMonkey
            } else if (line.contains("If false")) {
                val toMonkey = line.substringAfter("If false: throw to monkey ").toInt()
                monkey.ifFalseThrowTo = toMonkey
            }

        }
        monkeys.add(monkey)
    }



    monkeys.forEach { monkey -> println(monkey) }



    repeat(20) {
        for (monkey in monkeys) {
            while (monkey.items.isNotEmpty()) {
                monkey.inspectedItems++
                val item = monkey.items.removeFirst()
                val operateWith = if (monkey.operationNumber == "old") item else monkey.operationNumber.toInt()
                val result = when(monkey.operation) {
                    "*" -> (item * operateWith) / 3
                    "/" -> (item / operateWith) / 3
                    "+" -> (item + operateWith) / 3
                    "-" -> (item - operateWith) / 3
                    else -> error("unexpected operation $operateWith")
                }
                if (result % monkey.testDivisibleBy == 0) {
                    monkeys[monkey.ifTrueThrowTo].items.add(result)
                } else {
                    monkeys[monkey.ifFalseThrowTo].items.add(result)
                }
            }
        }
    }

    println()
    monkeys.forEach { monkey -> println(monkey) }
}

data class Monkey(
    var number: Int = -1,
    var inspectedItems: Int = 0,
    val items: MutableList<Int> = mutableListOf(),
    var operation: String = "",
    var operationNumber: String = "",
    var testDivisibleBy: Int = 0,
    var ifTrueThrowTo: Int = 0,
    var ifFalseThrowTo: Int = 0,
)