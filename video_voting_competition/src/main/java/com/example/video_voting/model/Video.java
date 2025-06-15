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

  @Column(nullable = false, length = 512)
  private String url;

  @Column(name = "thumbnail_url", length = 512)
  private String thumbnailURL;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private Integer votes = 0;

  @Column(name = "total_votes", nullable = false)
  private Integer totalVotes = 0;

  public Video() {
  }

  public Video(
      String name,
      String url,
      String thumbnailURL,
      String description) {
    this.name = name;
    this.url = url;
    this.thumbnailURL = thumbnailURL;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getURL() {
    return url;
  }

  public void setURL(String url) {
    this.url = url;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  public void setThumbnailURL(String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
