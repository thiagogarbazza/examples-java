package com.github.thiagogarbazza.pocjsonvalidator;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.net.URL;

@UtilityClass
class FileUtils {

    @SneakyThrows
    static File buscarNoResources(final String filePath) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        return new File(url.toURI());
    }
}
