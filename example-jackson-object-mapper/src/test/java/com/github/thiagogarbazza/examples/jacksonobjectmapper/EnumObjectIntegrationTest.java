package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumObjectIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(ExampleEnumObject.VALOR_01, this.objectMapper.readValue("\"VALOR_01\"", ExampleEnumObject.class));
  }

  @Test
  void verifySerializer() throws Exception {

    assertEquals("\"VALOR_01\"", this.objectMapper.writeValueAsString(ExampleEnumObject.VALOR_01));
  }

  @Getter
  enum ExampleEnumObject {
    VALOR_01(1, "Valor 01"),
    VALOR_02(2, "Valor 02"),
    VALOR_03(3, "Valor 03");

    private final String description;
    private final int id;

    ExampleEnumObject(final int id, final String description) {
      this.id = id;
      this.description = description;
    }
  }
}
