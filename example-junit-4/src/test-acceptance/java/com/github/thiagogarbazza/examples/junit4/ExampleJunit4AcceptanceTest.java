package com.github.thiagogarbazza.examples.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleJunit4AcceptanceTest {

  @After
  public void after() {
    System.out.println("  Acceptance-test after each");
  }

  @Before
  public void before() {
    System.out.println("  Acceptance-test before each");
  }

  @Test
  public void verify01() {
    System.out.println("    Acceptance-test 01");
  }

  @Test
  public void verify02() {
    System.out.println("    Acceptance-test 02");
  }

  @Test
  @Ignore
  public void verify03() {
    Assert.fail("    Acceptance-test 03");
  }

  @AfterClass
  public static void afterClass() {
    System.out.println("Acceptance-test after all");
  }

  @BeforeClass
  public static void beforeClass() {
    System.out.println("Acceptance-test before all");
  }
}
