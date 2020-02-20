package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static com.github.thiagogarbazza.examples.jacksonobjectmapper.EnumIdentifiableUtils.findIdentifiableById;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumIdentifiableInterfaceIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(EnumIdentifiableInterfaceInteger.VALOR_01, this.objectMapper.readValue("1", EnumIdentifiableInterfaceInteger.class));
    assertEquals(EnumIdentifiableJsonFormatString.VALOR_A, this.objectMapper.readValue("\"A\"", EnumIdentifiableJsonFormatString.class));
  }

  @Test
  void verifySerializer() throws Exception {
    assertEquals("{\"description\":\"Valor 01\",\"id\":1}", this.objectMapper.writeValueAsString(EnumIdentifiableInterfaceInteger.VALOR_01));
    assertEquals("{\"description\":\"Valor A\",\"id\":\"A\"}", this.objectMapper.writeValueAsString(EnumIdentifiableJsonFormatString.VALOR_A));
  }

  @Getter
  enum EnumIdentifiableInterfaceInteger implements EnumIdentifiable<Integer> {
    VALOR_01(1, "Valor 01"),
    VALOR_02(2, "Valor 02"),
    VALOR_03(3, "Valor 03");

    private final String description;
    private final Integer id;

    EnumIdentifiableInterfaceInteger(final Integer id, final String description) {
      this.id = id;
      this.description = description;
    }

    @JsonCreator
    static EnumIdentifiableInterfaceInteger valueOfId(final Integer value) {
      final EnumIdentifiableInterfaceInteger response = findIdentifiableById(asList(values()), value);

      if (isNull(response)) {
        throw new IllegalArgumentException("Não foi possível encontrar para o id " + value);
      }

      return response;
    }
  }

  @Getter
  enum EnumIdentifiableJsonFormatString implements EnumIdentifiable<String> {
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
    static EnumIdentifiableJsonFormatString valueOfId(final String value) {
      final EnumIdentifiableJsonFormatString response = findIdentifiableById(asList(values()), value);

      if (isNull(response)) {
        throw new IllegalArgumentException("Não foi possível encontrar para o id " + value);
      }

      return response;
    }
  }
}
