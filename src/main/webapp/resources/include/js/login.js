$(function(){
<<<<<<< HEAD
	//아래 함수는 서버로 받아 온 코드값에 대한 처리 함수 (login.jsp 파일에 정의.)
	errCodeCheck()
	$('#userId, #userPw').bind("keyup", function(){
			$(this).parents("div").find(".error").html("");
	});
	/*로그인 버튼 클릭시 처리 이벤트*/
	$("#loginBtn").click(function() {
		if (!formCheck($('#userId'), $('.error:eq(0)'), "아이디를")) return;
		else if(!inputVerify(0, '#userId', '.error:eq(0)')) return;
		else if(!formCheck($('#userPw'),$('.error:eq(1)'), "비밀번호를"))return;
		else if(!inputVerify(1, "#userPw", '.error:eq(1)')) return;
		else{
			$("#loginForm").attr({
				"method":"POST",
				"action":"/member/login.do"
			});
			$("#loginForm").submit();
		}
	});
	/*회원가입 버튼 클릭 시 처리 이벤트*/
	$("#joinBtn").click(function(){
		location.href="/member/join.do";
	});
});	
=======
   //아래 함수는 서버로 받아 온 코드값에 대한 처리 함수 (login.jsp 파일에 정의.)
   errCodeCheck()
   $('#userId, #userPw').bind("keyup", function(){
         $(this).parents("div").find(".error").html("");
   });
   /*로그인 버튼 클릭시 처리 이벤트*/
   $("#loginBtn").click(function() {
      if (!formCheck($('#userId'), $('.error:eq(0)'), "아이디를")) return;
      else if(!inputVerify(0, '#userId', '.error:eq(0)')) return;
      else if(!formCheck($('#userPw'),$('.error:eq(1)'), "비밀번호를"))return;
      else if(!inputVerify(1, "#userPw", '.error:eq(1)')) return;
      else{
         $("#loginForm").attr({
            "method":"POST",
            "action":"/member/login.do"
         });
         $("#loginForm").submit();
      }
   });
   /*회원가입 버튼 클릭 시 처리 이벤트*/
   $("#joinBtn").click(function(){
      location.href="/member/join.do";
   });
});   

var pattern = [ "((?=.*[a-zA-Z])(?=.*[0-9]).{6,10})",
	"((?=.*[a-zA-Z])(?=.*[0-9@#$%]).{8,12})", "^\\d{3}-\\d{3,4}-\\d{4}" ];

function inputVerify(index, data, printarea) {
	var data_regExp = new RegExp(pattern[index]);
	var match = data_regExp.exec($(data).val());
	if (match == null) {
		$(printarea).html("입력값이 형식에 맞지 않습니다. 다시 입력해 주세요.");
		$(data).val("");
		return false;
	} else {
		return true;
	}
}
>>>>>>> cd9e3bfe29da4a3a02e9e2b4997f882025dbbad8

//유효성체크, 출력영역, 메시지내용
function formCheck(main, item, msg){
   if(main.val().replace(/\s/g,"")==""){
      item.css("color", "#000099").html(msg + " 입력해 주세요.");
      main.val("");
      return false;
   }else{
      return true;
   }
}
<<<<<<< HEAD
	
=======
   
>>>>>>> cd9e3bfe29da4a3a02e9e2b4997f882025dbbad8
