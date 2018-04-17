package model.bean;

public class User {
  private int id = 0;
  private String username;
  private String password;

  public User() {
  }

  //修改， 删除
  public User(int id) {
    this.id = id;
  }
  //增加
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
