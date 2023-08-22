package com.replicate.apple.core.models.Impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class GridFile {

    @ValueMapValue(name = "alt")
    private String alt;

    @ValueMapValue(name = "caption")
    private String caption;

    @ValueMapValue(name = "fileReference")
    private String fileReference;

    @ValueMapValue(name = "linkURL")
    private String linkURL;

    public String getAlt() {
        return this.alt;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getFileReference() {
        return this.fileReference;
    }

    public String getLinkURL() {
        return this.linkURL;
    }

}
