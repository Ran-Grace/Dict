package controller.servlet.user.person;

import model.bean.Flag;
import model.bean.Msg;
import model.bean.User;
import model.dao.DaoUser;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/user/person/login"})
public class ServletLogin extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    User user = new User();
    Flag flag = new Flag();
    Msg msg = new Msg();

    DaoUser daoUser;

    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      user.setUsername(requestJSON.getString("username"));
      user.setPassword(requestJSON.getString("password"));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if((user.getUsername() != null) && (user.getPassword() != null) && (!"".equals(user.getUsername())) && (!"".equals(user.getPassword()))){
      daoUser = new DaoUser(user);
        user = daoUser.isUser();
      if(user.getId() != 0){
        flag.setFlag(true);
        msg.setState(Msg.STATE_SUCCESS);
        try {
//          json = JsonHelper.toMyJson(flag, user, "user");
          json = JsonHelper.toMyJson(
            new JsonBean(flag, JsonDataName.FLAG)
            , new JsonBean(user, JsonDataName.USER)
            , new JsonBean(msg, JsonDataName.MSG));
          json = JsonHelper.removeJsonItem(json,
            new JsonItem(JsonDataName.MSG, new String[]{JsonDataName.ERROR})
            , new JsonItem(JsonDataName.USER, new String[]{JsonDataName.PASSWORD})
            );
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      }else {
          flag.setFlag(true);
          msg.setState(Msg.STATE_FALSE);
          msg.setError(Msg.ERROR_AORP);
        try {
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG),
            new JsonBean(msg, JsonDataName.MSG));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      }
    }else {
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
