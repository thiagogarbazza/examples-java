package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import static com.github.thiagogarbazza.examples.apachepoiexcel.ExcelStyleColor.newColor;
import static com.github.thiagogarbazza.examples.apachepoiexcel.ReportProperty.reportProperty;
import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;

@UtilityClass
public class ExcelStyleFont {

  public static Font newFont(final Workbook workbook, final String baseProperty) {
    final String name = reportProperty(baseProperty.concat(".font-name"));
    final double height = parseDouble(reportProperty(baseProperty.concat(".font-height")));
    final boolean bold = toBoolean(reportProperty(baseProperty.concat(".font-bold")));
    final XSSFColor color = newColor(reportProperty(baseProperty.concat(".font-color")));

    return newFont(workbook, name, height, bold, color);
  }

  public static Font newFont(final Workbook workbook, final String name, final double height, final boolean bold, final XSSFColor color) {
    XSSFFont font = (XSSFFont) workbook.createFont();
    font.setFontName(name);
    font.setFontHeight(height);
    font.setBold(bold);
    font.setColor(color);

    return font;
  }
}
