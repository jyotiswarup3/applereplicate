package com.replicate.apple.core.models.Impl;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.replicate.apple.core.models.HeroImage;

@Model(adaptables = Resource.class, adapters = HeroImage.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroImageImpl implements HeroImage {

  @Inject
  String title;

  @ValueMapValue(name = "description")
  String des;

  @Inject
  String imagepath;

  @Inject
  String linktext;

  @Override
  public String getTitle() {

    return title;
  }

  @Override
  public String getDescription() {

    return des;
  }

  @Override
  public String getLinkText() {

    return linktext;
  }

  @Override
  public String getImagePath() {

    return imagepath;
  }

}
