package com.example.video_voting.model.supporting;

public enum Vote {
  POSITIVE(+1),
  NONE(0);

  private final int delta;

  Vote(int delta) {
    this.delta = delta;
  }

  public int getDelta() {
    return delta;
  }
}
