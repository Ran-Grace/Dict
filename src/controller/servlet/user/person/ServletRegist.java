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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletRegist", urlPatterns = {"/user/person/regist"})
public class ServletRegist extends HttpServlet {


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    PrintWriter out;

    User user = new User();
    Flag flag = new Flag();
    Msg msg = new Msg();

    int id;

    DaoUser daoUser = new DaoUser();
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    out = response.getWriter();

    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      user.setUsername(requestJSON.getString("username"));
      user.setPassword(requestJSON.getString("password"));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if ((user.getUsername() != null) && (user.getPassword() != null) && (!"".equals(user.getUsername())) && (!"".equals(user.getPassword()))) {
      daoUser.setUser(user);

      try {
        if (!daoUser.isRepetUser(user.getUsername())) {
          if (insertResult(id = daoUser.dbInsert())) {
            //插入成功
            flag.setFlag(true);
            msg.setState(Msg.STATE_SUCCESS);
            user.setId(id);
            json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG), new JsonBean(user, JsonDataName.USER));
            json = JsonHelper.removeJsonItem(json,
              new JsonItem(JsonDataName.USER, new String[]{JsonDataName.PASSWORD}),
              new JsonItem(JsonDataName.MSG, new String[]{JsonDataName.ERROR})
              );
            out.print(json);
          } else {
            //插入失败
            flag.setFlag(true);
            msg.setState(Msg.STATE_FALSE);
            msg.setError(Msg.ERROR_DB_INSERT);
            json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
            out.print(json);
          }
        } else {
          //用户重复
          flag.setFlag(true);
          msg.setState(Msg.STATE_FALSE);
          msg.setError(Msg.ERROR_DB_INSERT_REPETDATA);
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(msg, JsonDataName.MSG));
          out.print(json);
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } else {
      //数据格式错误
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


  //是否成功插入
  private boolean insertResult(int result) {
    if (result != 0) {
      return true;
    } else {
      return false;
    }
  }
}
