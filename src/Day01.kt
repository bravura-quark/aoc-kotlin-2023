fun main() {
    fun extractFirstAndLastDigitAsNum(str: String): Int {
        val firstDigit = str.first { it.isDigit() }
        val lastDigit = str.last { it.isDigit() }
        return "${firstDigit}${lastDigit}".toInt()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { extractFirstAndLastDigitAsNum(it) }
    }

    fun extractDigit(str: String, range: Iterable<Int>): Int {
        val validDigits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        for (i in range) {
            val anyValidDigit = validDigits.keys.filter { str.substring(i).startsWith(it) }
            if (str[i].isDigit() || anyValidDigit.isNotEmpty()) {
                return if (str[i].isDigit()) str[i].digitToInt()
                else validDigits[anyValidDigit[0]]!!
            }
        }
        return 0
    }

    fun extractFirstAndLastDigitWithStringDigits(str: String): Int {
        val firstDigit: Int = extractDigit(str, str.indices)
        val lastDigit: Int = extractDigit(str, str.indices.reversed())

        return "${firstDigit}${lastDigit}".toInt()
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { extractFirstAndLastDigitWithStringDigits(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val part2TestInput = readInput("Day01_part2_test")
    check(part2(part2TestInput) == 281)
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
