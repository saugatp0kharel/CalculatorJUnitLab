package com.example;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest {

    static Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("[Before All] Calculator Test suite starting... by spokharel");
        calc = new Calculator();
    }

    @BeforeEach
    void beforeEach(TestInfo info) {
        System.out.println("[Before Each] Starting Test: " + info.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo info) {
        System.out.println("[After Each] Finished Test: " + info.getDisplayName());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("[After All] Completed all test invocations by spokharel");
    }

    // add() with @MethodSource
    static Stream<Arguments> addData() {
        return Stream.of(
                Arguments.of(100, 2, 102),
                Arguments.of(100, -2, 98),
                Arguments.of(-100, 2, -98),
                Arguments.of(-100, -2, -102));
    }

    @ParameterizedTest(name = "add({0},{1}) = {2}")
    @MethodSource("addData")
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    // subtract() with @CsvSource
    @ParameterizedTest(name = "subtract({0},{1}) = {2}")
    @CsvSource({
            "100, 2, 98",
            "100, -2, 102",
            "-100, 2, -102",
            "-100, -2, -98"
    })
    void testSubtract(int a, int b, int expected) {
        assertEquals(expected, calc.subtract(a, b));
    }

    // multiple() with @CsvFileSource
    @ParameterizedTest(name = "multiply({0},{1}) = {2}")
    @CsvFileSource(resources = "/multiply-data.csv", numLinesToSkip = 1)
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calc.multiple(a, b));
    }

    // divide() negative test
    @Test
    void testDivideByZero() {
        IllegalArgumentException ex =
            assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
        assertTrue(ex.getMessage().toLowerCase().contains("zero"));
    }
}
