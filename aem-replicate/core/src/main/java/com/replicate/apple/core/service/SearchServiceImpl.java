package com.replicate.apple.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Component;

@Component(service = SearchService.class, immediate = true)
public class SearchServiceImpl implements SearchService {

  private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

  List<Map<String, String>> results;

  @Override
  public void setListResults(List<Map<String, String>> param) {
    results = new ArrayList<>();
    for (Map<String, String> string : param) {

      results.add(string);
    }
    for (Map<String, String> string : results) {

      log.info("results: " + string.get("path"));
    }

  }

  @Override
  public List<Map<String, String>> getResults() {

    return results;
  }
}
