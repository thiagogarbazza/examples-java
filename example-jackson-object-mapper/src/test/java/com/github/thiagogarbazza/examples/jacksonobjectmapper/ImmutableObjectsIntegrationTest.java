package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.With;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImmutableObjectsIntegrationTest {

  private final ObjectMapper objectMapper = JacksonObjectMapperBuilder.build();

  @Test
  void verifyDeserializer() throws Exception {
    assertEquals(ExampleImmutable.newExampleImmutable(), this.objectMapper.readValue(ExampleImmutable.JSON, ExampleImmutable.class));
  }

  @Test
  void verifySerializer() throws Exception {
    assertEquals(ExampleImmutable.JSON, this.objectMapper.writeValueAsString(ExampleImmutable.newExampleImmutable()));
  }

  @Value
  @With
  @Builder
  @AllArgsConstructor(access = PRIVATE)
  @NoArgsConstructor(force = true, access = PRIVATE)
  static final class ExampleImmutable {

    static final String JSON = "{\"condition\":true,\"date\":\"2019-01-31\",\"money\":3.14,\"text\":\"Algum texto\"}";

    private final Boolean condition;
    private final LocalDate date;
    private final BigDecimal money;
    private final String text;

    static ExampleImmutable newExampleImmutable() {
      return ExampleImmutable.builder()
        .condition(Boolean.TRUE)
        .date(LocalDate.of(2019, 1, 31))
        .money(new BigDecimal("3.14"))
        .text("Algum texto")
        .build();
    }
  }
}
