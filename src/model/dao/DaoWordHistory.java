package model.dao;

import jdk.nashorn.internal.runtime.JSONFunctions;
import model.bean.WordHistory;
import model.interfaces.db.DbDelete;
import model.interfaces.db.DbInsert;
import model.interfaces.db.DbSelect;
import model.interfaces.db.DbUpdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DaoWordHistory extends Dao {
  private int uid;


  public DaoWordHistory() {
  }

  public DaoWordHistory(int uid) {
    this.uid = uid;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public List<Map> getList() {
    List<Map> list = new ArrayList<>();

    String sql = "select * from v_wordhistory where uid = ?  order by addTime desc ";

    ResultSet result = null;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, this.uid);
      result = pstmt.executeQuery();

      while (result.next()){
        WordHistory wordHistory = new WordHistory();
        wordHistory.setUid(result.getInt("uid"));
        wordHistory.setWid(result.getInt("wid"));
        wordHistory.setWord(result.getString("word"));
        wordHistory.setMean(result.getString("mean"));
        wordHistory.setAddTime(result.getTimestamp("addTime"));
        Map<String, Object> map = new HashMap();
        map.put("uid", wordHistory.getUid());
        map.put("wid", wordHistory.getWid());
        map.put("word", wordHistory.getWord());
        map.put("mean", wordHistory.getMean());
        map.put("addTime", wordHistory.getAddTime());
        list.add(map);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        closeConn(conn, pstmt, result);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return list;
  }
}
