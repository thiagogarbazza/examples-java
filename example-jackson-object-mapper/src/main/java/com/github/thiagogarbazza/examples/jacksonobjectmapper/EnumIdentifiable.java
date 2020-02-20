package com.github.thiagogarbazza.examples.jacksonobjectmapper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
// TODO encontrar forma de configurar a serialização e deserialização sem utilizar as anotações @JsonFormat e  @JsonCreator
public interface EnumIdentifiable<T> {

  T getId();
}
