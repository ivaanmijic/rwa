package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.service.VideoService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RadnomVideosServlet
 */
@WebServlet(name = "RandomVideosServlet", urlPatterns = "/random-videos")
public class RandomVideosServlet extends HttpServlet {

  private VideoService videoService;
  private Gson gson;

  @Override
  public void init() throws ServletException {
    super.init();
    this.videoService = new VideoService();
    gson = new Gson();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    handleRadnomVideosRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    handleRadnomVideosRequest(req, resp);
  }

  private void handleRadnomVideosRequest(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    try {
      List<Video> randomVideos = videoService.getRandomVideos();
      String json = gson.toJson(randomVideos);
      resp.getWriter().write(json);
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Failed to load videos: " + e.getMessage());
    }
  }

}
