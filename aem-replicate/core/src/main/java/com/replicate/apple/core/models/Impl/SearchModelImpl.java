package com.replicate.apple.core.models.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.replicate.apple.core.models.SearchModel;
import com.replicate.apple.core.service.SearchService;

@Model(adaptables = Resource.class, adapters = SearchModel.class)
public class SearchModelImpl implements SearchModel {

  List<String> result = new ArrayList<>();

  @OSGiService
  SearchService searchservice;

  @Override
  public List<Map<String, String>> getResults() {
    return searchservice.getResults();
  }

  @PostConstruct
  public void init() {

    result.add("jyoti");
    result.add("swarup");
    result.add("jena");

  }

}
