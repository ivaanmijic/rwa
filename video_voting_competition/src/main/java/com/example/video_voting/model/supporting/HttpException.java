package com.example.video_voting.model.supporting;

/**
 * HTTPException
 */
public class HttpException extends RuntimeException {

  private final int statusCode;

  public HttpException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpException(int statusCode) {
    this(statusCode, "HTTP Error");
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  public int getStatusCode() {
    return statusCode;
  }

}
