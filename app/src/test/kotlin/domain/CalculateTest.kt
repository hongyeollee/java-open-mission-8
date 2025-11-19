package domain

import main.domain.Calculate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("CalculateTest 클래스 테스트")
class CalculateTest {

    @DisplayName("입력된 값에 대해서 커스텀 케이스가 아닌 베이직 케이스로 총 합을 구한다.")
    @Test
    fun 도출된_숫자의_총합을_계산한다_베이직_케이스() {
        val sumCase1: Int = Calculate().sum("2,3:4")
        assertEquals(9, sumCase1, "베이직 케이스 총 합")
    }

    @DisplayName("calculate(String input) 메소드에서 커스텀 구분자를 통해 변환된 숫자 1,2,3의 총합인 6을 반환한다.")
    @Test
    fun 도출된_숫자의_총합을_계산한다_커스텀케이스() {
        val sumCase2: Int = Calculate().sum("//;\n1;2;3")
        assertEquals(6, sumCase2, "커스텀 케이스 총 합")
    }

    @DisplayName("calculate(String input) 메소드에서 계산하려는 문자열의 값이 빈문자인 경우는 총합인 0을 반환한다.")
    @Test
    fun 기본_구분자와_커스텀_구분자_문자열의_계산값_빈입력_0() {
        val sumCase4: Int = Calculate().sum("")
        assertEquals(0, sumCase4, "기본 구분자의 빈문자열 입력은 0 반환")

        val sumCase5: Int = Calculate().sum("//;\n")
        assertEquals(0, sumCase5,"커스텀 케이스 값 없는 경우 총 합은 0 반환")
    }
}