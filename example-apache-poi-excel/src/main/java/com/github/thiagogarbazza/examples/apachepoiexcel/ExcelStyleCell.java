package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import static com.github.thiagogarbazza.examples.apachepoiexcel.ExcelStyleColor.newColor;
import static com.github.thiagogarbazza.examples.apachepoiexcel.ReportProperty.reportProperty;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;

@UtilityClass
public class ExcelStyleCell {

  public static XSSFCellStyle excelStyleCell(final Workbook workbook, final String baseProperty) {
    final XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

    style.setFont(ExcelStyleFont.newFont(workbook, baseProperty));
    style.setAlignment(HorizontalAlignment.valueOf(reportProperty(baseProperty.concat(".horizontal-alignment"))));
    style.setVerticalAlignment(VerticalAlignment.valueOf(reportProperty(baseProperty.concat(".vertical-alignment"))));
    style.setFillForegroundColor(newColor(reportProperty(baseProperty.concat(".background-color"))));
    style.setFillPattern(FillPatternType.valueOf(reportProperty(baseProperty.concat(".background-type"))));
    style.setWrapText(toBoolean(reportProperty(baseProperty.concat(".wrap-text"))));

    return style;
  }
}

