package com.replicate.apple.core.models.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.replicate.apple.core.models.Navigation;
import com.replicate.apple.core.utils.Resolverutil;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Navigation.class)
public class NavigationImpl implements Navigation {

  public final static Logger log = LoggerFactory.getLogger(NavigationImpl.class);

  @OSGiService
  ResourceResolverFactory resourceResolverFactory;

  List<Map<String, String>> childPageList = new ArrayList<>();

  @Override
  public List<Map<String, String>> getPath() {
    // TODO Auto-generated method stub

    try {
      ResourceResolver resolver = Resolverutil.newResolver(resourceResolverFactory);
      PageManager pageManager = resolver.adaptTo(PageManager.class);
      Page page = pageManager.getPage("/content/apple-replicate/language-masters/en/apple-replicate-home-page");
      Iterator<Page> pagelist = page.listChildren();

      while (pagelist.hasNext()) {
        Map<String, String> pageDetails = new HashMap<>();
        Page trialpage = pagelist.next();
        pageDetails.put("title", trialpage.getTitle().toString());
        pageDetails.put("path", trialpage.getPath().toString());
        childPageList.add(pageDetails);

      }
      return childPageList;

    } catch (LoginException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;

  }

}
