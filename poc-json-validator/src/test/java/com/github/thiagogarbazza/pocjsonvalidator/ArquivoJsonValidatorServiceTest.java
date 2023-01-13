package com.github.thiagogarbazza.pocjsonvalidator;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import com.github.thiagogarbazza.violationbuilder.ViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.thiagogarbazza.pocjsonvalidator.FileUtils.buscarNoResources;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArquivoJsonValidatorServiceTest {

    private static final String SCHEMA_DRAFT_07 = "arquivos/json-schema-draft-07.json";
    private static final String SCHEMA_DRAFT_201909 = "arquivos/json-schema-draft-201909.json";
    private static final String SCHEMA_DRAFT_202012 = "arquivos/json-schema-draft-202012.json";

    private final ArquivoJsonValidatorService arquivoJsonValidatorService = new ArquivoJsonValidatorService();
    private ViolationBuilder violationBuilder;

    @BeforeEach
    void before() {
        violationBuilder = ViolationBuilder.builder();
    }

    @ParameterizedTest
    @ValueSource(strings = {SCHEMA_DRAFT_07, SCHEMA_DRAFT_201909, SCHEMA_DRAFT_202012})
    void verificarValidacaoArquivo(final String schemaPath) {
        arquivoJsonValidatorService.validar(UTF_8.name(), buscarNoResources(schemaPath), buscarNoResources("arquivos/example-json-valido.json"),
            violationBuilder);
        violationBuilder.build();
    }

    @ParameterizedTest
    @ValueSource(strings = {SCHEMA_DRAFT_07, SCHEMA_DRAFT_201909, SCHEMA_DRAFT_202012})
    void verificarValidacaoArquivoComErro(final String schemaPath) {
        final ViolationException exception = assertThrows(ViolationException.class, () -> {
            arquivoJsonValidatorService.validar(UTF_8.name(), buscarNoResources(schemaPath), buscarNoResources("arquivos/example-json-invalido.json"),
                violationBuilder);
            violationBuilder.build();
        });

        assertEquals(1, exception.getViolations().size());
        assertEquals("$.tipoRemessa: does not have a value in the enumeration [E, I, S]",
            exception.getViolations().stream().findFirst().get().getContent());
    }
}