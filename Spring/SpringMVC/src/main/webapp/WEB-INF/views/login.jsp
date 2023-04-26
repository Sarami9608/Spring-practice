<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="member/login" method="post">
		<table>
			<tr>
				<td>
					ID : 
				</td>
				<td>					
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td>
					PW : 
				</td>
				<td>					
					<input type="password" name="pw">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="LOGIN">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>