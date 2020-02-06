package com.github.thiagogarbazza.examples.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleJunit4IntegrationTest {

  @After
  public void after() {
    System.out.println("  Integration-test after each");
  }

  @Before
  public  void before() {
    System.out.println("  Integration-test before each");
  }

  @Test
  public void verify01() {
    System.out.println("    Integration-test 01");
  }

  @Test
  public void verify02() {
    System.out.println("    Integration-test 02");
  }

  @Test
  @Ignore
  public void verify03() {
    Assert.fail("    Integration-test 03");
  }

  @AfterClass
  public static void afterClass() {
    System.out.println("Integration-test after all");
  }

  @BeforeClass
  public static void beforeClass() {
    System.out.println("Integration-test before all");
  }
}
