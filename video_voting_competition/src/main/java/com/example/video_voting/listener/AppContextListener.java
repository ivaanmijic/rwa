package com.example.video_voting.listener;

import com.example.video_voting.util.JPAUtil;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * AppContextListener
 */
@WebListener
public class AppContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    JPAUtil.getEMF();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    JPAUtil.shutdown();
  }

}
