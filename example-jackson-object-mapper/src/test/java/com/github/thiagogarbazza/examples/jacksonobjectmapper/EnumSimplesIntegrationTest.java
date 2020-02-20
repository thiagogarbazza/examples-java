package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumSimplesIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(ExampleEnumSimples.VALOR_01, this.objectMapper.readValue("\"VALOR_01\"", ExampleEnumSimples.class));
  }

  @Test
  void verifySerializer() throws Exception {
    assertEquals("\"VALOR_01\"", this.objectMapper.writeValueAsString(ExampleEnumSimples.VALOR_01));
  }

  static enum ExampleEnumSimples {
    VALOR_01,
    VALOR_02,
    VALOR_03;
  }
}
