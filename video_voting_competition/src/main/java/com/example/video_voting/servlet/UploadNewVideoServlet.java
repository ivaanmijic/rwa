package com.example.video_voting.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.video_voting.model.Video;
import com.example.video_voting.service.VideoService;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolationException;

/**
 * UploadNewVideoServlet
 */
@WebServlet("/upload")
public class UploadNewVideoServlet extends HttpServlet {

  private VideoService videoService;

  private static final Pattern YOUTUBE_EMBED_PATTERN = Pattern
      .compile("(?:youtube\\.com/embed/|youtu\\.be/)([A-Za-z0-9_-]{11})");

  private static final Pattern IMAGE_URL_PATTERN = Pattern.compile(
      "^https?://" +
          "[^\\s\"'<>]+?" + "\\.(?:png|jpe?g|gif|bmp|webp|svg)" +
          "(?:\\?[^\\s\"'<>]*)?" + "$",
      Pattern.CASE_INSENSITIVE);

  @Override
  public void init() throws ServletException {
    videoService = new VideoService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String embedUrl = req.getParameter("url");
    String title = req.getParameter("title");
    String customImage = req.getParameter("customImage");

    if (embedUrl == null || embedUrl.trim().isEmpty() ||
        title == null || title.trim().isEmpty()) {
      req.setAttribute("error", "Embed URL and Title are required");
      req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
      return;
    }

    if (customImage != null && !customImage.trim().isEmpty()) {
      if (!isValidImageUrl(customImage)) {
        req.setAttribute("error", "Invalid URL for Custom Image");
        req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
        return;
      }
    }

    String youtubeId = parseYoutubeId(embedUrl);
    if (youtubeId == null) {
      req.setAttribute("error", "Invalid Embed URL.");
      req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
      return;
    }

    String thumbnailUrl = getThumbnailURL(youtubeId);

    Video video = new Video(title, youtubeId, thumbnailUrl, customImage);

    try {
      videoService.saveVideo(video);
    } catch (PersistenceException persistenceException) {
      if (persistenceException.getCause() instanceof ConstraintViolationException) {
        req.setAttribute("error", "Already uploaded video with the Embed URL");
      } else {
        req.setAttribute("error", persistenceException.getMessage());
      }
      req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
      return;
    } catch (Exception e) {
      req.setAttribute("error", e.getMessage());
      req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
      return;
    }

    HttpSession session = req.getSession();
    session.setAttribute("info", "Successfully uploaded new video");
    resp.sendRedirect("/index.jsp");
  }

  private String parseYoutubeId(String url) {
    if (url == null)
      return null;
    Matcher matcher = YOUTUBE_EMBED_PATTERN.matcher(url);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

  private String getThumbnailURL(String videoId) {
    return String.format("https://img.youtube.com/vi/%s/hqdefault.jpg", videoId);
  }

  private boolean isValidImageUrl(String url) {
    Matcher matcher = IMAGE_URL_PATTERN.matcher(url);
    return matcher.matches();
  }

}
