package com.github.thiagogarbazza.examples.xmlwithsax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

class GenerateXMLBigFile {

  static final File BIG_FILES_DIR = new File(System.getProperty("java.io.tmpdir"), "example-xml-big-files");
  private static final String BROKEN_LINE = "\n";
  private static final Random RANDOM = new Random();

  static void generateXMLBigFile() {
    try {
      if (BIG_FILES_DIR.exists()) {
        return;
      }

      BIG_FILES_DIR.mkdirs();

      createFile("example-xml-big-file-MB-000001.xml", 1);
      createFile("example-xml-big-file-MB-000005.xml", 5);
      createFile("example-xml-big-file-MB-000010.xml", 10);
      createFile("example-xml-big-file-MB-000100.xml", 100);
      createFile("example-xml-big-file-MB-000500.xml", 500);
//      createFile("example-xml-big-file-MB-001000.xml", 1000);
//      createFile("example-xml-big-file-MB-002000.xml", 2000);
//      createFile("example-xml-big-file-MB-005000.xml", 5000);
//      createFile("example-xml-big-file-MB-010000.xml", 10000);
//      createFile("example-xml-big-file-MB-050000.xml", 50000);
//      createFile("example-xml-big-file-MB-100000.xml", 100000);
//      createFile("example-xml-big-file-MB-200000.xml", 200000);
    } catch (IOException e) {

    }
  }

  private static void createFile(final String fileName, final int lenth) throws IOException {
    final long start = System.currentTimeMillis();
    System.out.println(fileName);
    final File file = new File(BIG_FILES_DIR, fileName);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(BROKEN_LINE);
      writer.append("<documento codigo=\"9090\" data-base=\"2019-01-31\" tipo-remessa=\"I\">").append(BROKEN_LINE);
      writer.append("  <empresa>12345678901234</empresa>").append(BROKEN_LINE);
      writer.append("  <unidades>").append(BROKEN_LINE);

      createUnit(writer, lenth);

      writer.append("  </unidades>").append(BROKEN_LINE);
      writer.append("</documento>").append(BROKEN_LINE);
    }

    final long end = System.currentTimeMillis();
    System.out.println("                   " + (end - start) + "ms");
  }

  private static void createUnit(final BufferedWriter writer, final int lenght) throws IOException {
    for (int i = 0; i < lenght; i++) {
      final String codigoUnidade = "U" + (i + 1);

      writer.append(String.format("    <unidade codigo=\"%s\">", codigoUnidade)).append(BROKEN_LINE);
      writer.append("      <contas>").append(BROKEN_LINE);
      createUnitCounts(writer, codigoUnidade, 20000);
      writer.append("      </contas>").append(BROKEN_LINE);
      writer.append("    </unidade>").append(BROKEN_LINE);
    }
  }

  private static void createUnitCounts(final BufferedWriter writer, final String codigoUnidade, final int lenght) throws IOException {
    for (int i = 0; i < lenght; i++) {
      final String codigoConta = codigoUnidade + "-C" + (i + 1);

      final BigDecimal valor = new BigDecimal(RANDOM.nextDouble()).multiply(new BigDecimal("100000000")).setScale(2, BigDecimal.ROUND_HALF_UP);

      writer.append(String.format("        <conta codigo=\"%s\">%s</conta>", codigoConta, valor)).append(BROKEN_LINE);
    }
  }
}
