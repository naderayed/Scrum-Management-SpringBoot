package com.nader.scrum.management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class ApplicationTests {
    Calculater underTest = new Calculater();

    @Test
        //@Test is JUnit
    void itShouldAddTwoNumbers() {

        //given
        int a = 30;
        int b = 20;
        //when
        int result = underTest.add(a, b);
        //then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    class Calculater {
        int add(int a, int b) {
            return a + b;
        }
    }
}
