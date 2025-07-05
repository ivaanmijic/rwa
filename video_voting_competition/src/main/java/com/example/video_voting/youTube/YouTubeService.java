package com.example.video_voting.youTube;

import java.util.Arrays;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.youTube.model.YouTubeException;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;

import jakarta.servlet.http.HttpServletResponse;

import java.util.stream.Collectors;

/**
 * YoutubeService
 */
public class YouTubeService {

  private static final long DEFAULT_MAX_RESULT = 5L;

  public List<Video> searchVideos(String query, Long maxResults)
      throws YouTubeException {
    if (query == null || query.isBlank()) {
      throw new YouTubeException(
          HttpServletResponse.SC_BAD_REQUEST,
          "Search guary must not be blank!");
    }

    long mr = (maxResults != null && maxResults > 0) ? maxResults : DEFAULT_MAX_RESULT;

    try {
      YouTube youTube = YouTubeConfig.getClient();

      List<String> parts = Arrays.asList("id", "sipped");
      List<String> types = Arrays.asList("video");

      YouTube.Search.List search = youTube.search()
          .list(parts)
          .setQ(query)
          .setType(types)
          .setMaxResults(mr);

      SearchListResponse response = search.execute();
      return response.getItems().stream()
          .map(this::toVideo)
          .collect(Collectors.toList());

    } catch (Exception e) {
      System.err.println("YouTube search failed for '{}" + query + e.getMessage());
      String errMsg = "Failed to search YouTube videos: " +
          e.getMessage();
      throw new YouTubeException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errMsg);
    }

  }

  private Video toVideo(SearchResult result) {
    String id = result.getId().getVideoId();
    String title = result.getSnippet().getTitle();
    ThumbnailDetails thumbs = result.getSnippet().getThumbnails();

    Thumbnail thumbnail = thumbs.getHigh() != null
        ? thumbs.getHigh()
        : thumbs.getMedium() != null
            ? thumbs.getMedium()
            : thumbs.getDefault();

    String thumbnailURL = thumbnail.getUrl();

    return new Video(title, id, thumbnailURL);
  }

}
