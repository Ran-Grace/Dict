package model.dao;

import model.interfaces.db.DbDelete;
import model.interfaces.db.DbInsert;
import model.interfaces.db.DbSelect;
import model.interfaces.db.DbUpdata;
import model.bean.User;

import java.sql.*;

public class DaoUser extends Dao  implements DbInsert, DbDelete, DbUpdata {

  private User user;

  public DaoUser() {
  }

  public DaoUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public int dbDelete() {
    int result = 0;
    String sql = "delect from t_user where id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;


    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, user.getId());
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
    String sql = "insert into t_user(username, password) values(?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, user.getUsername());
      pstmt.setString(2, user.getPassword());
      pstmt.executeUpdate();
      rs = pstmt.getGeneratedKeys();
      if (rs.next()) {
        result = rs.getInt(1);
      }
      rs.close();
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
  public int dbUpdata() {
    int result = 0;
    String sql = "update t_user set password = ? where id = ? and username = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user.getPassword());
      pstmt.setInt(2, user.getId());
      pstmt.setString(3, user.getUsername());
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

  public User isUser() {
    int rowNum = 0;
    int id = 0;

    String sql = "select id from t_user where username = ? and password = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user.getUsername());
      pstmt.setString(2, user.getPassword());
      result = pstmt.executeQuery();
      result.last();

      rowNum = result.getRow();
      id = result.getInt("id");
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        closeConn(conn, pstmt, result);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if(rowNum == 1){
      user.setId(id);
      return user;
    }else {
      user.setId(0);
      return user;
    }
  }

  public boolean isUser(int uid) {
    boolean flag = false;
    int rowNum = 0;
    String sql = "select id from t_user where id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, uid);
      rs = pstmt.executeQuery();
      rs.last();
      rowNum = rs.getRow();
      if (rowNum != 0) {
        flag = true;
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        closeConn(conn, pstmt);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      return flag;
    }


  }

  public boolean isRepetUser (String username) {
    int rowNum = 0;
    String sql = "select id from t_user where username = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;

    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, username);
      result = pstmt.executeQuery();
      result.last();
      rowNum = result.getRow();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        closeConn(conn, pstmt, result);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (rowNum == 0) {
      return false;
    } else {
      return true;
    }
  }
}
