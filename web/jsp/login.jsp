<%@ page import="model.dao.DaoUser" %>
<%@ page import="model.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: hzandy
  Date: 17-12-19
  Time: 下午6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>词典登录</title>
  <link rel="stylesheet" type="text/css" href="../css/login.css"/>
  <script type="application/javascript" src="../js/jquery-3.2.1.min.js"></script>

</head>

<%--jsp--%>
<%
  String username = request.getParameter("username");
  String password = request.getParameter("password");

  if (username != null && password != null) {
    DaoUser daoUser = new DaoUser();
    User user = new User(username, password);
    daoUser.setUser(user);
    user = daoUser.isUser();
    if (user.getId() != 0) {
      session.setAttribute("user", user);
      response.sendRedirect("../index.jsp");
    } else {
//      out.print("没有");
    }
  }
%>
<%--jsp--%>

<body>
<div class="t">
  <div class="content">
    <img src="../images/icon/youdao.png">
    <div class="slogen"><a href="../html/login.html">登录</a></div>
    <div class="link"><a href="../index.jsp">词典首页</a><a href="#">更多产品</a>
    </div>
  </div>
</div>

<div class="w">
  <div class="login">
    <div class="login_left">
      <div class="banner">
        <p><br><br><br><br>登录有道，体验个性首页、音乐盒、阅读、返现等个性化服务。<br><br>
          如果您现在还没有词典邮箱，请点击这里<a href="sign.html">注册</a>。</p>
      </div>
      <!--</div>-->
      <div style="float:left;margin-left: 0;width: 300px">
        <ul class="product">
          <li>
            <img src="../images/icon/webpage.gif">
            <strong><a href="#">网页</a></strong>
            <br>
            汇集海量网页，更快一秒直达结果
          </li>
          <li>
            <img src="../images/icon/image.gif">
            <strong><a href="#">图片</a></strong>
            <br>
            搜索超过十亿网上图片
          </li>
          <li>
            <img src="../images/icon/fanyi.gif">
            <strong><a href="#">翻译</a></strong>
            <br>
            中国最大的多语种免费在线翻译服务
          </li>
        </ul>
      </div>
      <div style="float:left;margin-left: 0;width: 340px">
        <ul class="productlist">
          <li>
            <img src="../images/icon/dict.gif">
            <strong><a href="#">词典</a></strong>
            <br>
            结合词典和搜索技术，独创“网络释义”功能
          </li>
          <li>
            <img src="../images/icon/video.gif">
            <strong><a href="#">视频</a></strong>
            <br>
            视频预览、剧集整理，视频搜索可以更方便
          </li>
          <li>
            <img src="../images/icon/note.gif">
            <strong><a href="#">笔记</a></strong>
            <br>
            随时随地，记录一切
          </li>
        </ul>
      </div>
    </div>
    <div class="login_right">

      <div class="coner">
        <div class="inne">
          使用邮箱账号登录
          <form name="deng" method="post" action="login.jsp">
            <p>
              <nobr>
                账号：
                <input  type="text" placeholder="输入用户名"
                        tabindex="10" class="topinput" autocomplete="off" name="username" id="username">
              </nobr>
            </p>
            <p>
              <nobr>
                密码：
                <input id="password" class="topinput" name="password"  type="password" tabindex="11"/>
              </nobr>
            </p>
            <%--<p>--%>
              <%--<nobr>--%>
                <%--<span style="border-collapse:collapse"  >--%>
                  <%--<input type="checkbox" tabindex="12" value="1"  id="savelogin">--%>
              <%--<label class="small_text" for="savelogin" style="width:90px;">记住我的登录状态</label>--%>
                <%--</span>--%>
                <%--<span class="small_text">--%>
                <%--<a href="#">忘记密码？</a>--%>
                <%--</span>--%>
              <%--</nobr>--%>
            <%--</p>--%>
            <p style="text-align: center">
              <nobr>
                <input type="submit" class="btn_login" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" tabindex="17"/>
              </nobr>
            </p>
          </form>
          <div style="height: 26px"></div>
          <hr class="hr">
          <p class="register">
            还没有账号？<br>
            现在<a href="sign.jsp">注册</a>吧！
          </p>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
