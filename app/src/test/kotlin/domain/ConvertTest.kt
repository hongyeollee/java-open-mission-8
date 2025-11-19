package domain

import main.domain.Convert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@DisplayName("Convert 클래스 테스트")
class ConvertTest {
    val convert = Convert()

    @DisplayName("processInput 메소드. 문자열을 문자열로 변경해준다. 이때, 빈문자열인 경우는 String타입인 숫자 '0'을 반환한다.")
    @Test
    fun 문자열_컨버팅() {
        val convertString = convert.processInput("1,2:3")
        assertEquals("1,2:3", convertString, "문자열 변환 성공")
        val emptyZeroReturnString = convert.processInput("")
        assertEquals("0", emptyZeroReturnString, "빈문자열인 경우 0으로 반환 성공")
    }

    @DisplayName("toNumberArray 메소드(splitSeparator, customStarter). 빈 문자열은 값을 빈 배열로 반환 성공.")
    @Test
    fun 문자입력값이_없을때_빈배열로_변환() {
        val toNumberArrayCaseEmptyString = convert.toNumberArray("")
        assertContentEquals(intArrayOf(), toNumberArrayCaseEmptyString)
        val toNumberArrayCaseEmptyString1 = convert.toNumberArray("//#\n")
        assertContentEquals(intArrayOf(), toNumberArrayCaseEmptyString1)
    }

    @DisplayName("toNumberArray 메소드. 입력한 문자열의 값을 숫자 배열로 변환한다.")
    @Test
    fun 문자입력값을_숫자배열로_변환() {
        val toNumberArrayCase1 = convert.toNumberArray("1,2,3")
        assertContentEquals(intArrayOf(1,2,3), toNumberArrayCase1, "toNumberArrayCase1 성공")
        val toNumberArrayCase2 = convert.toNumberArray("//#\n4#10#6")
        assertContentEquals(intArrayOf(4,10,6), toNumberArrayCase2,"toNumberArrayCase2 성공")
    }

    @DisplayName("toNumberArray메소드. 입력한 문자열에 음수가 사용되면 예외규칙 적용")
    @Test
    fun 입력값에_음수_입력시_예외규칙_적용() {
        val toNumberArrayExceptionCase1 = assertThrows<IllegalArgumentException>{ convert.toNumberArray("-1,2,3") }
        assertEquals("음수는 허용되지 않습니다.", toNumberArrayExceptionCase1.message)
        val toNumberArrayExceptionCase2 = assertThrows<IllegalArgumentException>{ convert.toNumberArray("//!\n6!100!-30") }
        assertEquals("음수는 허용되지 않습니다.", toNumberArrayExceptionCase2.message)
    }

    @DisplayName("toNumberArray메소드. 구분자 사용이 잘못 사용된 경우 예외규칙 적용")
    @Test
    fun 입력값에_숫자가_아닌_다른값_사용_예외규칙_적용() {
        val toNumberArrayExceptionCase3 = assertThrows<IllegalArgumentException>{convert.toNumberArray("3:r,10")}
        assertEquals("잘못된 구분자 사용이 있습니다.: 'r'", toNumberArrayExceptionCase3.message)
        val toNumberArrayExceptionCase4 = assertThrows<IllegalArgumentException>{convert.toNumberArray("//$\n9$#$2")}
        assertEquals("잘못된 구분자 사용이 있습니다.: '#'", toNumberArrayExceptionCase4.message)
    }

    @DisplayName("toNumberArray메소드. 커스텀 구분자에 두개이상의 구분자를 사용하는 경우 예외규칙 적용")
    @Test
    fun 입력값에_커스텀_구분자_여러개_사용_예외규칙_적용() {
        val toNumberArrayExceptionCase5 = assertThrows<IllegalArgumentException>{convert.toNumberArray("//??\n1?2?3")}
        assertEquals("커스텀 구분자는 하나의 기호로만 설정 가능합니다.", toNumberArrayExceptionCase5.message)
    }

    @DisplayName("toNumberArray메소드. 커스텀 구분자를 사용했지만 입력값에는 구분자를 사용하지 않는 경우 예외규칙 적용")
    @Test
    fun 입력값에_커스텀_구분자_사용하지_않는_예외규칙_적용() {
        val toNumberArrayExceptionCase6 = assertThrows<IllegalArgumentException>{convert.toNumberArray("//?\n1#2#3")}
        assertEquals("구분자 사용이 정상적으로 사용되지 않았습니다.", toNumberArrayExceptionCase6.message)
    }

    @DisplayName("toNumberArray메소드. 커스텀 구분자를 사용이 잘못된 경우 예외규칙 적용")
    @Test
    fun 입력값에_커스텀_구분자_사용이_잘못된_경우_예외규칙_적용() {
        val toNumberArrayExceptionCase7 = assertThrows<IllegalArgumentException>{convert.toNumberArray("//;1,2,3")}
        assertEquals("커스텀 구분자에 적합하지 않습니다.", toNumberArrayExceptionCase7.message)
    }
}