package com.github.thiagogarbazza.examples.json.validator;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.Collection;

public class JsonValidationService {

  public static void createIssues(final Collection<ValidationException> causingExceptions, final ViolationBuilder violationBuilder) {
    if (CollectionUtils.isEmpty(causingExceptions)) {
      return;
    }

    causingExceptions.forEach(causingException -> {
      violationBuilder.error(causingException.getPointerToViolation(), causingException.getMessage());
      createIssues(causingException.getCausingExceptions(), violationBuilder);
    });
  }

  public static void validate(final InputStream json, final InputStream schema, final ViolationBuilder violationBuilder) {
    try {
      JSONObject jsonSchema = new JSONObject(new JSONTokener(schema));
      JSONObject jsonSubject = new JSONObject(new JSONTokener(json));

      Schema schemaLoader = SchemaLoader.load(jsonSchema);
      schemaLoader.validate(jsonSubject);
    } catch (ValidationException e) {
      violationBuilder.error(e.getPointerToViolation(), e.getMessage());
      createIssues(e.getCausingExceptions(), violationBuilder);
    } catch (Exception e) {
      violationBuilder.error("schema-json-fatal-error", e.getLocalizedMessage());
    }
  }
}
