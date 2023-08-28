package com.replicate.apple.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/pages", "/jyoti/swarup" })
public class FormServlet extends SlingAllMethodsServlet {

  private final static Logger logger = LoggerFactory.getLogger(FormServlet.class);

  @Override
  protected void doGet(final SlingHttpServletRequest req,
      final SlingHttpServletResponse resp) throws IOException {

    final ResourceResolver resource = req.getResourceResolver();
    Page page = resource.adaptTo(PageManager.class)
        .getPage("/content/apple-replicate/language-masters/en/apple-replicate-home-page");
    Iterator<Page> childpage = page.listChildren();

    JSONArray newarArray = new JSONArray();
    while (childpage.hasNext()) {
      Page pages = childpage.next();
      JSONObject object = new JSONObject();
      try {
        object.put("Page path", pages.getPath());
        object.put("Page Title:  ", pages.getTitle());
        newarArray.put(object);
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    resp.setContentType("text/html");
    resp.getWriter().write(newarArray.toString());

  }

  @Override
  protected void doPost(final SlingHttpServletRequest req,
      final SlingHttpServletResponse resp) throws IOException {

    final ResourceResolver resource = req.getResourceResolver();
    Page page = resource.adaptTo(PageManager.class)
        .getPage("/content/apple-replicate/language-masters/en/apple-replicate-home-page");
    Iterator<Page> childpage = page.listChildren();

    JSONArray newarArray = new JSONArray();
    while (childpage.hasNext()) {
      Page pages = childpage.next();
      JSONObject object = new JSONObject();
      try {
        object.put("Page path", pages.getPath());
        object.put("Page Title:  ", pages.getTitle());
        object.put("apple", "project");
        newarArray.put(object);
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    resp.setContentType("text/html");
    resp.getWriter().write(newarArray.toString());

  }

}
