<%@ page import="model.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: hzandy
  Date: 17-12-19
  Time: 下午6:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  User user;
%>
<html>
<head>
  <meta charset="UTF-8">
  <title>单词本</title>
  <link rel="stylesheet" type="text/css" href="../css/history.css"/>
  <script type="application/javascript" src="../js/jquery-3.2.1.min.js"></script>
</head>

<%--jsp--%>
<%
  user = (User) session.getAttribute("user");
%>
<%--jsp--%>
<body>
<header class="header">
  <%
    if (user != null && user.getId() != 0) {
  %>
  <form action="history.jsp" method="post" id="form1">
    <input type="hidden" name="logoutHidden" id="logoutHidden" value="logout">
    <input type="hidden" id="userID" name="userID" value="<%=user.getId()%>">
    <div class="hz">
      <ul>
        <li>您好</li>
        <li><%=user.getUsername()%></li>
      <li><a name="logout" id="logout" class="exit">退出</a></li>
      </ul>
    </div>
  </form>
  <%--jsp--%>
  <%
  } else {
  %>
  <a href="login.jsp">登录</a>
  <%
    }
  %>
  <%--jsp--%>
  <hr class="hr">
</header>
<figure class="icon">
  <img src="../images/icon/you.jpg">
</figure>

<table id="wordTable"  border="1" height="35" align="center" cellspacing="0" cellpadding="0">
  <caption><strong>查 询 记 录</strong>
    <div style="height: 30px"></div>
  </caption>
  <thead>
  <tr>
    <td align="center" width="200" valign="middle">单词</td>
    <td align="center" width="500" valign="middle">解释</td>
  </tr>
  </thead>
  <tbody>

  </tbody>
</table>

<%--jsp--%>
<%
  if (user.getId() != 0) {
%>
<script type="application/javascript">
  var userID = document.getElementById("userID").value;
  var jsonData = "{\"uid\":" + userID + "}";
  $.ajax({
    url: "/user/history/select",
    type:"POST",
    data:jsonData,
    success: function (data) {
      var jsonData = JSON.parse(data);
      var jsonArray = jsonData.wordhistory;
      for (var i in jsonArray) {
        addWordHistoryElement("wordTable", jsonArray[i].word, jsonArray[i].mean);
      }
    },
    error: function (data) {
    }
  });

</script>
<%
  }
%>

<%
  String str = request.getParameter("logoutHidden");
  if (str != null) {
    session.removeAttribute("user");
    response.sendRedirect("../index.jsp");
  }
%>
<%--jsp--%>

<%--js--%>
<script type="application/javascript">
  $("#logout").click(function () {
    document.getElementById("form1").submit();
  });

  function addWordHistoryElement(tableId, word, mean) {
    var dataA = document.createTextNode(word);
    var dataC = document.createTextNode(mean);

    var td_A = document.createElement("td");
    var td_C = document.createElement("td");
    var tr = document.createElement("tr");
    var tBody = document.createElement("tbody");

    td_A.appendChild(dataA);
    td_C.appendChild(dataC);

    tr.appendChild(td_A);
    tr.appendChild(td_C);

    tBody.appendChild(tr);

    var table = document.getElementById(tableId);
    table.appendChild(tBody);
  }

</script>
<%--js--%>
</body>
</html>