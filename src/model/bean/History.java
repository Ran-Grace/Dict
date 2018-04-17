package model.bean;

import model.dao.Dao;

import java.sql.Timestamp;

public class History{
  private int uid = 0, wid = 0;
  private Timestamp addTime;

  public History() {
  }


  //增加
  public History(int uid, int wid, Timestamp addTime) {
    this.uid = uid;
    this.wid = wid;
    this.addTime = addTime;
  }


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public int getWid() {
    return wid;
  }

  public void setWid(int wid) {
    this.wid = wid;
  }

  public Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(Timestamp addTime) {
    this.addTime = addTime;
  }
}
