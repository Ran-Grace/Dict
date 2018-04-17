package publicclass.jsonhelp;

import model.bean.Flag;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * 1:将JavaBean转换成Map、JSONObject
 * 2:将Map转换成Javabean
 * 3:将JSONObject转换成Map、Javabean
 *
 * @author Alexia
 */

public class JsonHelper {

  /**
   * 将Javabean转换为Map
   *
   * @param javaBean
   *            javaBean
   * @return Map对象
   */
  public static Map toMap(Object javaBean) {

    Map result = new HashMap();
    Method[] methods = javaBean.getClass().getDeclaredMethods();

    for (Method method : methods) {

      try {

        if (method.getName().startsWith("get")) {

          String field = method.getName();
          field = field.substring(field.indexOf("get") + 3);
          field = field.toLowerCase().charAt(0) + field.substring(1);

          Object value = method.invoke(javaBean, (Object[]) null);
          result.put(field, null == value ? "" : value.toString());

        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    return result;

  }

  /**
   * 将Json对象转换成Map
   *
   * @param jsonObject
   *            json对象
   * @return Map对象
   * @throws JSONException
   */
  public static Map toMap(String jsonString) throws JSONException {

    JSONObject jsonObject = new JSONObject(jsonString);

    Map result = new HashMap();
    Iterator iterator = jsonObject.keys();
    String key = null;
    String value = null;

    while (iterator.hasNext()) {

      key = (String) iterator.next();
      value = jsonObject.getString(key);
      result.put(key, value);

    }
    return result;

  }

  /**
   * 将JavaBean转换成JSONObject（通过Map中转）
   *
   * @param bean
   *            javaBean
   * @return json对象
   */
  public static JSONObject toJSON(Object bean) {

    return new JSONObject(toMap(bean));

  }

  /**
   * 将Map转换成Javabean
   *
   * @param javabean
   *            javaBean
   * @param data
   *            Map数据
   */
  public static Object toJavaBean(Object javabean, Map data) {

    Method[] methods = javabean.getClass().getDeclaredMethods();
    for (Method method : methods) {

      try {
        if (method.getName().startsWith("set")) {

          String field = method.getName();
          field = field.substring(field.indexOf("set") + 3);
          field = field.toLowerCase().charAt(0) + field.substring(1);
          method.invoke(javabean, new Object[] {

                  data.get(field)

          });

        }
      } catch (Exception e) {
      }

    }

    return javabean;

  }

  /**
   * JSONObject到JavaBean
   *
   * @param bean
   *            javaBean
   * @return json对象
   * @throws ParseException
   *             json解析异常
   * @throws JSONException
   */
  public static void toJavaBean(Object javabean, String jsonString)
          throws ParseException, JSONException {

    JSONObject jsonObject = new JSONObject(jsonString);

    Map map = toMap(jsonObject.toString());

    toJavaBean(javabean, map);

  }

  public static String toMyJson (JsonBean ...jsonBeans) throws JSONException {
    JSONObject json = new JSONObject();
    for (JsonBean jsonBean : jsonBeans) {
      JSONObject joBean = JsonHelper.toJSON(jsonBean.getBean());
      json.put(jsonBean.getBeanName(), joBean);
    }
    return json.toString();
  }

  public static String toMyJson(Flag flag, List<Map> list, String arrName) throws JSONException {
    Iterator<Map> iterator;
    JSONArray jsonArray = new JSONArray();
    JSONObject jo = new JSONObject();
    JSONObject jsonFlag = JsonHelper.toJSON(flag);
    iterator = list.iterator();
    while (iterator.hasNext()){
      Map map = iterator.next();
      jsonArray.put(map);
    }
    jo.put("flag", jsonFlag);
    jo.put(arrName, jsonArray);
    return jo.toString();
  }

  public static String removeJsonItem(String json, JsonItem ...jsonItems) throws JSONException {
    JSONObject jo = new JSONObject(json);

    for (JsonItem jsonItem : jsonItems) {
      JSONObject rJson = jo.getJSONObject(jsonItem.getJoName());
      for (String item : jsonItem.getItems()) {
        rJson.remove(item);
      }
      jo.remove(jsonItem.getJoName());
      jo.put(jsonItem.getJoName(), rJson);
    }
    return jo.toString();
  }

}


