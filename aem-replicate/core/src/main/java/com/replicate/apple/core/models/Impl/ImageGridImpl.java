package com.replicate.apple.core.models.Impl;

import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import com.replicate.apple.core.models.ImageGridInterface;

@Model(adaptables = Resource.class, adapters = ImageGridInterface.class)
public class ImageGridImpl implements ImageGridInterface {

    @ChildResource(name = "imagegrid")
    Collection<GridFile> imagegrid;

    @Inject
    String imagegridtile;

    @Override
    public Boolean getOption() {
        if (imagegridtile.equals("imagegrid")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<GridFile> getImageGrid() {
        return imagegrid;
    }

    @PostConstruct
    protected void init() {
        imagegrid = CollectionUtils.emptyIfNull(this.imagegrid);
    }

}
