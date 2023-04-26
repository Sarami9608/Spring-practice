<%@page import="com.smhrd.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<% Board board = (Board)request.getAttribute("board");%>
 
<div class="container">
  <h2>게시판💤</h2>
  <div class="panel panel-default">
    <div class="panel-heading">잠자고 싶은 사람들의 뜬소리 게시판</div>
    <div class="panel-body">
		<table class = "table table-bordered">
			<tr>
				<td>번호</td>
				<td><%=board.getIdx() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><%=board.getTitle() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=board.getContent() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%=board.getWriter() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=board.getIndate() %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-sm btn-info" onclick="location.href='updateForm?idx=<%=board.getIdx()%>'">수정</button>
					<button class="btn btn-sm btn-warning" onclick="location.href='deleteBoard/<%=board.getIdx()%>'">삭제</button><!-- restful?-->
					<button class="btn btn-sm btn-success" onclick="location.href='/board/'">리스트</button>
				</td>
			</tr>
		</table>
    </div>
    <div class="panel-footer">한 3일동안 잠만 자고 싶다.</div>
  </div>
</div>

</body>
</html>
