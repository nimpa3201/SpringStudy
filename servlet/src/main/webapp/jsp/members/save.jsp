<%@ page import="com.example.servlet.domain.member.Member" %>
<%@ page import="com.example.servlet.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: gwongilnam
  Date: 6/24/24
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%  // 자바 코드
  MemberRepository memberRepository = MemberRepository.getInstance();
  System.out.println("MemberSaveServlet.service");
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body> 성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>