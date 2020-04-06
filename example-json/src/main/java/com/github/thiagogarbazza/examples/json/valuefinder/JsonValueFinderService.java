package com.github.thiagogarbazza.examples.json.valuefinder;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import java.io.InputStream;

public class JsonValueFinderService {

  public static String valueFinder(InputStream json, String xpath) {
    Configuration configuration = Configuration.defaultConfiguration()
      .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);


    return JsonPath.using(configuration).parse(json).read(xpath, String.class);
  }
}
