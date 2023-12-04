fun main() {
    fun Pair<Int, Int>.neighbours(): List<Pair<Int, Int>> =
        (-1..1).flatMap { x ->
            (-1..1).map { y ->
                this.first + x to this.second + y
            }
        }

    fun part2(input: List<String>): Int {
        val symbols = input.flatMapIndexed { y, it ->
            it.mapIndexedNotNull { x, c ->
                if (!c.isDigit() && c != '.') (x to y) to c
                else null
            }
        }.toMap()
        val numbers = input.flatMapIndexed { y, line ->
            "(\\d+)".toRegex().findAll(line).map { match ->
                match.range.map { it to y } to match.value.toInt()
            }
        }
        return symbols.filter { (_, v) -> v == '*' }
            .map { (k, _) ->
                numbers.filter { (coords, _) -> k.neighbours().any { it in coords } }
            }.filter { it.count() == 2 }
            .sumOf { (a, b) -> a.second * b.second }

    }

    fun part1(input: List<String>): Int {
        val symbols = input.flatMapIndexed { y, it ->
            it.mapIndexedNotNull { x, c ->
                if (!c.isDigit() && c != '.') (x to y) to c
                else null
            }
        }.toMap()
        val numbers = input.flatMapIndexed { y, line ->
            "(\\d+)".toRegex().findAll(line).map { match ->
                match.range.map { it to y } to match.value.toInt()
            }
        }
        return numbers
            .filter { (coords, _) -> coords.any { n -> n.neighbours().any { it in symbols } } }
            .sumOf { it.second }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}