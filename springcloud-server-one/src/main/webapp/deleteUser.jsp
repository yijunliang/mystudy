<%@page import="com.yjl.entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
   List<User> userList = (List<User>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
  <%if(userList.size() > 0) {%>
  <table border="1" >
     <thead>
       <tr>
          <th>用户名</th>
          <th>密码</th>
          <th>操作</th>
       </tr>
     </thead>
     <tbody>
         <%
         for(int i = 0; i < userList.size(); i++) {%>
         <tr>
            <td><%=userList.get(i).getUserName()%></td>
            <td><%=userList.get(i).getPassword()%></td>
            <td><a href="/user/deleteUser?userId=<%=userList.get(i).getId()%>">删除</a>
                <a href="/user/updateUserUI?userId=<%=userList.get(i).getId()%>">更新</a></td>
         </tr>
         <%} %>
     </tbody>
  </table>
  <%} else { %>
      <h2 align="center">没有记录!!</h2>
  <%}%>
</body>
</html>