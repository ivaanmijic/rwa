package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import com.example.video_voting.model.User;
import com.example.video_voting.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * GetUserServlet
 */
@WebServlet("/current-user")
public class GetUserServlet extends HttpServlet {

  private UserService userService;
  private Gson gson;

  @Override
  public void init() throws ServletException {
    userService = new UserService();
    gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String username = Arrays
        .stream(req.getCookies() != null ? req.getCookies() : new Cookie[0])
        .filter(c -> "username".equals(c.getName()))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(null);

    if (username == null || username.trim().isEmpty()) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\":\"no username cookie\"}");
      return;
    }

    Optional<User> userOpt = userService.getByUsername(username);
    if (!userOpt.isPresent()) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\":\"user not found\"}");
      return;

    }

    User user = userOpt.get();
    resp.setContentType("application/json");
    resp.getWriter().write(gson.toJson(user));

  }

}
