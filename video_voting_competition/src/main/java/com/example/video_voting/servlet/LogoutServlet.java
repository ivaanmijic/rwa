package com.example.video_voting.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("username".equals(cookie.getName())) {
          cookie.setValue("");
          cookie.setPath(req.getContextPath());
          cookie.setMaxAge(0);
          cookie.setHttpOnly(true);
          cookie.setSecure(req.isSecure());
          resp.addCookie(cookie);
          break;
        }
      }
    }

    resp.sendRedirect(req.getContextPath() + "/login");

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

}
