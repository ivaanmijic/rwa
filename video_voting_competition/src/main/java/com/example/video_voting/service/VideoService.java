package com.example.video_voting.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.video_voting.model.Video;
import com.example.video_voting.model.supporting.HttpException;
import com.example.video_voting.model.supporting.Vote;
import com.example.video_voting.repository.VideoRepository;

/**
 * VideoService
 */
public class VideoService {

  private final VideoRepository repository;

  public VideoService() {
    this.repository = new VideoRepository();
  }

  public List<Video> getRandomVideos() {
    return repository.selectRandom();
  }

  public void updateVotesForVideo(Long votedVideoId, Long otherVideoId) throws Exception {
    Video votedVideo = findVideoOrThrow(votedVideoId);
    Video otherVideo = findVideoOrThrow(otherVideoId);

    votedVideo.castVote(Vote.POSITIVE);
    otherVideo.castVote(Vote.NONE);

    repository.updateVotes(votedVideo);
    repository.updateVotes(otherVideo);
  }

  public List<Video> getVideosFromPage(Integer page) {
    Integer offset;
    Integer limit;

    if (page == 1) {
      offset = 0;
      limit = 5;
    } else {
      offset = 5 + (page - 2) * 10;
      limit = 10;
    }

    return repository.SelectByRankFromOffsetWithLimit(offset, limit);
  }

  public void saveVideo(Video video) {
    repository.insert(video);
  }

  public void castVote(Video video, Vote vote) {
    video.castVote(vote);
    repository.updateVotes(video);
  }

  private Video findVideoOrThrow(Long id) throws HttpException {
    return repository.selectById(id)
        .orElseThrow(() -> new HttpException(404,
            "Video with id '" + id + "' not found"));
  }

}
