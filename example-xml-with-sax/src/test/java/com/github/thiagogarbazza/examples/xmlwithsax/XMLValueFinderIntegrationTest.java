package com.github.thiagogarbazza.examples.xmlwithsax;

import com.github.thiagogarbazza.examples.xmlwithsax.valuefinder.XMLValueFinderService;
import org.junit.jupiter.api.Assertions;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("xml-value-find")
class XMLValueFinderIntegrationTest {

  @BeforeAll
  static void beforeAll() { GenerateBigFile.generateBigFile();}


  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0} -> {2}")
  @MethodSource("verifyValueFinderAttributeMethodSource")
  void verifyValueFinderAttribute(final String fileName, final URI uri, final String xpath, final String expectedValue) {
    try (InputStream xml = new FileInputStream(new File(uri))) {

      final String value = XMLValueFinderService.valueFinder(xml, xpath);
      assertEquals(expectedValue, value);
    } catch (Exception e) {
      fail("Not expected exception.", e);
    }
  }

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0} -> {2}")
  @MethodSource("verifyValueFinderElementMethodSource")
  void verifyValueFinderElement(final String fileName, final URI uri, final String xpath, final String expectedValue) {
    try (InputStream xml = new FileInputStream(new File(uri))) {

      final String value = XMLValueFinderService.valueFinder(xml, xpath);
      assertEquals(expectedValue, value);
    } catch (Exception e) {
      fail("Not expected exception.", e);
    }
  }

  static Stream<Arguments> verifyValueFinderAttributeMethodSource() {
    Map<String, String> xpathValue = Collections.unmodifiableMap(new HashMap<String, String>() {{
      put("/documento/@codigo", "9090");
      put("//*[local-name()='documento']/@codigo", "9090");

      put("/documento/@data-base", "2019-01-31");
      put("//*[local-name()='documento']/@data-base", "2019-01-31");
    }});

    return getArgumentsStream("example-valid", xpathValue);
  }

  static Stream<Arguments> verifyValueFinderElementMethodSource() {
    Map<String, String> xpathValue = Collections.unmodifiableMap(new HashMap<String, String>() {{
      put("/documento/empresa", "12345678901234");
      put("//*[local-name()='documento']/*[local-name()='empresa']", "12345678901234");
    }});

    return getArgumentsStream("example-valid", xpathValue);
  }

  private static Stream<Arguments> getArgumentsStream(String rootPath, final Map<String, String> xpathValue) {
    final File root = ResourceGetter.resourceFile(rootPath);

    Collection<Arguments> arguments = getArguments(root, xpathValue);
    arguments.addAll(getArguments(GenerateBigFile.BIG_FILES_DIR, xpathValue));

    return arguments.stream();
  }

  private static Collection<Arguments> getArguments(final File root,final Map<String, String> xpathValue) {
    Collection<Arguments> arguments = new ArrayList<>();
    for (final File file : root.listFiles()) {
      for (final Map.Entry<String, String> entry : xpathValue.entrySet()) {
        arguments.add(Arguments.arguments(file.getName(), file.toURI(), entry.getKey(), entry.getValue()));
      }
    }
    return arguments;
  }
}
