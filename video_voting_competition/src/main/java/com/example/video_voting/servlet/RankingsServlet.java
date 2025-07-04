package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.model.supporting.HttpException;
import com.example.video_voting.service.VideoService;
import com.example.video_voting.supporting.IntegerParser;

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
  private static final int PAGE_BLOCK_SIZE = 5;

  private VideoService videoService;

  @Override
  public void init() {
    this.videoService = new VideoService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Integer currentPage = 1;

    String pageParam = req.getParameter("page");
    try {
      currentPage = IntegerParser.parse("page", pageParam);
    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
      return;
    }

    final int itemsPerPage = ITEMS_PER_PAGE;

    int totalItems = videoService.getCount();
    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

    currentPage = Math.max(1, Math.min(currentPage, totalPages));

    int blockIndex = (currentPage - 1) / PAGE_BLOCK_SIZE;
    int startPage = blockIndex * PAGE_BLOCK_SIZE + 1;
    int endPage = Math.min(startPage + PAGE_BLOCK_SIZE - 1, totalPages);
    boolean hasPrev = startPage > 1;
    boolean hasNext = endPage < totalPages;

    List<Video> videos = videoService.getByRanking(currentPage, itemsPerPage);

    req.setAttribute("videos", videos);
    req.setAttribute("currentPage", currentPage);
    req.setAttribute("startPage", startPage);
    req.setAttribute("endPage", endPage);
    req.setAttribute("hasPrevBlock", hasPrev);
    req.setAttribute("hasNextBlock", hasNext);
    req.getRequestDispatcher("/WEB-INF/views/ranking.jsp")
        .forward(req, resp);
  }
}
