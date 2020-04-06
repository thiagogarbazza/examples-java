package com.github.thiagogarbazza.examples.json;

import com.github.thiagogarbazza.examples.json.valuefinder.JsonValueFinderService;
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

@Tag("json-value-find")
class JsonValueFinderIntegrationTest {

  @ParameterizedTest(name = ParameterizedTest.INDEX_PLACEHOLDER + " {0} -> {2}")
  @MethodSource("verifyValueFinderMethodSource")
  void verifyValueFinder(final String fileName, final URI uri, final String xpath, final String expectedValue) {
    try (InputStream json = new FileInputStream(new File(uri))) {

      final String value = JsonValueFinderService.valueFinder(json, xpath);
      assertEquals(expectedValue, value);
    } catch (Exception e) {
      fail("Not expected exception.", e);
    }
  }

  @BeforeAll
  static void beforeAll() { GenerateJsonBigFile.generateJsonBigFile();}

  static Stream<Arguments> verifyValueFinderMethodSource() {
    Map<String, String> xpathValue = Collections.unmodifiableMap(new HashMap<String, String>() {{
      put("$.codigo", "1234");
      put("$.dataBase", "2019-01-31");
      put("$.empresa", "12345678901234");
    }});

    return getArgumentsStream("example-valid", xpathValue);
  }

  private static Collection<Arguments> getArguments(final File root, final Map<String, String> xpathValue) {
    Collection<Arguments> arguments = new ArrayList<>();
    for (final File file : root.listFiles()) {
      for (final Map.Entry<String, String> entry : xpathValue.entrySet()) {
        arguments.add(Arguments.arguments(file.getName(), file.toURI(), entry.getKey(), entry.getValue()));
      }
    }
    return arguments;
  }

  private static Stream<Arguments> getArgumentsStream(String rootPath, final Map<String, String> xpathValue) {
    final File root = ResourceGetter.resourceFile(rootPath);

    Collection<Arguments> arguments = getArguments(root, xpathValue);
    arguments.addAll(getArguments(GenerateJsonBigFile.BIG_FILES_DIR, xpathValue));

    return arguments.stream();
  }
}
