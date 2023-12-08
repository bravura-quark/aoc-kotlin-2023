fun main() {
    fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    fun listRepeatedSeq(list: List<Char>): Sequence<Char> {
        return generateSequence(Pair(list, 0)) {
            Pair(
                it.first,
                (it.second + 1) % it.first.size
            )
        }.map { it.first[it.second] }
    }

    fun getSourceToDestinationMapping(input: List<String>): Map<String, List<String>> {
        return (2..<input.size).associate { index ->
            val src = input[index].split("=")[0].trim()
            val destinations = input[index].split("=")[1]
                .replace("(", "")
                .replace(")", "")
                .split(",")
                .map { it.trim() }
            src to destinations
        }
    }

    fun part1(input: List<String>): Int {
        val leftRightDirections = input[0].trim().toCharArray().toList()
        val charIterator = listRepeatedSeq(leftRightDirections).iterator()

        val mapping = getSourceToDestinationMapping(input)
        var count = 0
        var src = "AAA"
        val destination = "ZZZ"
        while (src != destination) {
            val direction = charIterator.next()
            val destinations = mapping[src]!!

            src = if (direction == 'L') {
                destinations[0]
            } else {
                destinations[1]
            }
            count++
        }
        return count
    }

    fun part2(input: List<String>): Long {
        val leftRightDirections = input[0].trim().toCharArray().toList()
        val mapping = getSourceToDestinationMapping(input)
        val sources = mapping.keys.filter { it.endsWith("A") }

        return sources.map {
            var cnt = 0
            var src = it
            val charIterator = listRepeatedSeq(leftRightDirections).iterator()
            while (!src.endsWith("Z")) {
                val direction = charIterator.next()
                val destinations = mapping[src]!!

                src = if (direction == 'L') {
                    destinations[0]
                } else {
                    destinations[1]
                }
                cnt++
            }
            cnt.toLong()
        }.reduce { a, b -> findLCM(a, b) }
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 6)
    val testPart2Input = readInput("Day08_part2")
    check(part2(testPart2Input) == 6L)
    println("Testing on prod inputs")
    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}