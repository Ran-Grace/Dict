package model.dao;

import model.interfaces.db.DbSelect;
import model.bean.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoWord extends Dao {
  private Word word;

  public DaoWord() {
  }

  public DaoWord(Word word) {
    this.word = word;
  }


  //有结果时返回结果， 没有结果返回null
  public Word getWord() {
    int rowNum = 0;
    String sql = "select * from t_word where word = ?";

    int id = 0;
    String mean = "";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, word.getWord());
      result = pstmt.executeQuery();
      result.last();
      rowNum = result.getRow();
      id = result.getInt("id");
      mean = result.getString("mean");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeConn(conn, pstmt, result);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if(rowNum == 1){
      word.setWid(id);
      word.setMean(mean);
      return word;
    }else {
      return null;
    }
  }
}
