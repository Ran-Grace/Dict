package controller.servlet.user.history;

import model.bean.Flag;
import model.bean.History;
import model.bean.Msg;
import model.dao.DaoHistory;
import model.dao.DaoWordHistory;
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

@WebServlet(name = "ServletInsert", urlPatterns = {"/user/history/insert"})
public class ServletInsert extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    int result = 0;
    Flag flag = new Flag();
    Msg msg = new Msg();

    History history = new History();

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
      DaoHistory daoWordHistory = new DaoHistory(history);
      result = daoWordHistory.dbInsert();
      if (result == 1) {
        msg.setState(Msg.STATE_SUCCESS);
        flag.setFlag(true);
        try {
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
          json = JsonHelper.removeJsonItem(json,
            new JsonItem(JsonDataName.MSG, new String[]{JsonDataName.ERROR}));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      } else {
        msg.setState(Msg.STATE_FALSE);
        msg.setError(Msg.ERROR_DB_INSERT);
        flag.setFlag(true);
        try {
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
        json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      out.print(json);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
