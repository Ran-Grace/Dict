package model.bean;

import java.sql.Timestamp;

public class WordHistory {
  private int uid = 0;
  private int wid = 0;
  private String word, mean;
  private Timestamp addTime;

  public WordHistory() {
  }

  public Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(Timestamp addTime) {
    this.addTime = addTime;
  }

  public int getWid() {
    return wid;
  }

  public void setWid(int wid) {
    this.wid = wid;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getMean() {
    return mean;
  }

  public void setMean(String mean) {
    this.mean = mean;
  }
}
