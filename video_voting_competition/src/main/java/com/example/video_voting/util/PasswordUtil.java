package com.example.video_voting.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * PasswordUtil
 */
public class PasswordUtil {

  public static String hashPassword(String rawPassword) {
    return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
  }

  public static Boolean checkPassword(String rawPassword, String hashed) {
    return BCrypt.checkpw(rawPassword, hashed);
  }
}
