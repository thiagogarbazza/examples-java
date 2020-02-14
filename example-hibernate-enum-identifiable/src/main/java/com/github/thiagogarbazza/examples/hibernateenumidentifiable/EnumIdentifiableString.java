package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import lombok.Getter;

@Getter
public enum EnumIdentifiableString implements EnumIdentifiable<String> {

  TYPE_ZA("ZA"),
  TYPE_ZB("ZB"),
  TYPE_ZC("ZC");

  private final String id;

  EnumIdentifiableString(final String id) {this.id = id;}
}
