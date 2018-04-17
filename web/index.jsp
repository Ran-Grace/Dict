<%@ page import="model.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: hzandy
  Date: 17-12-19
  Time: 下午6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>词典首页</title>
  <link rel="stylesheet" type="text/css" href="css/index.css"/>
  <script type="application/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="header">
  <ul>
    <li><a href="#">背单词</a></li>
    <li><a href="#">翻译</a></li>
    <li><a href="#">精品课</a></li>
    <li><a href="#">云笔记</a></li>
    <li><a href="#">人工翻译</a></li>
    <li><a href="#">更多产品</a></li>
    <li><a href="index.jsp"><img src="images/icon/you.jpg"></a></li>

  </ul>
</div>
<div class="body">
  <div style="height: 20px"></div><!--多设置一个20px的框让网页无缝隙-->
  <div class="body-img">
    <img src="images/bg/timg.jpg">
  </div>
</div>
<div style="height: 40px"></div><!--多设置一个40px的框让网页无缝隙-->
<div class="search">
  <div class="select">
    <select>
      <option>&nbsp;&nbsp;&nbsp;英&nbsp;&nbsp;&nbsp;英</option>
      <option>&nbsp;&nbsp;&nbsp;英&nbsp;&nbsp;&nbsp;汉</option>
      <option>&nbsp;&nbsp;&nbsp;汉&nbsp;&nbsp;&nbsp;英</option>
    </select>
  </div>
  <div class="input">
    <input type="text" id="word_text"  placeholder="请输入要翻译的单词..."/>

  </div>
  <div class="icon_serch" >

    <img src="images/icon/search.png">
  </div>

  <div class="button">
    <ul>
      <li><p id="btn_select">&nbsp;&nbsp;&nbsp;查&nbsp;&nbsp;询</p></li>
    </ul>
  </div>
</div>
<div class="download">
<span>
    <a href="#">下载词典客户端</a>
</span>
  <span>
    <a href="#">下载词典移动端</a>
</span>
  <%--jsp--%>
  <%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getId() == 0) {
      user = new User(0);
      session.setAttribute("user", user);
  %>
  <span>
    <a href="jsp/login.jsp">查看历史记录</a>
</span>
  <%
  } else {
  %>
  <span><a href="jsp/history.jsp">查看历史记录</a> </span>
  <%
    }
  %>
  <%--jsp--%>

</div>
<article class="inputword">
  <h4 id="text_mean"></h4>
</article>
<div class="foot">
  <div>
    <ul>
      <li><a href="http://www1.cduestc.cn/"onClick="addnums()">电子科技大学成都学院</a></li>
      <li><a>四川省成都市高新西区百叶路1号</a></li>
      <li><a>CopyDict@copy2017</a></li>
      <li><a href="html/team.html">版权归词典小组所有</a></li>
    </ul>
  </div>
</div>

<%--js--%>
<script type="application/javascript">
//输出查询的单词
  $("#btn_select").click(function () {
    var word = document.getElementById("word_text").value;
    var jsonData = "{\"word\": \"" + word + "\"}";
    var text_mean = document.getElementById("text_mean");

    $.ajax({
      url: "/word/mean",
      type:"POST",
      data:jsonData,
      success: function (data) {
        var jsonData = JSON.parse(data);//转成json格式
        if (jsonData.msg.state == "SUCCESS") {
          text_mean.innerHTML = jsonData.word.mean;

          <%
          if (user!= null && user.getId() != 0) {
          %>
          addHistory(<%=user.getId()%>, jsonData.word.wid);
          <%
          }
          %>
        } else {
          text_mean.innerHTML = "您输的单词不存在！";
        }
      }
    });
  });
  function addHistory(uid, wid) {
    var jsonData = "{\"uid\":"+uid+", \"wid\" : "+wid+"} ";
    $.ajax({
      url:"/user/history/insert",
      type:"POST",
      data:jsonData,
      success:function (data) {
        var jsonData = JSON.parse(data);
        if (jsonData.msg.state == "FALSE") {
          flushHistory(uid, wid);
        }
      },
      error:function (data) {
      }
    });
  }
//更新历史
  function flushHistory(uid, wid) {
    var jsonData = "{\"uid\":"+uid+", \"wid\" : "+wid+"} ";
    $.ajax({
      url:"/user/history/update",
      type:"POST",
      data:jsonData,
      success:function (data) {
      },
      error:function (data) {
      }
    });
  }
</script>
<%--js--%>
</body>
</html>