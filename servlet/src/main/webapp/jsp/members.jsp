<%@ page import="com.example.servlet.domain.member.MemberRepository" %>
<%@ page import="com.example.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gwongilnam
  Date: 6/24/24
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//자바 로직
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> members  = memberRepository.findAll();


%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a> <table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
   <% for(Member member : members){
    out.write("    <tr>");
        out.write("        <td>"+member.getId()+"</td>");
        out.write("        <td>"+member.getUsername()+"</td>");
        out.write("        <td>"+member.getAge()+"</td>");

        }
%>
    </tbody>
</table>
</body>
</html>