import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {
    fun solveQuadraticEquation(a: Double, b: Double, c: Double): List<Double> {
        val determinant = sqrt(b * b - 4 * a * c)
        val root1 = (-b - determinant) / (2 * a)
        val root2 = (-b + determinant) / (2 * a)
        return listOf(root1, root2)
    }

    fun part1(input: List<String>): Int {
        val times = input[0].split(":")[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        val distances = input[1].split(":")[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        val solutions = times.indices.map { index ->
            val roots = solveQuadraticEquation(1.0, -times[index].toDouble(), distances[index].toDouble())
            val smallRoot = floor(roots[0] + 1)
            val bigRoot = ceil(roots[1] - 1)
            bigRoot - smallRoot + 1
        }
        return solutions.reduce { a, b -> a * b }.toInt()
    }

    fun part2(input: List<String>): Int {
        val time = input[0].split(":")[1].trim().replace(" ", "").toDouble()
        val distance = input[1].split(":")[1].trim().replace(" ", "").toDouble()
        val roots = solveQuadraticEquation(1.0, -time, distance)
        val smallRoot = floor(roots[0] + 1)
        val bigRoot = ceil(roots[1] - 1)
        return (bigRoot - smallRoot + 1).toInt()
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503)
    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}