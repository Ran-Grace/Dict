package model.interfaces.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbSelect {
  public ResultSet dbSelete() throws SQLException;
}
