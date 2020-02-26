package com.github.thiagogarbazza.examples.apachepoiexcel;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@UtilityClass
public class ReportProperty {

  private static final ResourceBundle APPLICATION_BUNDLE = ResourceBundle.getBundle("application-report");

  public static String reportProperty(final String key) {

    return trimToEmpty(APPLICATION_BUNDLE.getString(key));
  }
}
