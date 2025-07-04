package com.example.video_voting.supporting;

import com.example.video_voting.model.supporting.HttpException;

import jakarta.servlet.http.HttpServletResponse;

/**
 * IntegerParser
 */
public class IntegerParser {

  public static Integer parse(String key, String value) throws HttpException {
    if (value.isEmpty() || value.trim().isEmpty()) {
      System.err.println("ERROR: '" + key + "' paramter is missing");
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, key + " paramter is missing.");
    }

    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      System.err.println("ERROR: Failet to parse '" + key + "' value " + value + " " + e.getMessage());
      throw new HttpException(HttpServletResponse.SC_BAD_REQUEST, key + " parameter format is missing.");
    }
  }
}
