package publicclass.timehelp;

import java.sql.Timestamp;

public class TimeHelper {
  //获得时间
  public static Timestamp getTimestamp(){
    return new Timestamp(System.currentTimeMillis());
  }
}
