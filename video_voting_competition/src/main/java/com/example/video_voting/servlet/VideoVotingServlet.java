package com.example.video_voting.servlet;

import java.io.IOException;

import com.example.video_voting.model.supporting.HttpException;
import com.example.video_voting.service.VideoService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * VoteServlet
 */
@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VideoVotingServlet extends HttpServlet {

  private VideoService videoService;

  @Override
  public void init() throws ServletException {
    super.init();
    this.videoService = new VideoService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Long votedVideoId, nonVotedVideoID;
    try {
      votedVideoId = parseVideoParameter(req.getParameter("votedId"));
      nonVotedVideoID = parseVideoParameter(req.getParameter("otherId"));
    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
      return;
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error");
      System.err.println("Error: failed to parse parameters");
      return;
    }

    try {
      videoService.updateVotesForVideo(votedVideoId, nonVotedVideoID);

      req.setAttribute("voteStatus", "success");
      req.setAttribute("votedVideoId", votedVideoId);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/random-videos");
      dispatcher.forward(req, resp);

    } catch (HttpException e) {
      resp.sendError(e.getStatusCode(), e.getMessage());
      System.err.println(e.getMessage());
      return;

    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
      System.err.println("Unexpected error in doPost: " + e.getMessage());
      return;
    }
  }

  private Long parseVideoParameter(String idValue) throws HttpException {
    if (idValue == null || idValue.trim().isEmpty()) {
      System.err.println("Error: 'videoId' is missing");
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, "Video id parameter is missing");
    }

    try {
      return Long.parseLong(idValue);
    } catch (NumberFormatException e) {
      System.err.println("Error parsing 'videoId': " + idValue + " - " + e.getMessage());
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, "Invalid vidoe ID format");
    }

  }

  private Boolean parseVoteParameter(String voteValue) throws HttpException {
    if (voteValue == null || voteValue.trim().isEmpty()) {
      System.err.println("Error: 'vote' paramter is missing.");
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, "Vote parameter is missing");
    }

    if ("true".equalsIgnoreCase(voteValue)) {
      return true;
    } else if ("false".equalsIgnoreCase(voteValue)) {
      return false;
    } else {
      System.err.println("Error: Invalid 'vote' parameter value: " + voteValue);
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST,
          "Invalid vote value. Must be 'true' or 'false'");
    }

  }

}
