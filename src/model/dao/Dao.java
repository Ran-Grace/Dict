package model.dao;

import model.interfaces.db.DbDelete;
import model.interfaces.db.DbInsert;
import model.interfaces.db.DbSelect;
import model.interfaces.db.DbUpdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao  {
  protected static JdbcPool jdbcPool = new JdbcPool();

  protected void closeConn(Connection conn, PreparedStatement pstmt) throws SQLException {
    pstmt.close();
    conn.close();
  }
  protected void closeConn(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
    rs.close();
    pstmt.close();
    conn.close();
  }
}
