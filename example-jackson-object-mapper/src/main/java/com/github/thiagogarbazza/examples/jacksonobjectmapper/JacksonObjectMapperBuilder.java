package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;

@UtilityClass
public class JacksonObjectMapperBuilder {

  public static ObjectMapper build() {
    return new ObjectMapper()
      .disable(SerializationFeature.INDENT_OUTPUT)
      .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"))
      .registerModule(customModule())
      .registerModule(new JavaTimeModule())
      .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)

      .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
      .enable(MapperFeature.ALLOW_COERCION_OF_SCALARS)

      .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

      .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      ;
  }

  private static SimpleModule customModule() {
    return new SimpleModule()
      ;
  }
}
