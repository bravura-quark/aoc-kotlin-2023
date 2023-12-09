import java.util.Stack

fun main() {
    fun nextItem(list: List<Int>): Int {
        // Recursive solution
        if (list.all { it == 0 }) return 0
        val diffs = list.windowed(2).map { it[1] - it[0] }
        return list.last() + nextItem(diffs)

        // Iterative solution
//        val stack = Stack<List<Int>>()
//        stack.push(list)
//        var elements = list
//        while(!elements.all { it == 0 }) {
//            elements = elements.windowed(2).map { it[1] - it[0] }
//            stack.push(elements)
//        }
//        val element = stack.fold(0) {acc, lst ->
//            acc + lst.last()}
//        return element
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { str -> nextItem(str.split("\\s+".toRegex()).map { it.toInt() }) }
    }
    fun part2(input: List<String>): Int {
        return input.sumOf { str -> nextItem(str.split("\\s+".toRegex()).map { it.toInt() }.reversed()) }
    }
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)
    println("Testing on prod inputs")
    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}