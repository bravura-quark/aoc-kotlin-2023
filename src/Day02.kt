import kotlin.math.max

fun main() {
    fun processData(input: List<String>): Map<Int, MutableMap<String, Int>> {
        return input.associate { gameStr ->
            val gameAndDraws = gameStr.split(":")
            val gameId = gameAndDraws[0].split(" ")[1].toInt()
            val draws = gameAndDraws[1].split(";").map { draw ->
                draw.trim().split(",").associate { coloredBalls ->
                    val numColors = coloredBalls.trim().split(" ").map { it.trim() }
                    numColors[1] to numColors[0].toInt()
                }.toMutableMap()
            }.reduce { acc, map ->
                map.forEach { (k, v) ->
                    acc[k] = max(v, acc[k] ?: 0)
                }
                acc
            }
            gameId to draws
        }
    }

    fun part2(input: List<String>): Int {
        return processData(input).map {
            it.value.values.reduce { x, y -> x * y }
        }.sum()
    }

    fun part1(input: List<String>): Int {
        return processData(input).filter { it ->
            val colorCount = it.value
            (colorCount["red"] ?: 0) <= 12 &&
                    (colorCount["green"] ?: 0) <= 13 &&
                    (colorCount["blue"] ?: 0) <= 14
        }.keys.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
