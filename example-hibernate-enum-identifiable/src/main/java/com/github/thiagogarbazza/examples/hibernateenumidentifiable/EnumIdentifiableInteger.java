package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import lombok.Getter;

@Getter
public enum EnumIdentifiableInteger implements EnumIdentifiable<Integer> {

  TYPE_10(10),
  TYPE_20(20),
  TYPE_30(30);

  private final int id;

  EnumIdentifiableInteger(final int id) {this.id = id;}

  @Override
  public Integer getId() {
    return this.id;
  }
}
