package main.controller

import main.domain.Calculate
import main.view.InputView
import main.view.OutputView

class Controller {
    val inputView = InputView()
    val outputView = OutputView()
    val calculate = Calculate()

    fun run () {
        val input = inputView.inputString()
        val result = calculate.sum(input)
        outputView.result(result)
    }


}