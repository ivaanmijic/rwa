package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.Optional;

import com.example.video_voting.model.User;
import com.example.video_voting.service.UserService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LoginServlet
 */
@WebServlet("/auth-status")
public class AuthStatusServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    HttpSession session = req.getSession(false);

    if (session != null && session.getAttribute("user") != null) {
      Boolean isLoggedIn = true;
      resp.getWriter().write("{\"loggedIn\": " + isLoggedIn + "}");
    }

  }

}
