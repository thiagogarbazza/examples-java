package com.github.thiagogarbazza.examples.xmlwithsax;

import com.github.thiagogarbazza.examples.xmlwithsax.validator.XMLValidationService;
import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import com.github.thiagogarbazza.violationbuilder.ViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("xml-validator")
class XMLValidationIntegrationTest {

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0}")
  @MethodSource("verifyValidateAInvalidXMLBasedAttacksMethodSource")
  void verifyValidateAInvalidXMLBasedAttacks(String fileName, URI uri) {
    try (
      InputStream xml = new FileInputStream(new File(uri));
      InputStream xsd = ResourceGetter.resourceInputStream("example.xsd")
    ) {
      final ViolationBuilder violationBuilder = ViolationBuilder.builder();

      XMLValidationService.validateXMLByXSD(xml, xsd, violationBuilder);

      violationBuilder.build();
      fail("Expected exception.");
    } catch (Exception e) {
      assertTrue(e instanceof ViolationException);
    }
  }

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0}")
  @MethodSource("verifyValidateAInvalidXMLFileMethodSource")
  void verifyValidateAInvalidXMLFile(String fileName, URI uri) {
    try (
      InputStream xml = new FileInputStream(new File(uri));
      InputStream xsd = ResourceGetter.resourceInputStream("example.xsd")
    ) {
      final ViolationBuilder violationBuilder = ViolationBuilder.builder();

      XMLValidationService.validateXMLByXSD(xml, xsd, violationBuilder);

      violationBuilder.build();
      fail("Expected exception.");
    } catch (Exception e) {
      assertTrue(e instanceof ViolationException);
    }
  }

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0}")
  @MethodSource("verifyValidateAValidXMLFileMethodSource")
  void verifyValidateAValidXMLFile(String fileName, URI uri) {
    try (
      InputStream xml = new FileInputStream(new File(uri));
      InputStream xsd = ResourceGetter.resourceInputStream("example.xsd")
    ) {
      final ViolationBuilder violationBuilder = ViolationBuilder.builder();

      XMLValidationService.validateXMLByXSD(xml, xsd, violationBuilder);

      violationBuilder.build();
    } catch (Exception e) {
      fail("Not expected exception.", e);
    }
  }

  @BeforeAll
  static void beforeAll() { GenerateBigFile.generateBigFile();}

  static Stream<Arguments> verifyValidateAInvalidXMLBasedAttacksMethodSource() {
    final Collection<Arguments> arguments = getArguments("example-based-attacks");

    return arguments.stream();
  }

  static Stream<Arguments> verifyValidateAInvalidXMLFileMethodSource() {
    final Collection<Arguments> arguments = getArguments("example-invalid");

    return arguments.stream();
  }

  static Stream<Arguments> verifyValidateAValidXMLFileMethodSource() {
    final Collection<Arguments> arguments = getArguments("example-valid");
    arguments.addAll(getArguments(GenerateBigFile.BIG_FILES_DIR));

    return arguments.stream();
  }

  private static Collection<Arguments> getArguments(String rootPath) {
    final File root = ResourceGetter.resourceFile(rootPath);

    return getArguments(root);
  }

  private static Collection<Arguments> getArguments(final File root) {
    Collection<Arguments> arguments = new ArrayList<>();
    for (final File file : root.listFiles()) {
      arguments.add(Arguments.arguments(file.getName(), file.toURI()));
    }

    return arguments;
  }
}
