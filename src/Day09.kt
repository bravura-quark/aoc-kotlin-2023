import java.util.Stack

fun main() {
    fun part1NextItem(list: List<Int>): Int {
        val stack = Stack<List<Int>>()
        stack.push(list)
        var elements = list
        while(!elements.all { it == 0 }) {
            elements = elements.windowed(2).map { it[1] - it[0] }
            stack.push(elements)
        }
        var nextElement = 0
        while (!stack.empty()) {
            nextElement += stack.pop()!!.last()
        }
        return nextElement
    }
    fun part2NextItem(list: List<Int>): Int {
        val stack = Stack<List<Int>>()
        stack.push(list)
        var elements = list
        while(!elements.all { it == 0 }) {
            elements = elements.windowed(2).map { it[1] - it[0] }
            stack.push(elements)
        }
        var nextElement = 0
        while (!stack.empty()) {
            nextElement = stack.pop()!!.first() - nextElement
        }
        return nextElement
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { str -> part1NextItem(str.split("\\s+".toRegex()).map { it.toInt() }) }
    }
    fun part2(input: List<String>): Int {
        return input.sumOf { str -> part2NextItem(str.split("\\s+".toRegex()).map { it.toInt() }) }
    }
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)
    println("Testing on prod inputs")
    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}