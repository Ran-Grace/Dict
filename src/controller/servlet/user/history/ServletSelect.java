package controller.servlet.user.history;

import model.bean.Flag;
import model.bean.Msg;
import model.bean.WordHistory;
import model.dao.DaoUser;
import model.dao.DaoWordHistory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import publicclass.jsonhelp.JsonBean;
import publicclass.jsonhelp.JsonHelper;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletSelect", urlPatterns = {"/user/history/select"})
public class ServletSelect extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    int uid = 0;
    List<Map> list = null;
    Flag flag = new Flag();
    Msg msg = new Msg();

    DaoWordHistory dHistory;
    DaoUser daoUser = new DaoUser();

    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      uid = requestJSON.getInt("uid");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if (!daoUser.isUser(uid)) {
      flag.setFlag(true);
      msg.setState(Msg.STATE_FALSE);
      msg.setError(Msg.ERROR_USER_NULL + Msg.ERROR_STR_OR + Msg.ERROR_DATA_FALSE);
      try {
        json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
        out.print(json);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } else if(uid != 0) {
      dHistory = new DaoWordHistory(uid);
      list = dHistory.getList();
      flag.setFlag(true);
      try {
        json = JsonHelper.toMyJson(flag, list, "wordhistory");
      } catch (JSONException e) {
        e.printStackTrace();
      }
      out.print(json);
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

  }
}
