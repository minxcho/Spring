<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
   <div class="container">
     <h2>Spring Book</h2>
     <div class="panel panel-default">
       <div class="panel-heading">Book</div>
       <div class="panel-body">
         <table id="boardList" class="table table-bordered table-hover">
            <tr class="active">
               <td>번호</td>
               <td>제목</td>
               <td>작가</td>
               <td>출판사</td>
               <td>isbn</td>
               <td>보유도서수</td>
               <td>삭제</td>
               <td>수정</td>
            </tr>
            </tr>
            <tbody id="view" >
            <!-- 비동기 방식으로 가져온 게시글 나오게할 부분 -->
            </tbody>
            
            <tr>
               <td colspan="8">
                  <button onclick="goForm()" class="btn btn-primary btn-sm">도서등록</button>               
               </td>
            </tr>
         </table>         
      </div>
      
      <!-- 글쓰기 폼 -->
      <div class="panel-body" id="wform" style="display : none;">
         <form id="frm">
         <table class="table">
            <tr>
               <td>제목</td>
               <td><input type="text" name="title" class="form-control"></td>
            </tr>
            <tr>
               <td>작가</td>
               <td><input type="text" name="author" class="form-control"></td>
            </tr>
            <tr>
               <td>출판사</td>
               <td><input type="text" name="company" class="form-control"></td>
            </tr>
            <tr>
               <td>isbn</td>
               <td><input type="text" name="isbn" class="form-control"></td>
            </tr>
            <tr>
               <td>보유도서수</td>
               <td><input type="text" name="count" class="form-control"></td>
            </tr>
            <tr>
               <td colspan="2" align="center">
                  <button class="btn btn-success btn-sm" type="button" onclick="goInsert()">등록</button>
                  <button class="btn btn-warning btn-sm" type="reset" id="fclear">취소</button>
                  <button onclick="goList()" class="btn btn-info btn-sm">리스트로가기</button>
               </td>            
            </tr>
         </table>
         </form>
      </div>
      
      <!-- 수정화면 폼 -->
      <div class="panel-body" id="uform" style="display : none;">
         <form id="frm" action="${cpath}/update.do" method="post">
         <table class="table">
            <tr>
               <td>제목</td>
               <td><input type="text" name="title" class="form-control" readonly="readonly" value="${vo.title}"></td>
            </tr>
            <tr>
               <td>작가</td>
               <td><input type="text" name="author" class="form-control" readonly="readonly" value="${vo.author}"></td>
            </tr>
            <tr>
               <td>출판사</td>
               <td><input type="text" name="company" class="form-control" readonly="readonly" value="${vo.company}"></td>
            </tr>
            <tr>
               <td>isbn</td>
               <td><input type="text" name="isbn" class="form-control" readonly="readonly" value="${vo.isbn}"></td>
            </tr>
            <tr>
               <td>보유도서수</td>
               <td><input type="text" name="count" class="form-control"></td>
            </tr>
            <tr>
               <td colspan="2" align="center">
                  <button class="btn btn-success btn-sm" type="button" onclick="goUpdate()">수정</button>
                  <button class="btn btn-warning btn-sm" type="reset" id="fclear">취소</button>
                  <button onclick="goList()" class="btn btn-info btn-sm">리스트로가기</button>
               </td>            
            </tr>
         </table>
         </form>
      </div>
      
      
            
       <div class="panel-footer">스프링도서관-박병관</div>
     </div>
   </div>
   
   <script type="text/javascript">
      $(document).ready(function(){
         loadList();
      });
   
   
      function loadList(){
         $.ajax({
            url : "book/all",
            type : "get",
            dataType : "json",
            success : makeView,
            error : function() { alert("error"); }
         });
      }
      
      function makeView(data){
         
         var listHtml = "";
         $.each(data, function(index, obj){
            listHtml += "<tr>";
            listHtml += "<td>" + (index + 1) + "</td>";
            listHtml += "<td id='ta'>" + obj.title + "</td>";
            listHtml += "<td>" + obj.author + "</td>";
            listHtml += "<td>" + obj.company + "</td>";
            listHtml += "<td>" + obj.isbn + "</td>";
            listHtml += "<td id='c" + obj.num + "'>" + obj.count + "</td>";
            listHtml += "<td><button class='btn btn-primary btn-sm' type='button' onclick='goDelete("+ obj.num +")'>삭제</button></td>";
            listHtml += "<td><button class='btn btn-warning btn-sm' type='button' onclick='goUpdateForm("+ obj.num +")'>수정</button></td>";
            listHtml += "</tr>";

         });   
         
         $("#view").html(listHtml);
         
         goList();
         
      }
      
      // goForm 함수를 만들어서 view는 감추고 wform은 보이게하시오
      function goForm(){
         $("#boardList").css("display", "none");
         $("#wform").css("display", "block");
      }
      
      function goUpdateForm(){
          $("#boardList").css("display", "none");
          $("#uform").css("display", "block");
          updateForm();
       }
      
      function goList(){
         $("#boardList").css("display", "table-row");
         $("#wform").css("display", "none");
      }
      
      function goInsert(){
         var fData = $("#frm").serialize();
         
         $.ajax({
            url : "book/new",
            type : "post",
            data : fData,
            success : loadList,
            error : function() { alert("error") }
         });
         
         $("#fclear").trigger("click");
      }
      
      
      function goDelete(num){
         $.ajax({
            url : "book/" + num,
            type : "delete",
            data : {"num" : num},
            success : loadList,
            error : function() { alert("error"); }      
         });
      }
      
      function updateForm(num){
         
         $("#ta" + num).attr("readonly", true);
         var title = "<input id='ta"+num+"' value='" + title + "' type='text' class='form-control' >";
         $("#ta" + num).html(title); 
        
         
         var newBtn = "<button onclick='goUpdate("+num+")' class='btn btn-primary btn-sm'>수정</button>";
         $("#ub" + num).html(newBtn);
         
      }
      
      function goUpdate(num){
         
         var num = $("#nt" + num).val();
         var count = $("#nw" + num).val();

         $.ajax({
             url : "book/update",
             type : "put",
             contentType : "application/json",
             data : JSON.stringify({"num" : num, "count" : count}),
             success : loadList,
             error : function() { alert("error"); }      
          });
         
      }
      
   </script>
   
</body>
</html>