package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.model.supporting.HttpException;
import com.example.video_voting.service.VideoService;
import com.example.video_voting.supporting.IntegerParser;
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

  private static final int DEFAULT_PAGE_NUMBER = 1;
  private static final int DEFAULT_PAGE_SIZE = 20;

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

    Integer pageNumber = DEFAULT_PAGE_NUMBER;
    Integer pageSize = DEFAULT_PAGE_SIZE;

    String pageParam = req.getParameter("page");
    String limitParam = req.getParameter("limit");

    try {
      pageNumber = IntegerParser.parse("page", pageParam);
      pageSize = IntegerParser.parse("limit", limitParam);
    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
      return;
    }

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    try {
      List<Video> videos = videoService.getByRanking(pageNumber, pageSize);
      String json = gson.toJson(videos);
      resp.getWriter().write(json);

    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Failed to load videos: " + e.getMessage());
    }

  }

}
