package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.thiagogarbazza.examples.apachepoiexcel.ExcelWorkbook.workbookToOutputStream;
import static java.util.Arrays.asList;

class ExcelTableBuilderTest {

  @Test
  void verifyExcelTableBuilder() throws IOException {
    final Workbook workbook = new SXSSFWorkbook(10000);
    final Sheet sheet = workbook.createSheet("Example");
    sheet.setDisplayGridlines(false);

    ExcelTableBuilder.<Integer>builder()
      .sheet(sheet)
      .rowStart(0)
      .colStart(0)
      .title("Table example title")
      .colunas(asList(ExampleColumn.values()))
      .items(asList(1, 2, 3, 4, 5, 6))
      .build();

    final File tempFile = File.createTempFile("example-apache-poi-excel-table-builder", ".xlsx");
    final Path path = Paths.get(tempFile.toURI());
    Files.write(path, workbookToOutputStream(workbook).toByteArray());
  }

  @Getter
  private enum ExampleColumn implements Coluna<Integer> {

    COL_A(0, 4750, "Coluna a") {
      @Override
      public String getValor(Integer item) {
        return "col-a: " + StringUtils.leftPad(item.toString(), 5, '0');
      }
    },
    COL_B(1, 4750, "Coluna b") {
      @Override
      public String getValor(Integer item) {
        return "col-b: " + StringUtils.leftPad(item.toString(), 5, '0');
      }
    },
    COL_C(2, 4750, "Coluna c") {
      @Override
      public String getValor(Integer item) {
        return "col-c: " + StringUtils.leftPad(item.toString(), 5, '0');
      }
    },
    COL_D(3, 4750, "Coluna d") {
      @Override
      public String getValor(Integer item) {
        return "col-d: " + StringUtils.leftPad(item.toString(), 5, '0');
      }
    };

    private int index;
    private int tamanho;
    private String titulo;

    ExampleColumn(int index, int tamanho, String titulo) {
      this.index = index;
      this.titulo = titulo;
      this.tamanho = tamanho;
    }
  }
}
