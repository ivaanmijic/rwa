package com.example.video_voting.logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * AppLogger
 */
public class AppLogger {

  private static final Logger LOGGER = Logger.getLogger("com.example.video_voting");

  static {
    try {
      FileHandler fh = new FileHandler("logs/app.log", 10_000_000, 5, true);

      fh.setFormatter(new SimpleFormatter());
      fh.setLevel(Level.INFO);

      LOGGER.addHandler(fh);
      LOGGER.setUseParentHandlers(false);
      LOGGER.setLevel(Level.INFO);
    } catch (Exception e) {
      System.err.println("Faile to initialize log file handler: " + e);
    }
  }

  public static Logger getLogger() {
    return LOGGER;
  }

}
