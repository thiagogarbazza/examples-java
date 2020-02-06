package com.github.thiagogarbazza.examples.junitjupiter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ExampleJunit5UnitTest {

  @AfterEach
  void afterEach() {
    System.out.println("  Unit-test after each");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("  Unit-test before each");
  }

  @Test
  void verify01() {
    System.out.println("    Unit-test 01");
  }

  @Test
  void verify02() {
    System.out.println("    Unit-test 02");
  }

  @Test
  @Disabled
  void verify03() {
    Assertions.fail("    Unit-test 03");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("Unit-test after all");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("Unit-test before all");
  }
}
