package com.replicate.apple.core.models;
import javax.inject.Inject;

import org.apache.sling.models.annotations.Model;


import org.apache.sling.api.resource.Resource;


@Model(adaptables = Resource.class, adapters = TitleInterface.class)
public class TitleImplements implements TitleInterface{

    @Inject
    private String text;
    
    @Override
    public String getText() {

        return text;
    }

    
}
