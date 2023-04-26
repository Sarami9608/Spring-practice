<%@page import="com.smhrd.domain.Board"%>
<%@page import="java.util.List"%>
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
 <%
 	/* 모델에 저장한 데이터를 가져오는 방법 */
 	List<Board> list = (List<Board>)request.getAttribute("list");
 	System.out.print(list.size());
 %>
 
 
<div class="container">
  <h2>게시판💤</h2>
  <div class="panel panel-default">
    <div class="panel-heading">잠자고 싶은 사람들의 뜬소리 게시판</div>
    <div class="panel-body">
		<table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>제목</th>
		        <th>작성자</th>
		        <th>작성일</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<%for(Board board : list){ %>
			      <tr>
			        <td><%= board.getIdx() %></td>
			        <td><a href="content?idx=<%=board.getIdx()%>"><%= board.getTitle() %></a></td>
			        <td><%= board.getWriter() %></td>
			        <td><%= board.getIndate() %></td>
			      </tr>
		      	<%} %>
		      	<tr>
		      		<td colspan="4" align="center">
		      			<button class="btn btn-sm btn-success" onclick="location.href='writeform'">글작성</button>
		      		</td>
		      	</tr>
		    </tbody>
		  </table>
	</div>
    <div class="panel-footer">한 3일동안 잠만 자고 싶다.</div>
  </div>
</div>

</body>
</html>
