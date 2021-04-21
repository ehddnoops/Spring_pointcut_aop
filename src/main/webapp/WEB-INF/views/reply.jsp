<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>reply</title>
</head>
<body>
   <div style="text-align: center">
      <div>
         <input type="hidden" id="replyBno" value="1">
         <input type="text" id="replyId">
         <input type="text" id="replyContent" placeholder="댓글 입력하기">
         <button type="button" id="btn_add">작성</button>
      </div>
   </div>
   <hr>
   <div style="text-align: center;">
      <div id="replies"></div>
   </div>
   <script type="text/javascript">
      $(document).ready(function(){
         var replyBno = $('#replyBno').val(); // 게시판 번호 값
         getAllReplies();
         
         // 댓글 입력 기능
         $('#btn_add').click(function () {
            var replyContent = $('#replyContent').val(); // 댓글 내용
            var replyId = $('#replyId').val(); // 댓글 아이디
            var obj = {
                  'replyBno' : replyBno,
                  'replyContent' : replyContent,
                  'replyId' : replyId
            };
            console.log(obj);
            var JSONObj = JSON.stringify(obj);
            
            // $.ajax로 송신
            $.ajax({
               type : 'post',
               url : 'replies',
               headers : {
                  'Content-Type' : 'application/json',
                  'X-HTTP-Method-Override' : 'POST'
               },
               data : JSONObj,
               success : function (result, status) {
                  console.log(result);
                  console.log(status);
                  if (result == 1) {
                     alert('댓글 입력 성공');
                     getAllReplies();
                     $('#replyContent').val('');
                     $('#replyId').val('');
                  }
               } // end success
            }); // end ajax()
         });  // end btn_add.click()
         
         // 게시판의 댓글 전체 가져오기
         function getAllReplies() {
            var url = 'replies/all/' + replyBno;
            $.getJSON(
                  url,
                  function(jsonData) {
                     console.log(jsonData);
                     var list = ''; // JSON 데이터를 표현할 변수
                     $(jsonData).each(function() {
                        // this : 컬렉션에서 각 데이터를 꺼내서 저장
                             console.log(this); // this-> 한줄
                             var replyDate = new Date(this.replyDate);
                             
                          list += '<div class="reply_item">'
                              + '<pre>'
                              + '<input type="hidden" id = "replyNo" value="' + this.replyNo + '" />'
                              + '<input type="hidden" id = "replyId" value="' + this.replyId + '" />'
                              + this.replyId 
                              + '&nbsp;&nbsp;' // 공백
                              + '<input type="text" id="replyContent" value="' + this.replyContent + '" />'
                              + '&nbsp;&nbsp;'
                              + replyDate
                              + '&nbsp;&nbsp;'
                              + '<button class="btn_update" type="button">수정</button> '
                              + '<button class="btn_delete" type="button">삭제</button> '
                              + '</pre>'
                              + '</div>';
                     }); // end each()
                     $('#replies').html(list);
                  } // end callback()
                  ); // end getJSON()
         } // end getAllReplies
         
         // 수정 버튼을 클릭하면 선택된 댓글 수정
         $('#replies').on('click', 
               '.reply_item .btn_update',
               function() {
            console.log(this);
            
            // 선택된 댓글 replyNo, replyContent 값을 저장
            var replyNo = $(this).prevAll('#replyNo').val();
            var replyContent = $(this).prevAll('#replyContent').val();
            console.log("선택된 댓글 번호 : " + replyNo + ", 댓글 내용 : " + replyContent);
            
            // ajax 요청
            $.ajax({
               type : 'put',
               url : 'replies/' + replyNo,
               headers : {
                  'Content-Type' : 'application/json',
                  'X-HTTP-Method-Override' : 'PUT'
               },
               data : JSON.stringify({
                  'replyBno' : replyBno,
                  'replyContent' : replyContent
               }),
               success : function (result) {
                  if(result == 'success'){
                     alert('댓글 수정 성공');
                     getAllReplies();
                  }
               } // end callback()
            }); // end ajax()
         }); // end bnt_update()
         
         // 삭제 버튼을 클릭하면 선택된 댓글 삭제
         $('#replies').on('click', '.reply_item .btn_delete',
               function () {
            console.log(this);
            
            // 선택된 댓글 replyNo
            var replyNo = $(this).prevAll('#replyNo').val();
            console.log("선택된 댓글 번호 : " + replyNo);
            
            // ajax 요청
            $.ajax({
               type : 'delete',
               url : 'replies/' + replyNo,
               headers : {
                  'Content-Type' : 'application/json',
                  'X-HTTP-Method-Override' : 'DELETE'
               }, 
               data : JSON.stringify({
                  'replyBno' : replyBno
               }),
               success : function (result) {
                  if(result == 'success'){
                     alert('댓글 삭제 성공');
                     
                     getAllReplies();
                  }
               }
            }); // end ajax()
         }); // end btn_delete()
      }); // end document
   </script>
</body>
</html>