package com.replicate.apple.core.models.Impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.replicate.apple.core.models.Navigation;

@Model(adaptables = Resource.class, adapters = Navigation.class)
public class NavigationImpl implements Navigation {

  public final static Logger log = LoggerFactory.getLogger(NavigationImpl.class);

  @Reference
  ResourceResolver resourcerResolver;

  String name;

  public void init() {
    Resource res = resourcerResolver
        .getResource("/content/apple-replicate/language-masters/en/apple-replicate-home-page");
    this.name = res.getName();
  }

  @Override
  public String getPath() {

    return name;
  }

}
