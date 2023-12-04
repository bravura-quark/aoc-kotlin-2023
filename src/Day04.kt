import kotlin.math.pow

fun main() {
    fun part2(input: List<String>): Int {
        val cardCounts = (1..input.size).associateWith { 1 }.toMutableMap()
        return input.sumOf { str ->
            val cards = str.split(":")[1].split("|")
            val cardNumber = str.split(":")[0].split("\\s+".toRegex())[1].trim().toInt()
            val winningCards = cards[0].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
            val myCards = cards[1].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
            val winningSize = winningCards.intersect(myCards).size
            (1..winningSize).forEach { cardCounts[cardNumber + it] = (cardCounts[cardNumber + it] ?:0) + 1*cardCounts[cardNumber]!! }
            cardCounts[cardNumber] ?: 0
        }
    }
    fun part1(input: List<String>): Int {
        return input.sumOf { str ->
            val cards = str.split(":")[1].split("|")
            val winningCards = cards[0].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
            val myCards = cards[1].trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
            val winningNumbers = winningCards.intersect(myCards)
            if (winningNumbers.isEmpty()) 0
            else 2.0.pow((winningNumbers.size - 1).toDouble()).toInt()
        }
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}