package model.bean;

public class Msg {
  private String state;
  private String error;

  final public static String STATE_SUCCESS = "SUCCESS";
  final  public static String STATE_FALSE = "FALSE";

  final public static String ERROR_DB_INSERT = "DB INSERT FALSE";       //插入数据失败
  final  public static String ERROR_DB_INSERT_REPETDATA = "DB INSERT REPETDATA";    //插入数据重复
  final   public static String ERROR_DB_UPDATE = "DB UPDATE FALSE";   //更新数据失败
  final  public static String ERROR_DB_DELETE = "DB DELETE FALSE";    //删除数据失败

  final   public static String ERROR_DATA_FALSE = "DATA FORMATTING ERROR";    //数据格式错误

  final   public static String ERROR_AORP = "AORP ERROR";   //帐号或密码错误

  final public static String ERROR_WORD_NULL = "NULL WORD ERROR";   //没有该单词

  final public static String ERROR_USER_NULL = "NULL USER ERROR";   //没有该用户

  final public static String ERROR_STR_OR = " OR ";

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
