package com.example.video_voting.cli;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.video_voting.model.User;
import com.example.video_voting.model.supporting.Role;
import com.example.video_voting.service.UserService;
import com.example.video_voting.util.JPAUtil;

/**
 * VideoDataGenerator
 */
public class AdminDataGenerator {

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
    UserService userService = new UserService();

    Set<Role> roles = new HashSet<Role>();

    roles.add(Role.USER);
    User user1 = new User("Ivan", "Mijic", "ivaanmijic", "ivan.mijic@fet.ba", "ivan123", roles);
    userService.createUser(user1);

    var roles2 = roles;
    roles2.add(Role.ADMIN);
    User user2 = new User("Admin", "Dimitrije", "admin", "admin@example.com", "admin123", roles2);
    userService.createUser(user2);

    // Faker faker = new Faker();
    // Random random = new Random();
    // for (int i = 0; i < 100000; i++) {
    // String id = IDS.get(random.nextInt(IDS.size()));
    // String title = faker.book().title();
    // Video video = new Video(title, id);
    // videoService.saveVideo(video);
    // }

  }

  private static String getThumbnailURL(String videoId) {
    return String.format("https://img.youtube.com/vi/%s/hqdefault.jpg", videoId);
  }

}
