package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

@UtilityClass
public class ExcelStyleBorder {

  public static void styleBorder(final XSSFCellStyle cellStyle, final BorderStyle borderStyle, final XSSFColor cor) {
    cellStyle.setBorderTop(borderStyle);
    cellStyle.setTopBorderColor(cor);

    cellStyle.setBorderRight(borderStyle);
    cellStyle.setRightBorderColor(cor);

    cellStyle.setBorderBottom(borderStyle);
    cellStyle.setBottomBorderColor(cor);

    cellStyle.setBorderLeft(borderStyle);
    cellStyle.setLeftBorderColor(cor);
  }
}
