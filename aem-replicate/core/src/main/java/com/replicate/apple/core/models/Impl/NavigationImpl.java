package com.replicate.apple.core.models.Impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.replicate.apple.core.models.Navigation;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Navigation.class)
public class NavigationImpl implements Navigation {

  public final static Logger log = LoggerFactory.getLogger(NavigationImpl.class);



  public void init() {

   
  }

  @Override
  public String getPath() {

    return "    path   "  ;
  }

}
