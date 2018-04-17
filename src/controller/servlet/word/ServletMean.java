package controller.servlet.word;

import model.bean.Flag;
import model.bean.Msg;
import model.bean.Word;
import model.dao.DaoWord;
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

@WebServlet(name = "ServletMean", urlPatterns = {"/word/mean"})
public class ServletMean extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String json = "", strRequestJson;
    JSONObject requestJSON = null;
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
//    response.setContentType("application/json; charset=utf-8");
    PrintWriter out = response.getWriter();

    Word word = new Word();
    Flag flag = new Flag();
    Msg msg = new Msg();

    DaoWord daoWord;

    strRequestJson = ServletHelper.readRequest(request);
    try {
      requestJSON = new JSONObject(strRequestJson);

      word.setWord(requestJSON.getString("word"));
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if ((word.getWord() != null) && (!"".equals(word.getWord()))) {
      daoWord = new DaoWord(word);
      word = daoWord.getWord();
      if (word != null) {
        flag.setFlag(true);
        msg.setState(Msg.STATE_SUCCESS);
        try {
          json = JsonHelper.toMyJson(new JsonBean(flag, JsonDataName.FLAG), new JsonBean(word, JsonDataName.WORD), new JsonBean(msg, JsonDataName.MSG));
          json = JsonHelper.removeJsonItem(json,
            new JsonItem(JsonDataName.MSG, new String[]{JsonDataName.ERROR})
            );
        } catch (JSONException e) {
          e.printStackTrace();
        }
        out.print(json);
      } else {
        flag.setFlag(true);
        msg.setState(Msg.STATE_FALSE);
        msg.setError(Msg.ERROR_WORD_NULL);
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
}
