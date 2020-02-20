package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumIdentifiableInterfaceJsonFormatIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(EnumIdentifiableInterface.VALOR_01, this.objectMapper.readValue("1", EnumIdentifiableInterface.class));
    assertEquals(EnumIdentifiableJsonFormatString.VALOR_A, this.objectMapper.readValue("\"A\"", EnumIdentifiableJsonFormatString.class));
  }

  @Test
  void verifySerializer() throws Exception {
    assertEquals("{\"description\":\"Valor 01\",\"id\":1}", this.objectMapper.writeValueAsString(EnumIdentifiableInterface.VALOR_01));
    assertEquals("{\"description\":\"Valor A\",\"id\":\"A\"}", this.objectMapper.writeValueAsString(EnumIdentifiableJsonFormatString.VALOR_A));
  }

  @Getter
  enum EnumIdentifiableInterface implements EnumIdentifiableJsonFormat<Integer> {
    VALOR_01(1, "Valor 01"),
    VALOR_02(2, "Valor 02"),
    VALOR_03(3, "Valor 03");

    private final String description;
    private final Integer id;

    EnumIdentifiableInterface(final Integer id, final String description) {
      this.id = id;
      this.description = description;
    }

    @JsonCreator
    static EnumIdentifiableInterface valueOfId(final Integer value) {
      for (final EnumIdentifiableInterface exampleEnumIdentifiable : values()) {
        if (exampleEnumIdentifiable.getId().equals(value)) {
          return exampleEnumIdentifiable;
        }
      }

      throw new IllegalArgumentException("Não foi possível encontrar para o id " + value);
    }
  }

  @Getter
  enum EnumIdentifiableJsonFormatString implements EnumIdentifiableJsonFormat<String> {
    VALOR_A("A", "Valor A"),
    VALOR_B("B", "Valor B"),
    VALOR_C("C", "Valor C");

    private final String description;
    private final String id;

    EnumIdentifiableJsonFormatString(final String id, final String description) {
      this.id = id;
      this.description = description;
    }

    @JsonCreator
    public static EnumIdentifiableJsonFormatString valueOfId(final String value) {
      for (final EnumIdentifiableJsonFormatString exampleEnumIdentifiable : values()) {
        if (exampleEnumIdentifiable.getId().equals(value)) {
          return exampleEnumIdentifiable;
        }
      }

      throw new IllegalArgumentException("Não foi possível encontrar para o id " + value);
    }
  }

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  interface EnumIdentifiableJsonFormat<T> {

    T getId();
  }
}
