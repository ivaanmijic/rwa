package com.example.video_voting.cli;

import java.util.Arrays;
import java.util.List;

import com.example.video_voting.model.Video;
import com.example.video_voting.service.VideoService;
import com.example.video_voting.util.JPAUtil;

/**
 * VideoDataGenerator
 */
public class VideoDataGenerator {

  private static final List<String> URLS = Arrays.asList(
      "https://www.youtube.com/embed/e-P5IFTqB98",
      "https://www.youtube.com/embed/G4H1N_yXBiA",
      "https://www.youtube.com/embed/2pp17E4E-O8",
      "https://www.youtube.com/embed/vo4pMVb0R6M",
      "https://www.youtube.com/embed/21eFwbb48sE",
      "https://www.youtube.com/embed/p7HKvqRI_Bo",
      "https://www.youtube.com/embed/LWULB9Aoopc",
      "https://www.youtube.com/embed/ZTrKZrnAJfI",
      "https://www.youtube.com/embed/yAwNfUkPpi4",
      "https://www.youtube.com/embed/ZyYqyYAKGC0");

  private static final List<String> THUMBNAILS = Arrays.asList(
      "https://img.youtube.com/vi/e-P5IFTqB98/hqdefault.jpg",
      "https://img.youtube.com/vi/G4H1N_yXBiA/hqdefault.jpg",
      "https://img.youtube.com/vi/2pp17E4E-O8/hqdefault.jpg",
      "https://img.youtube.com/vi/vo4pMVb0R6M/hqdefault.jpg",
      "https://img.youtube.com/vi/21eFwbb48sE/hqdefault.jpg",
      "https://img.youtube.com/vi/p7HKvqRI_Bo/hqdefault.jpg",
      "https://img.youtube.com/vi/LWULB9Aoopc/hqdefault.jpg",
      "https://img.youtube.com/vi/ZTrKZrnAJfI/hqdefault.jpg",
      "https://img.youtube.com/vi/yAwNfUkPpi4/hqdefault.jpg",
      "https://img.youtube.com/vi/ZyYqyYAKGC0/hqdefault.jpg");

  private static final List<String> TITLES = Arrays.asList(
      "Black Holes Explained – From Birth to Death",
      "Causes and Effects of Climate Change | National Geographic",
      "Genome Editing with CRISPR-Cas9",
      "Intro to Psychology: Crash Course Psychology #1",
      "Who Invented the Internet? And Why?",
      "How does the stock market work? - Oliver Elfenbaum",
      "Why do we sleep? | Russell Foster",
      "The Beauty of Wild Nature | National Geographic",
      "Photographing Southern Namibia 4K",
      "Time Is But a Stubborn Illusion - Sneak Peek | Genius");

  private static final List<String> DESCRIPTIONS = Arrays.asList(
      "An in-depth look at how black holes form, evolve, and eventually evaporate through Hawking radiation.",
      "Explore the causes and far-reaching effects of climate change, presented by National Geographic.",
      "A primer on CRISPR-Cas9 genome editing: mechanism, applications, and ethical considerations.",
      "Crash Course Psychology introduction: key concepts and foundational studies in the field.",
      "A historical overview of the Internet's invention and the motivations behind its creation.",
      "Oliver Elfenbaum explains the mechanics of the stock market and how investors can participate.",
      "Neuroscientist Russell Foster discusses the importance of sleep and its impact on health.",
      "National Geographic's visual journey into the stunning landscapes of wild nature.",
      "A 4K visual tour of southern Namibia, highlighting unique geological and cultural landmarks.",
      "Genius presents a sneak peek discussion on the concept of time as an illusion.");

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::shutdown));

    VideoService videoService = new VideoService();

    for (int i = 0; i < 10; i++) {
      Video video = new Video(TITLES.get(i), URLS.get(i), THUMBNAILS.get(i), DESCRIPTIONS.get(i));
      videoService.saveVideo(video);
    }

  }

}
