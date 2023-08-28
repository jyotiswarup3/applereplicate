package com.replicate.apple.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

public final class Resolverutil {

    public static final String apple_replicate="applereplicateadmin";

    @Reference
    private static ResourceResolverFactory resolverFactory;


    public static ResourceResolver newResolver() throws LoginException{

       final Map<String,Object> map=new HashMap<String , Object>();

       map.put(ResourceResolverFactory.SUBSERVICE, apple_replicate);
       
       ResourceResolver resolver=resolverFactory.getResourceResolver(map);


        return resolver;
    }
    
}
