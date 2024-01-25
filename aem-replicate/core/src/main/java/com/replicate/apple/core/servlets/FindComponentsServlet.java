package com.replicate.apple.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
  service = Servlet.class,
  property = { "sling.servlet.paths=/bin/findcomponent" }
)
public class FindComponentsServlet extends SlingSafeMethodsServlet {

  @Override
  protected void doGet(
    SlingHttpServletRequest request,SlingHttpServletResponse response
  )
    throws ServletException, IOException {
    response.setContentType("text/plain");

    // Obtain the resource resolver
    ResourceResolver resolver = request.getResourceResolver();

    // Get the page resource based on the request URL
    Resource pageResource = resolver.resolve(request.getRequestURI());
    response.getWriter().println("page requirement" + pageResource.getName());

    // Check if the resource is a valid page
    if (pageResource != null && ResourceUtil.isA(pageResource, "cq:Page")) {
      // Iterate through the child resources of the page
      Iterator<Resource> childResources = pageResource.listChildren();
      while (childResources.hasNext()) {
        Resource childResource = childResources.next();

        // Check if the child resource is a component
        if (ResourceUtil.isA(childResource, "cq:Component")) {
          response
            .getWriter()
            .println("Component found: " + childResource.getName());
        }
      }
    } else {
      response.getWriter().println("Invalid page resource");
    }
  }
}
