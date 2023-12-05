import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    data class SourceDestinationRange(val source: Long, val destination: Long, val range: Long);
    fun readAllData(name: String) = Path("src/$name.txt").readText()
    fun getSourceToDestination(input: List<String>): List<SourceDestinationRange> {
        val map = mutableMapOf<Long, Long>()
        val sourceDestinationRanges = mutableListOf<SourceDestinationRange>()
        (1..<input.size).forEach { index ->
            val rangeAndValue = input[index].trim().split("\\s+".toRegex()).map { it.toLong() }
            val sourceStart = rangeAndValue[1]
            val destinationStart = rangeAndValue[0]
            val range = rangeAndValue[2]
//            (0..<range).forEach{
//                map[sourceStart+it] = destinationStart+it
//            }
            sourceDestinationRanges.add(SourceDestinationRange(sourceStart, destinationStart, range))
        }
//        return map
        return sourceDestinationRanges
    }

    fun getDestination(ranges : List<SourceDestinationRange>, source: Long): Long {
        val availableRange = ranges.filter { it.source<=source && source <= it.source+it.range-1 }
        return if (availableRange.isEmpty()) source
        else availableRange.map {
            it.destination + source-it.source
        }[0]
    }
    fun processData(input: List<String>): Map<Long,Long> {
        val seedToSoil = mutableMapOf<Long,Long>()
        val seedToSoilRange = mutableListOf<SourceDestinationRange>()
        val seedList = mutableListOf<Long>()

        val soilToFertilizer = mutableMapOf<Long,Long>()
        val soilToFertilizerRange = mutableListOf<SourceDestinationRange>()

        val fertilizerToWater = mutableMapOf<Long,Long>()
        val fertilizerToWaterRange = mutableListOf<SourceDestinationRange>()

        val waterToLight = mutableMapOf<Long,Long>()
        val waterToLightRange = mutableListOf<SourceDestinationRange>()

        val lightToTemperature =mutableMapOf<Long,Long>()
        val lightToTemperatureRange = mutableListOf<SourceDestinationRange>()

        val temperatureToHumidity = mutableMapOf<Long,Long>()
        val temperatureToHumidityRange = mutableListOf<SourceDestinationRange>()

        val humidityToLocation = mutableMapOf<Long,Long>()
        val humidityToLocationRange = mutableListOf<SourceDestinationRange>()


        input.forEach { line ->
            when {
                line.startsWith("seeds") ->
                    seedList.addAll(line.split(":")[1].trim().split(" ").map { it.toLong() })
                line.startsWith("seed-to-soil map") ->
                    getSourceToDestination(line.split("\n")).forEach {
                        seedToSoilRange.add(it)
                        //seedToSoil[it.key] = it.value
                    }
                line.startsWith("soil-to-fertilizer map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        soilToFertilizer[it.key] = it.value
                        soilToFertilizerRange.add(it)
                    }
                line.startsWith("fertilizer-to-water map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        fertilizerToWater[it.key] = it.value
                        fertilizerToWaterRange.add(it)
                    }
                line.startsWith("water-to-light map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        waterToLight[it.key] = it.value
                        waterToLightRange.add(it)
                    }
                line.startsWith("light-to-temperature map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        lightToTemperature[it.key] = it.value
                        lightToTemperatureRange.add(it)
                    }
                line.startsWith("temperature-to-humidity map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        temperatureToHumidity[it.key] = it.value
                        temperatureToHumidityRange.add(it)
                    }
                line.startsWith("humidity-to-location map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        humidityToLocation[it.key] = it.value
                        humidityToLocationRange.add(it)
                    }
            }
        }
        return seedList.associateWith { seed ->
            val soil = getDestination(seedToSoilRange, seed) //seedToSoil[seed] ?: seed
            val fertilizer =getDestination(soilToFertilizerRange, soil) // soilToFertilizer[soil] ?: soil
            val water = getDestination(fertilizerToWaterRange, fertilizer) // fertilizerToWater[fertilizer] ?: fertilizer
            val light = getDestination(waterToLightRange, water) //waterToLight[water] ?: water
            val temperature = getDestination(lightToTemperatureRange, light) // lightToTemperature[light] ?: light
            val humidity = getDestination(temperatureToHumidityRange, temperature) // temperatureToHumidity[temperature] ?: temperature
            val location = getDestination(humidityToLocationRange, humidity) // humidityToLocation[humidity] ?: humidity
            location
        }
    }
    fun part2(input: List<String>): Long {
        val seedToSoil = mutableMapOf<Long, Long>()
        val seedToSoilRange = mutableListOf<SourceDestinationRange>()
        val seedList = mutableListOf<Long>()

        val soilToFertilizer = mutableMapOf<Long, Long>()
        val soilToFertilizerRange = mutableListOf<SourceDestinationRange>()

        val fertilizerToWater = mutableMapOf<Long, Long>()
        val fertilizerToWaterRange = mutableListOf<SourceDestinationRange>()

        val waterToLight = mutableMapOf<Long, Long>()
        val waterToLightRange = mutableListOf<SourceDestinationRange>()

        val lightToTemperature = mutableMapOf<Long, Long>()
        val lightToTemperatureRange = mutableListOf<SourceDestinationRange>()

        val temperatureToHumidity = mutableMapOf<Long, Long>()
        val temperatureToHumidityRange = mutableListOf<SourceDestinationRange>()

        val humidityToLocation = mutableMapOf<Long, Long>()
        val humidityToLocationRange = mutableListOf<SourceDestinationRange>()


        input.forEach { line ->
            when {
                line.startsWith("seeds") ->
                    seedList.addAll(line.split(":")[1].trim().split(" ").map { it.toLong() })

                line.startsWith("seed-to-soil map") ->
                    getSourceToDestination(line.split("\n")).forEach {
                        seedToSoilRange.add(it)
                        //seedToSoil[it.key] = it.value
                    }

                line.startsWith("soil-to-fertilizer map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        soilToFertilizer[it.key] = it.value
                        soilToFertilizerRange.add(it)
                    }

                line.startsWith("fertilizer-to-water map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        fertilizerToWater[it.key] = it.value
                        fertilizerToWaterRange.add(it)
                    }

                line.startsWith("water-to-light map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        waterToLight[it.key] = it.value
                        waterToLightRange.add(it)
                    }

                line.startsWith("light-to-temperature map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        lightToTemperature[it.key] = it.value
                        lightToTemperatureRange.add(it)
                    }

                line.startsWith("temperature-to-humidity map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        temperatureToHumidity[it.key] = it.value
                        temperatureToHumidityRange.add(it)
                    }

                line.startsWith("humidity-to-location map") ->
                    getSourceToDestination(line.split("\n")).forEach {
//                        humidityToLocation[it.key] = it.value
                        humidityToLocationRange.add(it)
                    }
            }
        }

        return (0..<seedList.size step 2).minOf {
            val seedRange = seedList[it]..<seedList[it]+seedList[it+1]
            seedRange.minOf { seed ->
                val soil = getDestination(seedToSoilRange, seed) //seedToSoil[seed] ?: seed
                val fertilizer = getDestination(soilToFertilizerRange, soil) // soilToFertilizer[soil] ?: soil
                val water =
                    getDestination(fertilizerToWaterRange, fertilizer) // fertilizerToWater[fertilizer] ?: fertilizer
                val light = getDestination(waterToLightRange, water) //waterToLight[water] ?: water
                val temperature = getDestination(lightToTemperatureRange, light) // lightToTemperature[light] ?: light
                val humidity = getDestination(
                    temperatureToHumidityRange,
                    temperature
                ) // temperatureToHumidity[temperature] ?: temperature
                val location =
                    getDestination(humidityToLocationRange, humidity) // humidityToLocation[humidity] ?: humidity
                location?:0L
            }
        }
    }
    fun part1(input: List<String>): Long {
        return processData(input).values.min()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readAllData("Day05_test").split("\n\n".toRegex())
    check(part1(testInput) == 35L)
    check(part2(testInput) == 46L)

    val input = readAllData("Day05").split("\n\n".toRegex())
    part1(input).println()
    part2(input).println()
}