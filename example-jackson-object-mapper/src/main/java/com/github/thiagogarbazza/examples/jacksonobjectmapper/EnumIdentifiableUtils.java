package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Objects;

@UtilityClass
public class EnumIdentifiableUtils {

  public static <E extends EnumIdentifiable<T>, T> E findIdentifiableById(final Collection<E> values, final T id) {
    if (Objects.isNull(id) || Objects.isNull(values) || values.size() == 0) {
      return null;
    }

    return values.stream()
      .filter(v -> id.equals(v.getId()))
      .findFirst()
      .orElse(null);
  }
}
