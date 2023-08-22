package com.replicate.apple.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "apple-replicate/components/apple-formcomponent", methods = {
    HttpConstants.METHOD_GET }

)

public class SearchServlet extends SlingSafeMethodsServlet {

  private static final Logger log = LoggerFactory.getLogger(SearchServlet.class);

  @Override
  public void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {

    try {
      ResourceResolver resourceResolver = req.getResourceResolver();
      Session session = resourceResolver.adaptTo(Session.class);
      String searchtext = req.getRequestParameter("id").getString();
      // resp.setContentType("text/plain");
      // resp.getWriter().write("Input Search word is: " + searchtext);
      List<String> set = getResult(searchtext, session, resourceResolver);
      // resp.setContentType("text/html");
      // resp.getWriter().write("Search Result = " + " \n" + set);
      JSONArray newarArray = new JSONArray();
      for (String string : set) {
        JSONObject object = new JSONObject();
        object.put("Page path", string);
        newarArray.put(object);
      }
      resp.setContentType("application/json");
      resp.getWriter().write(newarArray.toString());
    } catch (Exception e) {
    }

  }

  public List<String> getResult(String param, Session session, ResourceResolver resourceResolver) {
    List<String> resultSet = new ArrayList<String>();
    QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
    try {
      Map<String, String> map = new HashMap<String, String>();
      map.put("group.1_path", "/content/apple-replicate");
      map.put("group.2_path", "/apps/apple-replicate");
      map.put("group.p.or", "true");
      map.put("fulltext", param);
      map.put("p.limit", "-1");
      Query query = builder.createQuery(PredicateGroup.create(map), session);
      SearchResult searchResult = query.getResult();
      if (searchResult != null) {
        for (Hit hit : searchResult.getHits())
          resultSet.add(hit.getPath());
      }
    } catch (Exception e) {
    }
    return resultSet;

  }

}
