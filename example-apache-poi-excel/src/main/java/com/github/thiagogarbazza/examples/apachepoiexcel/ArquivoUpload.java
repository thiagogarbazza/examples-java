package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.ByteArrayOutputStream;

@Getter
@Builder()
@ToString(of = {"nome", "mimetype"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArquivoUpload {

  private ByteArrayOutputStream conteudo;
  private String extensao;
  private String mimetype;
  private String nome;

  public String filename() {
    return this.nome + "." + this.extensao;
  }
}
