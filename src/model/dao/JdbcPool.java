package model.dao;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class JdbcPool implements DataSource{

  private static LinkedList<Connection> connList = new LinkedList<>();

  static {
    //数据库配置
    final String URL = "jdbc:mysql://localhost:3306/dict";
    final String USER = "root";
    final String PWD = "123456";
    int jdbcPoolInitSize = 1000;     //最大连接数
    try {
      Class.forName("com.mysql.jdbc.Driver");
      for (int i = 0; i < jdbcPoolInitSize; i++){
        Connection conn = DriverManager.getConnection(URL, USER, PWD);
        connList.add(conn);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Connection getConnection() throws SQLException {
    if(connList.size() > 0){
      final Connection conn = connList.removeFirst();
      return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          if(!method.getName().equals("close")){
            return method.invoke(conn, args);
          }else{
            //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
            connList.add(conn);
            return null;
          }
        }
      });
    }else {
      throw new RuntimeException("连接数已经满了");
    }
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    return null;
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return null;
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return null;
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {

  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {

  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return 0;
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return null;
  }
}
