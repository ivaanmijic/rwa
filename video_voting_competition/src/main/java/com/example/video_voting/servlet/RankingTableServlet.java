package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.model.supporting.HttpException;
import com.example.video_voting.service.VideoService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RankingTableServlet
 */

@WebServlet(name = "RankingTableServlet", urlPatterns = "/ranking-table")
public class RankingTableServlet extends HttpServlet {

  private VideoService videoService;
  private Gson gson;

  @Override
  public void init() throws ServletException {
    super.init();
    this.videoService = new VideoService();
    this.gson = new Gson();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer pageNumber = parseInteger("page", req.getParameter("page"));

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    try {
      List<Video> videos = videoService.getVideosFromPage(pageNumber);
      String json = gson.toJson(videos);
      resp.getWriter().write(json);
    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Failed to load videos: " + e.getMessage());
    }

  }

  private Integer parseInteger(String key, String value) throws HttpException {
    if (value.isEmpty() || value.trim().isEmpty()) {
      System.err.println("ERROR: '" + key + "' paramter is missing");
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, key + " paramter is missing.");
    }

    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      System.err.println("ERROR: Failet to parse '" + key + "' value " + value + " " + e.getMessage());
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, key + " parameter format is missing.");
    }
  }

}
