// Combined JUnit Advanced Exercises
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

// ================================
// Exercise 1: Parameterized Tests
// ================================
class EvenChecker {
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}

class EvenCheckerTest {
    EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    public void testIsEvenWithEvenNumbers(int input) {
        assertTrue(checker.isEven(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9})
    public void testIsEvenWithOddNumbers(int input) {
        assertFalse(checker.isEven(input));
    }
}

// ================================
// Exercise 2: Test Suites and Categories
// ================================
class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
}

class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calc.subtract(4, 3));
    }
}

@Suite
@SelectClasses({
    CalculatorTest.class,
    EvenCheckerTest.class
})
class AllTests {
}

// ================================
// Exercise 3: Test Execution Order
// ================================
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    @Test
    @Order(1)
    public void testA() {
        System.out.println("Running testA");
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void testB() {
        System.out.println("Running testB");
        assertTrue(true);
    }

    @Test
    @Order(3)
    public void testC() {
        System.out.println("Running testC");
        assertTrue(true);
    }
}

// ================================
// Exercise 4: Exception Testing
// ================================
class ExceptionThrower {
    public void throwException(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }
}

class ExceptionThrowerTest {
    ExceptionThrower thrower = new ExceptionThrower();

    @Test
    public void testExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwException(null);
        });
    }

    @Test
    public void testNoException() {
        assertDoesNotThrow(() -> {
            thrower.throwException("Not null");
        });
    }
}

// ================================
// Exercise 5: Timeout and Performance Testing
// ================================
class PerformanceTester {
    public void performTask() throws InterruptedException {
        Thread.sleep(100); // Simulates task
    }
}

class PerformanceTesterTest {
    PerformanceTester tester = new PerformanceTester();

    @Test
    public void testPerformance() {
        assertTimeout(Duration.ofMillis(200), () -> {
            tester.performTask();
        });
    }
}
