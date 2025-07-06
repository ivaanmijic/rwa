package com.example.video_voting.youTube;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.hibernate.cfg.Environment;

import com.example.video_voting.youTube.model.YouTubeException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;

import jakarta.servlet.http.HttpServletResponse;

/**
 * YoutubeConfig
 */
public final class YouTubeConfig {

  private static final String API_KEY = System.getenv("YOUTUBE_KEY");
  private static final String APP_NAME = "Video Voting Competition";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static volatile YouTube youtube;

  private YouTubeConfig() {
    if (API_KEY == null) {
      System.err.println("Environment variable YOUTUBE_KEY not set!");
      throw new YouTubeException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Failed to initialize Youtube Service");
    }
  }

  public static YouTube getClient()
      throws GeneralSecurityException, IOException {
    if (youtube == null) {
      synchronized (YouTubeConfig.class) {
        if (youtube == null) {
          youtube = new YouTube.Builder(
              GoogleNetHttpTransport.newTrustedTransport(),
              JSON_FACTORY,
              null)
              .setApplicationName(APP_NAME)
              .setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY))
              .build();
        }
      }
    }
    return youtube;
  }

}
