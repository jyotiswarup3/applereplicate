package com.replicate.apple.core.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;

@Component(service = SearchService.class,immediate = true)
public class SearchServiceImpl implements SearchService {

  private static final Logger Log= LoggerFactory.getLogger(SearchServiceImpl.class);

  @Reference
  QueryBuilder queryBuilder;
  
}
