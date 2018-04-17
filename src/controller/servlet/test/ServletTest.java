package controller.servlet.test;

import model.bean.Flag;
import model.bean.Msg;
import model.bean.User;
import org.json.JSONException;
import org.json.JSONObject;
import publicclass.jsonhelp.JsonBean;
import publicclass.jsonhelp.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletTest", urlPatterns = {"/test"})
public class ServletTest extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Flag flag = new Flag(true);
    Msg msg = new Msg();
    String json = "";
    msg.setError(Msg.ERROR_AORP);
    msg.setState(Msg.STATE_FALSE);
    try {
   json = JsonHelper.toMyJson(new JsonBean(flag, "flag"), new JsonBean(msg, "msg"));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    PrintWriter out = response.getWriter();
    out.print(json);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
