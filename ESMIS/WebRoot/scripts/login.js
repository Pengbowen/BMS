
function SubmitLogin() {
	var username = document.getElementById("username");
	if (username.value == "" || username.value == null) {
		document.getElementById("show1").innerHTML = "请输入用户名";
		document.getElementById("show1").style.color = "red";
		return;
	}

	var password = document.getElementById("password");
	if (password.value == "" || password.value == null) {
		document.getElementById("show2").innerHTML = "请输入密码";
		document.getElementById("show2").style.color = "red";
		return;
	}
	// 会员用户名和密码
	var url = document.getElementById("form").action;
	var data = "username=" + encodeURIComponent(username.value);
	data = data + "&password=" + encodeURIComponent(password.value);
	data = data + "&rnd=" + (Math.random() * 10 + 1);
	AjaxJson(url, true, data, doresponse);
}

function doresponse(rtnjson) {
	if (rtnjson.result == "1") {
		setTimeout("fReturnUrl('" + currentUrl + "')", 1000);
	} else {
		alert(rtnjson.message);
		return;
	}
}

function fReturnUrl(cURL) {
	var objForm = document.getElementById("form");
	objForm.action = cURL;
	objForm.submit();
}

document.onkeydown = function(e) {
	var e = e || event;
	if (e.keyCode == 13) {
		SubmitLogin();
	}
};
