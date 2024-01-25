package com.replicate.apple.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.replicate.apple.core.service.SearchService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jcr.Session;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
  resourceTypes = "apple-replicate/components/page",
  methods = { HttpConstants.METHOD_GET }
)
// : resource type : apple-replicate/components/apple-formcomponent
// @SlingServletPaths(
// value ="/content/apple-replicate/language-masters/en/search-page.html"
// )

public class SearchServlet extends SlingSafeMethodsServlet {
  @Reference
  SearchService searchService;

  private static final Logger log = LoggerFactory.getLogger(
    SearchServlet.class
  );

  @Override
  public void doGet(
    final SlingHttpServletRequest req,
    final SlingHttpServletResponse resp
  )
    throws IOException {
    try {
      ResourceResolver resourceResolver = req.getResourceResolver();
      Session session = resourceResolver.adaptTo(Session.class);
      String searchtext = req.getRequestParameter("id").getString();

      List<Map<String, String>> set = getResult(
        searchtext,
        session,
        resourceResolver
      );
      

      searchService.setListResults(set);

      for (Map<String, String> string : set) {
        log.info(
          "Title " + string.get("title") + " \n path" + string.get("path")
        );
      }
      // path to send it to the searchService

      JSONArray newarArray = new JSONArray();
      for (Map<String, String> string : set) {
        JSONObject object = new JSONObject();
        object.put("Page path", string.get("path"));
        object.put("Page Description", string.get("description"));
        newarArray.put(object);
      }
      resp.setContentType("application/json");
      resp.getWriter().write(newarArray.toString());

      resp.sendRedirect(
        "/content/apple-replicate/language-masters/en/search-page.html?id=" +
        req.getRequestParameter("id").getString()
      );
    } catch (Exception e) {}
  }

  public List<Map<String, String>> getResult(
    String searchtext,
    Session session,
    ResourceResolver resourceResolver
  ) {
    List<String> resultSet = new ArrayList<String>();
    List<Map<String, String>> resultsMap = new ArrayList<>();

    QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
    try {
      Map<String, String> map = new HashMap<String, String>();
      map.put("fulltext", searchtext);
      map.put("group.1_path", "/content/apple-replicate");
      map.put("group.2_path", "/apps/apple-replicate");
      map.put("group.p.or", "true");
      map.put("type", "cq:Page");
      map.put("orderby", "@jcr:content/cq:lastModified");
      map.put("orderby.sort", "desc");
      map.put("p.limit", "-1");
      map.put("p.hits", "selective");
      map.put("p.properties", "jcr:content/jcr:title");

      Query query = builder.createQuery(PredicateGroup.create(map), session);
      SearchResult searchResult = query.getResult();
      if (searchResult != null) {
        for (Hit hit : searchResult.getHits()) {
          Map<String, String> resultmap = new HashMap<>();
          resultmap.put(
            "title",
            hit
              .getResource()
              .getValueMap()
              .get("jcr:content/jcr:title", String.class)
          );
          resultmap.put("path", hit.getPath());
          resultmap.put(
            "description",
            hit
              .getResource()
              .getValueMap()
              .get("jcr:content/jcr:description", String.class)
          );
          resultmap.put(
            "modifieddate",
            hit
              .getResource()
              .getValueMap()
              .get("jcr:content/cq:lastModified", String.class)
          );

          resultsMap.add(resultmap);

          resultSet.add(
            "\n Title is: " +
            hit
              .getResource()
              .getValueMap()
              .get("jcr:content/jcr:title", String.class) +
            "\n Path is:  " +
            hit.getPath()
          );
        }
      }
    } catch (Exception e) {}
    return resultsMap;
  }
}
