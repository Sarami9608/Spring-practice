<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>게시판💤</h2>
		<div class="panel panel-default">
			<div class="panel-heading">잠자고 싶은 사람들의 뜬소리 게시판</div>
			<div class="panel-body" id="list"></div>
			
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>No.</th>
							<th>Title</th>
							<th>Writer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody id="board-table-body">
					</tbody>
				</table>
			</div>
			<div id='wform' class="panel-body" style="display: none">
				<form class="form-horizontal" action="#" id="frm">
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목 : </label>
						<div class="col-sm-10">
							<input type="text" name="title" class="form-control"
								placeholder="Enter title">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내용 : </label>
						<div class="col-sm-10">
							<textarea rows="10" name="content" class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="writer">작성자:</label>
						<div class="col-sm-10">
							<input type="text" name="writer" class="form-control"
								placeholder="Enter writer">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-success btn-sm"
								onclick='goInsert()'>등록</button>
							<button type="reset" class="btn btn-warning btn-sm" id="init">취소</button>
						</div>
					</div>
				</form>
			</div>
			<div class="panel-footer">한 3일동안 잠만 자고 싶다.</div>
		</div>
	</div>
	<script type="text/javascript">
		//basic.jsp 페이지가 로드 되자마자 리스트를 가져와야함
		$(document).ready(()=>{
			loadList();
		})
		function loadList(){
			$.ajax({
				url : "boardlist",
				type : "get", // 요청방식
				dataType : "json",//서버 반환 데이터 타입
				success : function(result){
					// result : 서버에서 반환해준 데이터
					console.log(result)
				      let boardTableBody = document.querySelector("#board-table-body");
				      let tableData = "";
				      for (let i = 0; i < result.length; i++) {
				        let idx = result[i].idx;
				        let title = result[i].title;
				        let writer = result[i].writer;
				        let content = result[i].content;
				        let indate = result[i].indate;

				        tableData += "<tr>";
				        tableData += "<td>" + idx + "</td>";
				        tableData += "<td id='t"+idx+"'><a href='javascript:contentView("+idx+")'>" + title + "</a></td>";
				        tableData += "<td id='w"+idx+"'>" + writer + "</td>";
				        tableData += "<td>" + indate + "</td>";
				        tableData += "<td id='u"+idx+"'><button class='btn btn-success btn-sm' onclick='goUpdate("+idx+")'>수정</button></td>";
				        tableData += "<td><button class='btn btn-warning btn-sm' onclick='goDelete("+idx+")'>삭제</button></td>";
				        tableData += "</tr>";
				        tableData += "<tr id='cv"+idx+"' style='display:none'>";
				        tableData += "<td>내용</td>";
				        tableData += "<td colspan='4'>";
				        tableData += "<textarea id='content"+idx+"' rows='6' class='form-control'>"+content+"</textarea>";
				        tableData += "<br>";
				        tableData+= "<button class='btn btn-success btn-sm' onclick='updateFn("+idx+")'>수정</button>&nbsp;";
				        tableData += "<button class='btn btn-warning btn-sm' onclick='closeFn("+idx+")'>닫기</button>";
				        tableData += "</td>";
				        tableData += "</tr>";

				      }
				      tableData += "<tr>";
				      tableData += "<td colspan='6' align='center'>";
				      tableData += "<button class='btn btn-primary btn-sm' onclick='goView()'>글작성</button>";
				      tableData += "</td>";
				      tableData += "</tr>";
				      boardTableBody.innerHTML = tableData;
				},
				
				/* jquery
					$.each(data,(index,vo)=>{ data : boardlist / vo : BoardVO
						
					})
				
				*/
				error : function(){
					alert("통신실패!")
				}
				
			})
		}
		
			function contentView(idx){
				//내용행이 안보일 때 -> 보이게
				//내용행이 보일 때 -> 안보이게
				if($("#cv"+idx).css("display")=="none"){
				//현재 display 속성값이 none 인지 판단
				$("#cv"+idx).css("display","table-row")
				} else{
					$("#cv"+idx).css("display","none")
				}
			}
			function closeFn(idx){
				$("#cv"+idx).css("display","none")
			}
		
		
			function updateFn(idx){
				let newContents = $("#content"+idx).val() // 사용자가 작성한 내용 수정본
				
				$.ajax({
					url : "boardcontentupdate",  // 요청경로
					type : "post", // 요청방식 why post 길면 get으로 전송 불가
					data : {"idx":idx, "content":newContents}, // 요청데이터 (인덱스, 내용)
					success : loadList,
					error:function(){
						alert("실패!")
					}
					
				})
				
			}
			
			function goUpdate(idx){
				//선택한 게시물의 제목, 작성자를 수정할 수 있는 폼으로 만들어주는 함수
				//현재 써져있는 값을 가져온다.
				let t = $("#t"+idx).text() // text == innertext , html == innerHtml
				
				//제목 수정하는 input 태그
				let tInput = "<input id='nt"+idx+"' type='text' class='form-control' value='"+t+"'>"
				$("#t"+idx).html(tInput) // html에 추가하고 싶은 html 문장을 () 안에 적는다.
				
				
				let w = $("#w"+idx).text()
				let wInput = "<input id='nw"+idx+"' type='text' class='form-control' value='"+w+"'>"
				$("#w"+idx).html(wInput) // html에 추가하고 싶은 html 문장을 () 안에 적는다.
				
				// 수정하기 버튼을 실제로 다시 누른 경우 값을 수정하기 위한 버튼으로 바꾸기 위한 코드
				let newBtn = "<button class='btn btn-info btn-sm' onclick='updateTWFn("+idx+")'>수정하기</button>"
				$("#u"+idx).html(newBtn)
				
			}
			
			function updateTWFn(idx){
				let newTitle = $("#nt"+idx).val() // 제목 수정본
				let newWriter = $("#nw"+idx).val() // 작성자 수정본
				
				$.ajax({
					url : "boardtwupdate",  // 요청경로
					type : "post", // 요청방식 why post 길면 get으로 전송 불가
					data : {"idx":idx, "title":newTitle, "writer":newWriter}, // 요청데이터 (인덱스, 내용)
					success : loadList,
					error:function(){
						alert("실패!")
					}
					
				})
			}
			
			function goDelete(idx){
				$.ajax({
					url:"boarddelete",
					type : "post",
					data : {"idx":idx},
					success :loadList,
					error:function(){
						alert("삭제 실패!")
					}
				})
			}
			function goView(){
				//내용행이 안보일 때 -> 보이게
				//내용행이 보일 때 -> 안보이게
				if($("#wform").css("display")=="none"){
				//현재 display 속성값이 none 인지 판단
				// 부드럽게 슬라이드 형식으로
					$("#wform").slideDown();
				} else{
					$("#wform").slideUp();
				}
			}
			
			function goInsert(){
				// 데이터를 한 번에 가져오는 방법
				let f = $("#frm").serialize();
				console.log(f);
				 $.ajax({
					url:"boardinsert",
					type:"post",
					data:f,
					success : function(){
						loadList();
						$("#wform").slideUp();
						$("#init").trigger("click");
					},
					error: function(){
						alert("실패!");
					}
				}) 
				
				
			}
		</script>


</body>
</html>
