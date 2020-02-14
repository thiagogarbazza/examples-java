package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

public interface EnumIdentifiable<T> {

  String TYPE_NAME = "com.github.thiagogarbazza.examples.hibernateenumidentifiable.EnumIdentifiableType";

  T getId();
}
