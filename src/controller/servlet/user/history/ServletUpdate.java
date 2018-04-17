package controller.servlet.user.history;

import model.bean.Flag;
import model.bean.History;
import model.bean.Msg;
import model.dao.DaoHistory;
import org.json.JSONException;
import org.json.JSONObject;
import publicclass.jsonhelp.JsonBean;
import publicclass.jsonhelp.JsonHelper;
import publicclass.jsonhelp.JsonItem;
import publicclass.servlethelp.ServletHelper;
import publicclass.str.JsonDataName;
import publicclass.timehelp.TimeHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletUpdate", urlPatterns = {"/user/history/update"})
public class ServletUpdate extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //定义数据、对象
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    //定义该方法特有的数据对象
    int result = 0;
    Msg msg = new Msg();
    Flag flag = new Flag();
    History history = new History();

    //数据库操作对象
    DaoHistory dHistory;

    //获得传输过来的json内容， 并生成json对象
    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      history.setUid(requestJSON.getInt("uid"));
      history.setWid(requestJSON.getInt("wid"));
      history.setAddTime(TimeHelper.getTimestamp());
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if ((history.getUid() != 0) && (history.getWid() != 0)) {
      //数据操作
      dHistory = new DaoHistory(history);
      result = dHistory.dbUpdata();

      //返回结果
      if (result == 1) {
        flag.setFlag(true);
        msg.setState(Msg.STATE_SUCCESS);
        try {
//          json = JsonHelper.toMyJson(flag, msg, "msg");
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
          json = JsonHelper.removeJsonItem(json,
            new JsonItem(JsonDataName.MSG, new String[]{JsonDataName.ERROR}));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      } else {
        flag.setFlag(true);
        msg.setState(Msg.STATE_FALSE);
        msg.setError(Msg.ERROR_DB_UPDATE);
        try {
//          json = JsonHelper.toMyJson(flag, msg, "msg");
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      }
    } else {
      flag.setFlag(false);
      msg.setState(Msg.STATE_FALSE);
      msg.setError(Msg.ERROR_DATA_FALSE);
      try {
//        json = JsonHelper.toMyJson(flag, msg, "msg");
        json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      out.print(json);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}
