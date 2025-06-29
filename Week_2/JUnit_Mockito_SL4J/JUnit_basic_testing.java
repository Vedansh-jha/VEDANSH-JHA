// File: JUnitExercises.java

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Exercise 2: Basic Calculator class
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}

// Exercise 4: MathUtils class for AAA pattern and setup/teardown
class MathUtils {
    public int multiply(int a, int b) {
        return a * b;
    }
}

public class JUnitExercises {

    private MathUtils mathUtils;

    // Exercise 3: Assertions Test
    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);
        assertTrue(5 > 3);
        assertFalse(5 < 3);
        assertNull(null);
        assertNotNull(new Object());
    }

    // Exercise 2: Calculator add and subtract tests
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(3, 2);
        assertEquals(5, result);
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        int result = calc.subtract(5, 2);
        assertEquals(3, result);
    }

    // Exercise 4: Setup and Teardown methods
    @Before
    public void setUp() {
        mathUtils = new MathUtils();
        System.out.println("Setting up...");
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down...");
    }

    @Test
    public void testMultiply() {
        // Arrange
        int a = 4, b = 5;

        // Act
        int result = mathUtils.multiply(a, b);

        // Assert
        assertEquals(20, result);
    }
}
