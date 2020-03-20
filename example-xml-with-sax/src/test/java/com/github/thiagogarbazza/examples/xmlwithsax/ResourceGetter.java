package com.github.thiagogarbazza.examples.xmlwithsax;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

class ResourceGetter {

  static File resourceFile(final String filePath) {
    final URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
    try {
      return new File(url.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  static InputStream resourceInputStream(final String filePath) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
  }
}
