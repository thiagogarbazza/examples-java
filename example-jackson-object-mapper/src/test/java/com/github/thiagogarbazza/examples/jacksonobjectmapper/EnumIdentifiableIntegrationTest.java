package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumIdentifiableIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(ExampleEnumIdentifiable.VALOR_01, this.objectMapper.readValue("1", ExampleEnumIdentifiable.class));
  }

  @Test
  void verifySerializer() throws Exception {
    assertEquals("{\"description\":\"Valor 01\",\"id\":1}", this.objectMapper.writeValueAsString(ExampleEnumIdentifiable.VALOR_01));
  }

  @Getter
  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  enum ExampleEnumIdentifiable {
    VALOR_01(1, "Valor 01"),
    VALOR_02(2, "Valor 02"),
    VALOR_03(3, "Valor 03");

    private final String description;
    private final Integer id;

    ExampleEnumIdentifiable(final Integer id, final String description) {
      this.id = id;
      this.description = description;
    }

    @JsonCreator
    static ExampleEnumIdentifiable valueOfId(final Integer value) {
      for (final ExampleEnumIdentifiable exampleEnumIdentifiable : values()) {
        if (exampleEnumIdentifiable.getId().equals(value)) {
          return exampleEnumIdentifiable;
        }
      }

      throw new IllegalArgumentException("Não foi possível encontrar para o id " + value);
    }
  }
}
