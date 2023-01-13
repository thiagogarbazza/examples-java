package com.github.thiagogarbazza.pocjsonvalidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Collection;

class ArquivoJsonValidatorService {

    @SneakyThrows
    void validar(final String encoding, final File schema, final File json, final ViolationBuilder violationBuilder) {
        final ObjectMapper mapper = new ObjectMapper();

        final JsonNode jsonSchema = mapper.readTree(schema);
        final JsonNode jsonNode = mapper.readTree(json);

        final SpecVersion.VersionFlag versionFlag = SpecVersionDetector.detect(jsonSchema);
        final Collection<ValidationMessage> errors = JsonSchemaFactory.getInstance(versionFlag)
            .getSchema(jsonSchema)
            .validate(jsonNode);

        errors.parallelStream().forEach(e -> violationBuilder.error("error"+e.getPath(), e.getMessage()));
    }
}
