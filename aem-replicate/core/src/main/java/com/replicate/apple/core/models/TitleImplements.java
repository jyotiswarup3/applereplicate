package com.replicate.apple.core.models;
import javax.inject.Inject;

import org.apache.sling.models.annotations.Model;


import org.apache.sling.api.resource.Resource;


@Model(adaptables = Resource.class, adapters = TitleInterface.class)
public class TitleImplements implements TitleInterface{

    @Inject
    private String text;

    @Inject
    private int margintop;
    
    @Inject
    private int fontsize;
    
    @Override
    public String getText() {

        return text;
    }

    @Override
    public int getMarginTop() {

        return margintop;
    }

    @Override
    public int getFontSize() {

        return fontsize;
    }

    
}
