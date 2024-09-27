package com.jguerra47;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnitTest {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each test");
    }

    @Test
    @DisplayName("Add operation")
    public void addTest() {
        Calculadora calc = new Calculadora();
        assertEquals(4, calc.add(2, 2));
    }

    @Test
    @DisplayName("Sub operation")
    public void subTest() {
        Calculadora calc = new Calculadora();
        assertEquals(0, calc.sub(2, 2));
    }

}
