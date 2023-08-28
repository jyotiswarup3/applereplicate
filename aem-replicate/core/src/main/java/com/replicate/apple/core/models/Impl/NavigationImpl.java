package com.replicate.apple.core.models.Impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.replicate.apple.core.models.Navigation;
import com.replicate.apple.core.utils.Resolverutil;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Navigation.class)
public class NavigationImpl implements Navigation {

  public final static Logger log = LoggerFactory.getLogger(NavigationImpl.class);

  @Override
  public String getPath() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPath'");
  }


 }




