package com.example.video_voting.cli;

import net.datafaker.Faker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.video_voting.model.Video;
import com.example.video_voting.service.VideoService;
import com.example.video_voting.util.JPAUtil;

/**
 * VideoDataGenerator
 */
public class VideoDataGenerator {

  private static final List<String> IDS = Arrays.asList(
      "srVPLrmlBJY",
      "IGvDBuKYL_I",
      "K-K_TwCX-jw",
      "RGEqGaqtuVs",
      "wpzWY0m0F1w",
      "zRwl4B0NYyg",
      "qETKhWzCLyk",
      "IxK43kRt_p0",
      "k0PQFzh8b-w",
      "I1cCUUhiZgc");

  private static final List<String> TITLES = Arrays.asList(
      "Creative Commons Music Compilation",
      "Relaxing Nature Sounds - Free to Use",
      "Hall of the Mountain King - Kevin MacLeod",
      "Timelapse Footage - CC License",
      "Ambient Background Music - Free Use",
      "Drawing Timelapse - Creative Commons",
      "Piano Chill Beats - CC Licensed",
      "Guitar Background Music - Audionautix",
      "Cinematic Trailer Music - Free to Embed",
      "Tutorial Intro Template - Public Use");

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::shutdown));

    VideoService videoService = new VideoService();

    for (int i = 0; i < 10; i++) {
      Video video = new Video(TITLES.get(i), IDS.get(i));
      videoService.saveVideo(video);
    }

    Faker faker = new Faker();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      String id = IDS.get(random.nextInt(IDS.size()));
      String title = faker.book().title();
      Video video = new Video(title, id);
      videoService.saveVideo(video);
    }

  }

}
