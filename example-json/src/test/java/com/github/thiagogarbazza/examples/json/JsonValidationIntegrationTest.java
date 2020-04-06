package com.github.thiagogarbazza.examples.json;

import com.github.thiagogarbazza.examples.json.validator.JsonValidationService;
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

@Tag("json-validator")
class JsonValidationIntegrationTest {

  public static final String EXAMPLE_SCHEMA_VALIDATOR = "example-schema.json";

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0}")
  @MethodSource("verifyValidateAInvalidFileMethodSource")
  void verifyValidateAInvalidFile(String fileName, URI uri) {
    try (
      InputStream xml = new FileInputStream(new File(uri));
      InputStream xsd = ResourceGetter.resourceInputStream(EXAMPLE_SCHEMA_VALIDATOR)
    ) {
      final ViolationBuilder violationBuilder = ViolationBuilder.builder();

      JsonValidationService.validate(xml, xsd, violationBuilder);

      violationBuilder.build();
      fail("Expected exception.");
    } catch (ViolationException e) {
      assertTrue(e.getViolations().size() != 0);
      e.getViolations().forEach(
        message -> System.out.println(String.format("[%s] %s -> %s", message.getType(), message.getKey(), message.getContent())));
    } catch (Exception e) {
      fail(e);
    }
  }

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0}")
  @MethodSource("verifyValidateAValidFileMethodSource")
  void verifyValidateAValidFile(String fileName, URI uri) {
    try (
      InputStream xml = new FileInputStream(new File(uri));
      InputStream xsd = ResourceGetter.resourceInputStream(EXAMPLE_SCHEMA_VALIDATOR)
    ) {
      final ViolationBuilder violationBuilder = ViolationBuilder.builder();

      JsonValidationService.validate(xml, xsd, violationBuilder);

      violationBuilder.build();
    } catch (Exception e) {
      fail("Not expected exception.", e);
    }
  }

  @BeforeAll
  static void beforeAll() { GenerateJsonBigFile.generateJsonBigFile();}

  static Stream<Arguments> verifyValidateAInvalidFileMethodSource() {
    final Collection<Arguments> arguments = getArguments("example-invalid");

    return arguments.stream();
  }

  static Stream<Arguments> verifyValidateAValidFileMethodSource() {
    final Collection<Arguments> arguments = getArguments("example-valid");
    arguments.addAll(getArguments(GenerateJsonBigFile.BIG_FILES_DIR));

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
