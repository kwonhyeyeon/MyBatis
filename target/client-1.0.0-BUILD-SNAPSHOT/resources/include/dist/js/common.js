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