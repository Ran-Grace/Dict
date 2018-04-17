<%@ page import="model.dao.DaoUser" %>
<%@ page import="model.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: hzandy
  Date: 17-12-19
  Time: 下午7:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>词典注册</title>
  <link rel="stylesheet" type="text/css" href="../css/sign.css"/>
  <link rel="script" href="../js/sign.js"/>
  <script type="application/javascript" src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="login_right">
  <div class="coner">
    <div class="inne">
      词典注册
      <form name="deng" method="post" action="sign.jsp">
        <p>
          <nobr>
            &nbsp;用户名&nbsp;  ：
            <input  type="text" placeholder=" 请输入用户名"  name="username" maxlength="16"
                    tabindex="10" class="topinput" autocomplete="off" >
          </nobr>
        </p>
        <p>
          <nobr>
            &nbsp;密&nbsp;&nbsp;&nbsp;码 &nbsp;  ：
            <input id="password" value="" class="topinput" placeholder=" 区分大小写" name="password" type="password" tabindex="11"/>
          </nobr>
        </p>
        <p>
          <nobr>
            确认密码：
            <input  id="repassword"  class="topinput" name="password" type="password"
                    value="" placeholder=" 重新输入密码" tabindex="11" onblur="checkpwd()"/>
          </nobr>
        </p>
        <p style="text-align: center">
          <nobr>
            <input type="submit" class="btn_login" value="注&nbsp;&nbsp;&nbsp;&nbsp;册" tabindex="17"/>
          </nobr>
        </p>
      </form>
      <div style="height: 26px"></div>
      <hr class="hr">
      <p class="register">
        已有账号？<br>
        现在<a href="login.html">登录</a>吧！
      </p>
    </div>
  </div>
</div>

<%--jsp--%>
<%
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  if (username != null && password != null) {
    DaoUser daoUser = new DaoUser();
    if (daoUser.isRepetUser(username)) {
      // 当焦点移出用户名输入框时
      %>
<script>
     alert("该用户已被注册！")
</script>
  <%
    } else {
      //不重复
      int uid;
      User user = new User(username, password);
      daoUser.setUser(user);
      uid = daoUser.dbInsert();
      user.setId(uid);
      session.setAttribute("user", user);
      response.sendRedirect("../jsp/login.jsp");
    }
  }
%>
<%--jsp--%>


<%--js--%>
<script>
  function checkpwd() {
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("repassword").value;
    if (password != repassword){
      alert("两次密码不同，请重新输入");
    }
  }

</script>
<%--js--%>

</body>
</html>
