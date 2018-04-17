package model.dao;

import model.interfaces.db.DbDelete;
import model.interfaces.db.DbInsert;
import model.interfaces.db.DbUpdata;
import model.bean.History;

import java.sql.*;

public class DaoHistory extends Dao  implements DbInsert, DbDelete, DbUpdata {
  private History wordHistory;

  public DaoHistory() {
  }

  public DaoHistory(History wordHistory) {
    this.wordHistory = wordHistory;
  }

  public History getWordHistory() {
    return wordHistory;
  }

  public void setWordHistory(History wordHistory) {
    this.wordHistory = wordHistory;
  }


  @Override
  public int dbDelete(){
    int result = 0;
    String sql = "delete from t_history where uid = ? and wid = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, wordHistory.getUid());
      pstmt.setInt(2, wordHistory.getWid());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeConn(conn, pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public int dbInsert() {
    int result = 0;
    String sql = "insert into t_history(uid, wid, addTime) values(?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, wordHistory.getUid());
      pstmt.setInt(2, wordHistory.getWid());
      pstmt.setTimestamp(3, wordHistory.getAddTime());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeConn(conn, pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Override
  public int dbUpdata(){
    int result = 0;
    String sql = "update t_history set addTime = ? where uid = ? and wid = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;


    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setTimestamp(1, wordHistory.getAddTime());
      pstmt.setInt(2, wordHistory.getUid());
      pstmt.setInt(3, wordHistory.getWid());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeConn(conn, pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

}
