package publicclass.servlethelp;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class ServletHelper {
  //读取request 的值  转换成字符串
  public static String readRequest(HttpServletRequest request) throws IOException {
    StringBuffer strbuf = new StringBuffer();
    String line = null;
    BufferedReader bufReader = request.getReader();

    while ((line = bufReader.readLine()) != null){
      strbuf.append(line);
    }
    return strbuf.toString();
  }
}
