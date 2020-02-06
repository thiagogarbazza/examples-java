package com.github.thiagogarbazza.examples.junitjupiter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ExampleJunit5AcceptanceTest {

  @AfterEach
  void afterEach() {
    System.out.println("  Acceptance-test after each");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("  Acceptance-test before each");
  }

  @Test
  void verify01() {
    System.out.println("    Acceptance-test 01");
  }

  @Test
  void verify02() {
    System.out.println("    Acceptance-test 02");
  }

  @Test
  @Disabled
  void verify03() {
    Assertions.fail("    Acceptance-test 03");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("Acceptance-test after all");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("Acceptance-test before all");
  }
}
