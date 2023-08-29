package com.replicate.apple.core.utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import java.util.HashMap;
import java.util.Map;

// resource resolver factory helper class

public final class Resolverutil {

  private Resolverutil() {

  }

  public static final String apple_replicate = "applereplicateadmin";

  /**
   * @param resourceResolverFactory factory
   * @return new resource resolver for Sony service user
   * @throws LoginException if problems
   */
  public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
    final Map<String, Object> paramMap = new HashMap<String, Object>();
    // created a map object

    paramMap.put(ResourceResolverFactory.SUBSERVICE, apple_replicate);

    // fetches the admin service resolver using service user.
    ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
    return resolver;
  }

}
