<%@page import="com.smhrd.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
	
	<%if(loginMember == null){ %>
	<a href="join"><button>회원가입</button></a>
	<a href="login"><button>로그인</button></a>
	<%} else { %>
		<p><%= loginMember.getNick() %>님 환영합니다.</p>
		<a href="logout"><button>로그아웃</button></a>
	<%} %>		
</body>
</html>