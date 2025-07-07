package com.example.video_voting.servlet;

import com.example.video_voting.service.UserService;
import com.example.video_voting.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession(false);
    if (session != null && session.getAttribute("user") != null) {
      resp.sendRedirect(req.getContextPath() + "/");
      return;
    }

    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String rememberMe = req.getParameter("rememberMe");

    if (username == null || username.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
      req.setAttribute("error", "Username and password are required");
      req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
      return;
    }

    if (userService.authenticate(username, password)) {
      Optional<User> userOpt = userService.getByUsername(username);
      if (userOpt.isPresent()) {
        User user = userOpt.get();

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRoles());

        session.setMaxInactiveInterval(30 * 60);

        if (rememberMe != null && rememberMe.equals("on")) {
          Cookie usernameCookie = new Cookie("username", username);
          usernameCookie.setMaxAge(7 * 24 * 60 * 60);
          usernameCookie.setHttpOnly(true);
          usernameCookie.setSecure(req.isSecure());
          usernameCookie.setPath(req.getContextPath());
          resp.addCookie(usernameCookie);
        }

        resp.sendRedirect(req.getContextPath() + "/");
      } else {
        req.setAttribute("error", "Authentication failed");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
      }
    } else {
      req.setAttribute("error", "Invalid username or password");
      req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
  }
}
