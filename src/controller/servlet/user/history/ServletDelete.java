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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


//删除历史需要json传输的数据格式
//json{"uid":int, }
@WebServlet(name = "ServletDelete", urlPatterns = {"/user/history/delete"})
public class ServletDelete extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    int result = 0;
    Msg msg = new Msg();
    Flag flag = new Flag();
    History history = new History();

    DaoHistory dHistory;

    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      history.setUid(requestJSON.getInt("uid"));
      history.setWid(requestJSON.getInt("wid"));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    //判断数据是否有效
    if ((history.getUid() != 0) && (history.getWid() != 0)) {
      dHistory = new DaoHistory(history);
      result = dHistory.dbDelete();

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
        msg.setError(Msg.ERROR_DB_DELETE);
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
