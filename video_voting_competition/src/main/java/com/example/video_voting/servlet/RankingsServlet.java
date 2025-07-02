package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.service.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * RankingsServlet
 */
@WebServlet("/ranking")
public class RankingsServlet extends HttpServlet {
  private static final int ITEMS_PER_PAGE = 20;
  private static final int PAGE_BLOCK_SIZE = 10;

  private VideoService videoService;

  @Override
  public void init() {
    this.videoService = new VideoService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int currentPage = 1;
    String pageParam = req.getParameter("page");
    if (pageParam != null) {
      try {
        currentPage = Integer.parseInt(pageParam);
      } catch (NumberFormatException ignored) {
      }
    }

    int totalItems = videoService.getCount();
    int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);
    int blockIndex = (currentPage - 1) / PAGE_BLOCK_SIZE;
    int startPage = blockIndex * PAGE_BLOCK_SIZE + 1;
    int endPage = Math.min(startPage + PAGE_BLOCK_SIZE - 1, totalPages);

    boolean hasPrevBlock = startPage > 1;
    boolean hasNextBlock = endPage < totalPages;

    int offset = (currentPage - 1) * ITEMS_PER_PAGE;
    List<Video> videos = videoService.getByRanking(offset, ITEMS_PER_PAGE);

    req.setAttribute("videos", videos);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("startPage", startPage);
    req.setAttribute("endPage", endPage);
    req.setAttribute("hasPrevBlock", hasPrevBlock);
    req.setAttribute("hasNextBlock", hasNextBlock);

    req.getRequestDispatcher("/WEB-INF/views/ranking.jsp")
        .forward(req, resp);
  }
}
