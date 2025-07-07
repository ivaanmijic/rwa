package com.example.video_voting.servlet;

import com.example.video_voting.service.UserService;
import com.google.gson.Gson;
import com.example.video_voting.model.User;
import java.util.List;
import java.util.Set;

import com.example.video_voting.model.supporting.Role;

import com.example.video_voting.model.supporting.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UsersServlet
 */
@WebServlet("/admin/users")
public class UsersServlet extends HttpServlet {

  private UserService userService;
  private Gson gson;

  private static class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Set<Role> roles;

    public UserResponse(Long id, String firstname, String lastname, Set<Role> roles) {
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.roles = roles;
    }

    public Long getId() {
      return id;
    }

    public Set<Role> getRoles() {
      return roles;
    }

    public String getFirstname() {
      return firstname;
    }

    public String getLastname() {
      return lastname;
    }
  }

  @Override
  public void init() throws ServletException {
    userService = new UserService();
    gson = new Gson();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    try {
      List<User> users = userService.getNonAdminUsers();

      List<UserResponse> responseList = users.stream()
          .map(user -> new UserResponse(user.getId(), user.getFirstname(), user.getLastname(),
              user.getRoles()))
          .toList();

      String json = gson.toJson(responseList);

      resp.getWriter().write(json);
    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Failed to load videos: " + e.getMessage());
    }

  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long userId = Long.parseLong(req.getParameter("userId"));
      userService.deleteUser(userId);
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

}
