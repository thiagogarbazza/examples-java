package com.github.thiagogarbazza.examples.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

class GenerateJsonBigFile {

  static final File BIG_FILES_DIR = new File(System.getProperty("java.io.tmpdir"), "example-json-big-files");
  private static final String BROKEN_LINE = "\n";
  private static final Random RANDOM = new Random();

  static void generateJsonBigFile() {
    try {
      if (BIG_FILES_DIR.exists()) {
        return;
      }

      BIG_FILES_DIR.mkdirs();

      createFile("example-json-big-file-MB-000001.json", 1);
      createFile("example-json-big-file-MB-000005.json", 5);
      createFile("example-json-big-file-MB-000010.json", 10);
      createFile("example-json-big-file-MB-000100.json", 100);
      createFile("example-json-big-file-MB-000500.json", 500);
//      createFile("example-json-big-file-MB-001000.json", 1000);
//      createFile("example-json-big-file-MB-002000.json", 2000);
//      createFile("example-json-big-file-MB-005000.json", 5000);
//      createFile("example-json-big-file-MB-010000.json", 10000);
//      createFile("example-json-big-file-MB-050000.json", 50000);
//      createFile("example-json-big-file-MB-100000.json", 100000);
//      createFile("example-json-big-file-MB-200000.json", 200000);
    } catch (IOException e) {

    }
  }

  private static void createFile(final String fileName, final int lenth) throws IOException {
    final long start = System.currentTimeMillis();
    System.out.println(fileName);
    final File file = new File(BIG_FILES_DIR, fileName);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      writer.append("{\n");
      writer.append("  \"codigo\": 1234,\n");
      writer.append("  \"dataBase\": \"2019-01-31\",\n");
      writer.append("  \"tipoRemessa\": \"I\",\n");
      writer.append("  \"empresa\": \"12345678901234\",\n");
      writer.append("  \"unidades\": [\n");

      createUnit(writer, lenth);

      writer.append("  ]\n");
      writer.append("}\n");
    }

    final long end = System.currentTimeMillis();
    System.out.println("                   " + (end - start) + "ms");
  }

  private static void createUnit(final BufferedWriter writer, final int lenght) throws IOException {
    for (int i = 0; i < lenght; i++) {
      final String codigoUnidade = "U" + (i + 1);

      writer.append("    {\n");
      writer.append(String.format("      \"codigo\": \"%s\",\n", codigoUnidade));
      writer.append("      \"contas\": [\n");

      createUnitCounts(writer, codigoUnidade, 12000);

      writer.append("      ]\n");
      writer.append(String.format("    }%s", i < lenght -1 ? ",\n": "\n"));
    }
  }

  private static void createUnitCounts(final BufferedWriter writer, final String codigoUnidade, final int lenght) throws IOException {
    for (int i = 0; i < lenght; i++) {
      final String codigoConta = codigoUnidade + "-C" + (i + 1);

      final BigDecimal valor = BigDecimal.valueOf(RANDOM.nextDouble()).multiply(new BigDecimal("100000000")).setScale(2, BigDecimal.ROUND_HALF_UP);

      writer.append("        {\n");
      writer.append(String.format("          \"codigo\": \"%s\",\n", codigoConta));
      writer.append(String.format("          \"valor\": %s\n", valor));
      writer.append(String.format("        }%s", i < lenght -1 ? ",\n": "\n"));
    }
  }
}
