package com.replicate.apple.core.service;

import java.util.List;
import java.util.Map;

public interface SearchService {

  void setListResults(List<Map<String, String>> param);
  
  List<Map<String, String>> getResults();
}
