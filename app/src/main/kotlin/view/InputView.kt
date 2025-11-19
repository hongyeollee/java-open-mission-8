package main.view

import main.domain.Convert
import java.util.*

class InputView {
    fun inputString(): String {
        println("덧셈할 문자열을 입력하세요.")
        val sc = Scanner(System.`in`)
        val inputString = sc.nextLine()
        val processInput = Convert().processInput(inputString)
        return processInput
    }


}