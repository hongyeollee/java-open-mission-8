package main.constant

class Constant {
    val INPUT_REGURAL_PREFIX = "//"
    val INPUT_BASIC_REGEX = Regex("[,:]")
    val INPUT_CUSTOM_REGEX = Regex("^//(.+?)(\\r?\\n)(.*)\$")
}