package main.domain

import main.constant.Constant

class Convert {
    val constant = Constant()

    fun processInput(value: String): String {
        return value.ifBlank { "0" }
    }

    fun toNumberArray(input: String): IntArray {
        val toArrayInput = splitSeparator(input)
        if (toArrayInput.isEmpty()) return IntArray(0)

        val result = IntArray(toArrayInput.size)
        for (i in toArrayInput.indices) {
            val value = toArrayInput[i].trim()
            val number = value.toIntOrNull()
                ?: throw IllegalArgumentException("잘못된 구분자 사용이 있습니다.: '$value'")
            if (number < 0) throw IllegalArgumentException("음수는 허용되지 않습니다.")
            result[i] = number
        }
        return result
    }

    private fun splitSeparator(input: String): Array<String> {
        if (input.isBlank()) return emptyArray()
        return if (input.startsWith(constant.INPUT_REGURAL_PREFIX)) customStarter(input)
        else input.split(constant.INPUT_BASIC_REGEX).toTypedArray()
    }

    private fun customStarter(input: String): Array<String> {
        if (!input.startsWith(constant.INPUT_REGURAL_PREFIX)) throw IllegalArgumentException("커스컴 구분자 사용이 //로 시작되지 않습니다.")
        val normalized = normalizeNewline(input)
        val match = constant.INPUT_CUSTOM_REGEX
            .matchEntire(normalized) ?: throw IllegalArgumentException("커스텀 구분자에 적합하지 않습니다.")
        val customHeader = match.groupValues[1]
        val numbers = match.groupValues[3]
        if (customHeader.length != 1) throw IllegalArgumentException("커스텀 구분자는 하나의 기호로만 설정 가능합니다.")
        if (numbers.isBlank()) return emptyArray()
        if (!numbers.contains(customHeader)) throw IllegalArgumentException("구분자 사용이 정상적으로 사용되지 않았습니다.")
        return numbers.split(Regex(Regex.escape(customHeader))).toTypedArray()
    }

    private fun normalizeNewline(s: String): String {
        return s
            .replace("\\r\\n", "\r\n")
            .replace("\\n", "\n")
    }
}