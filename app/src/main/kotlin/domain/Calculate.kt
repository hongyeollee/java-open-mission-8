package main.domain

class Calculate {

    fun sum(input: String): Int {
        val numbers: IntArray = Convert().toNumberArray(input)
        return numbers.sum()
    }
}