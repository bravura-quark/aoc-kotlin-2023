fun main() {

    fun getCardRank(card: String): Int {
        return when (card) {
            "A" -> 14
            "K" -> 13
            "Q" -> 12
            "J" -> 11
            "T" -> 10
            else -> card.toInt()
        }
    }

    fun getCardRankWithJoker(card: String): Int {
        return when (card) {
            "A" -> 14
            "K" -> 13
            "Q" -> 12
            "J" -> 1
            "T" -> 10
            else -> card.toInt()
        }
    }

    fun getHandRank(hand: String): Int {
        val frequencies = hand.groupBy { it }.map { entry -> entry.key to entry.value.size }.toMap().values.sorted()
            .joinToString(separator = "")
        return when (frequencies) {
            "11111" -> 1
            "1112" -> 2
            "122" -> 3
            "113" -> 4
            "23" -> 5
            "14" -> 6
            else -> 7
        }
    }

    fun getHandRankWithJoker(hand: String): Int {
        val frequencies = hand.groupBy { it }.map { entry -> entry.key to entry.value.size }.toMap()
        val sortedFreq = frequencies.values.sorted().joinToString(separator = "")
        if (frequencies['J'] != null) {
            val jFreq = frequencies['J']
            return when (Pair(sortedFreq, jFreq)) {
                Pair("11111", 1) -> 2
                Pair("1112", 1) -> 4
                Pair("1112", 2) -> 4
                Pair("122", 1) -> 5 // 23
                Pair("122", 2) -> 6 // 14
                Pair("113", 1) -> 6 // 14
                Pair("113", 3) -> 6 // 14
                else -> 7
            }
        }
        return when (sortedFreq) {
            "11111" -> 1
            "1112" -> 2
            "122" -> 3
            "113" -> 4
            "23" -> 5
            "14" -> 6
            else -> 7
        }
    }

    val cardComparator = Comparator<String> { card1, card2 ->
        getCardRank(card1).compareTo(getCardRank(card2))
    }
    val handComparator = Comparator<List<String>> { hand1Input, hand2Input ->
        val hand1 = hand1Input[0]
        val hand2 = hand2Input[0]
        val handRank1 = getHandRank(hand1)
        val handRank2 = getHandRank(hand2)
        if (handRank1 == handRank2) {
            val index = hand1.indices.dropWhile { hand1[it] == hand2[it] }[0]
            cardComparator.compare(hand1[index].toString(), hand2[index].toString())
        } else {
            handRank1.compareTo(handRank2)
        }
    }

    val cardComparatorWithJoker = Comparator<String> { card1, card2 ->
        getCardRankWithJoker(card1).compareTo(getCardRankWithJoker(card2))
    }
    val handComparatorWithJoker = Comparator<List<String>> { hand1Input, hand2Input ->
        val hand1 = hand1Input[0]
        val hand2 = hand2Input[0]
        val handRank1 = getHandRankWithJoker(hand1)
        val handRank2 = getHandRankWithJoker(hand2)
        if (handRank1 == handRank2) {
            val index = hand1.indices.dropWhile { hand1[it] == hand2[it] }[0]
            cardComparatorWithJoker.compare(hand1[index].toString(), hand2[index].toString())
        } else {
            handRank1.compareTo(handRank2)
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(" ") }.sortedWith(handComparator)
            .mapIndexed { index, strings ->
                (index + 1) * strings[1].trim().toInt()
            }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" ") }.sortedWith(handComparatorWithJoker)
            .mapIndexed { index, strings ->
                (index + 1) * strings[1].trim().toInt()
            }.sum()
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 5905)
    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}