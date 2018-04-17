package model.bean;

public class Word {
  private int wid = 0;
  private String word;
  private String mean;

  public Word() {
  }

  public Word(int wid) {
    this.wid = wid;
  }

  public Word(String word, String mean) {
    this.word = word;
    this.mean = mean;
  }

  public Word(String word) {
    this.word = word;
  }

  public int getWid() {
    return wid;
  }

  public void setWid(int wid) {
    this.wid = wid;
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
