package com.github.thiagogarbazza.examples.xmlwithsax.validator;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class ErrorHandlerCustom extends DefaultHandler {

  private final ViolationBuilder violationBuilder;

  ErrorHandlerCustom(final ViolationBuilder violationBuilder) {
    this.violationBuilder = violationBuilder;
  }

  @Override
  public void warning(final SAXParseException e) throws SAXException {
    violationBuilder.warning("xsd-warning", e.getLocalizedMessage());
  }

  @Override
  public void error(final SAXParseException e) throws SAXException {
    violationBuilder.error("xsd-error", e.getLocalizedMessage());
  }

  @Override
  public void fatalError(final SAXParseException e) throws SAXException {
    violationBuilder.error("xsd-fatal-error", e.getLocalizedMessage());
  }
}
