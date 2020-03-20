package com.github.thiagogarbazza.examples.xmlwithsax.validator;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidationService {

  public static void validateXMLByXSD(final InputStream xml, final InputStream xsd, final ViolationBuilder violationBuilder) {
    try {
      final Schema schema = schemaFactory(xsd);
      final Validator validator = validatorFactory(schema, new ErrorHandlerCustom(violationBuilder));
      final SAXSource source = new SAXSource(new InputSource(xml));

      validator.validate(source);
    } catch (SAXException | IOException e) {
      violationBuilder.error("xsd-fatal-error", e.getLocalizedMessage());
    }
  }

  private static Schema schemaFactory(final InputStream xsd) throws SAXException {
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    Source source = new StreamSource(xsd);

    return schemaFactory.newSchema(source);
  }

  private static Validator validatorFactory(final Schema schema, final ErrorHandler errorHandler) throws SAXException {
    final Validator validator = schema.newValidator();
    validator.setErrorHandler(errorHandler);

    return validator;
  }
}
