<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정 화면</title>

<link rel="stylesheet" href="/resources/include/css/common.css" />
<link rel="stylesheet" href="/resources/include/css/board.css" />

<script src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script src="/resources/include/js/common.js"></script>
<script>
   $(function() {
      // 수정 버튼 클릭 시 처리 이벤트
      $("#boardUpdateBtn").click(function() {
         // 입력값 체크
         if(!chkSubmit($("#b_title"), "제목을")) {
            return;
         } else if(!chkSubmit($("#b_content"), "작성할 내용을")) {
            return;
         } else {
            $("#f_writeForm").attr({
               "method" : "post",
               "action" : "/board/boardUpdate.do"
            });
            $("#f_writeForm").submit();
         }
      });
      
      // 목록 버튼 클릭 시 처리 이벤트
      $("#boardListBtn").click(function() {
         location.href = "/board/boardList.do";
      });
   });
</script>
</head>
<body>
   
   <div class="contentContainer">
      
      <div class="contentTit">
         <h3>게시판 글 수정</h3>
      </div>
      
      <div class="contentTB">
         <form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
            <input type="hidden" name="b_num" id="b_num" value="${updateData.b_num}" />
         	<input type="hidden" name="b_file" id="b_file" value="${updateData.b_file }"/>
			<input type="hidden" name="page" id="page" value="${param.page }" />
			<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize }" />
            <table>
               <colgroup>
                  <col width="17%" />
                  <col width="33%" />
                  <col width="17%" />
                  <col width="33%" />
               </colgroup>
               <tbody>
                  <tr>
                     <td class="ac">글 번호</td>
                     <td>${updateData.b_num}</td>
                     <td class="ac">작성일</td>
                     <td>${updateData.b_date}</td>
                  </tr>
                  <tr>
                     <td class="ac">작성자</td>
                     <td colspan="3">${updateData.b_name}</td>
                  </tr>
                  <tr>
                     <td class="ac">글 제목</td>
                     <td colspan="3"><input type="text" name="b_title" id="b_title" value="${updateData.b_title}" /></td>
                  </tr>
                  <tr>
                     <td class="ac vm">내용</td>
                     <td colspan="3">
                        <textarea name="b_content" id="b_content">${updateData.b_content}</textarea>
                     </td>
                  </tr>
                  <tr>
						<td class="ac">첨부파일</td>
						<td colspan="3"><input type="file" name="file" id="file" /><span id="imgView">기존 이미지 파일명 : ${updateData.b_file } <span id="imgArea"></span></span></td>
                  </tr>
                  <tr>
                     <td class="ac">비밀번호</td>
                     <td colspan="3">
                        <input type="password" name="b_pwd" id="b_pwd" />
                        <label>수정할 비밀번호를 입력해주세요</label>
                     </td>
                  </tr>
               </tbody>
            </table>
         </form>
      </div>
      
      <div class="contentBtn">
         <input type="button" id="boardUpdateBtn" value="수정" />
         <input type="button" id="boardListBtn" value="목록" />
      </div>
      
   </div>

</body>
</html>