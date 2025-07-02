package com.example.video_voting.model;

import com.example.video_voting.model.supporting.Vote;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(name = "youtube_id", nullable = false, length = 11)
  private String youtubeId;

  @Column(nullable = false)
  private Integer votes = 0;

  @Column(name = "total_votes", nullable = false)
  private Integer totalVotes = 0;

  public Video() {
  }

  public Video(String name, String youtubeId) {
    this.name = name;
    this.youtubeId = youtubeId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getYoutubeId() {
    return youtubeId;
  }

  public Integer getVotes() {
    return votes;
  }

  public void setVotes(Integer votes) {
    this.votes = votes;
  }

  public Integer getTotalVotes() {
    return totalVotes;
  }

  public void setTotalVotes(Integer totalVotes) {
    this.totalVotes = totalVotes;
  }

  public void castVote(Vote vote) {
    this.votes += vote.getDelta();
    this.totalVotes += 1;
  }

}
