package com.replicate.apple.core.models.Impl;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import com.replicate.apple.core.models.BannerImage;

@Model(adaptables = Resource.class, adapters = BannerImage.class)
public class BannerImageImpl implements BannerImage {

  @Inject
  String imagevalue;

  @Override
  public String getImage() {

    return imagevalue;
  }

}
