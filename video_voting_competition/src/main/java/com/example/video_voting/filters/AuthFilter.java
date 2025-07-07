package com.example.video_voting.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AuthFilter
 */
@WebFilter(urlPatterns = {
    "/upload",
    "/admin/*"
})
public class AuthFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;

    HttpSession session = httpReq.getSession(false);

    Boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

    if (isLoggedIn) {
      chain.doFilter(request, response);
    } else {
      httpResp.sendRedirect(httpReq.getContextPath() + "/login");
    }

  }

  @Override
  public void destroy() {
  }

}
