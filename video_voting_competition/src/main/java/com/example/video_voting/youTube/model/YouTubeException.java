package com.example.video_voting.youTube.model;

/**
 * YouTubeException
 */
public class YouTubeException extends RuntimeException {

  private final int statusCode;

  public YouTubeException(int statusCode, String msg) {
    super(msg);
    this.statusCode = statusCode;
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  public int getStatusCode() {
    return statusCode;
  }

}
