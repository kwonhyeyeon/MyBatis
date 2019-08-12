/**
 * 
 */
// 유효성 체크, 메시지 내용
function chkSubmit(item, msg){
	if(item.val().replace(/\s/g,"")==""){
		alert(msg+" 입력해 주세요.");
		item.val("");
		item.focus();
		return false;
	}else{
		return true;
	}
}
// 유효성 체크 대상, 메시지 내용
// placeholder 속성을 이용하여 msg출력
function checkForm(item, msg){
	var message = "";
	if(item.val().replace(/\s/g,"")==""){
		message = msg + " 입력해 주세요.";
		item.attr("placeholder", message);
		return false;
	}else{
		return true;
	}
}
// 유효성체크, 출력영역, 메시지내용
function formCheck(main, item, msg){
	if(main.val().replace(/\s/g,"")==""){
		item.css("color", "#000099").html(msg + " 입력해 주세요.");
		main.val("");
		return false;
	}else{
		return true;
	}
}

function chkData(item, msg){
	if($(item).val().replace(/\s/g,"")==""){
		alert(msg + " 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	}else{
		return true;
	}
}


/*배열: 유효성 체크 시 필요한 정규식으로 배열을 초기화.
 * pattern = [아이디 , 비밀번호 , 핸드폰번호]
 * 함수명:inputVerify(배열 인덱스번호, 비교할 값 , 출력영역)
 */
var pattern = [
	 "((?=.*[a-zA-Z])(?=.*[0-9]).{6,10})",
	 "((?=.*[a-zA-Z])(?=.*[0-9@#$%]).{8,12})",
	 "^\\d{3}-\\d{3,4}-\\d{4}"];

function inputVerify(index, data, printarea){
	 var data_regExp = new RegExp(pattern[index]);
	 var match = data_regExp.exec($(data).val());
	 if(match==null){
		 $(printarea).html("입력값이 형식에 맞지 않습니다. 다시 입력해 주세요.");
		 $(data).val("");
		 return false;
	 }else{
		 return true;
	 }
}