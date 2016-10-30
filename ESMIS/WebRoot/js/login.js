$(function () {
	var userNameCookie=$.cookie('ly_Current_Login_UserName');
	var userPwdCookie=$.cookie('ly_Current_Login_UserPWD');
	if(sessionTag && userNameCookie!='' && userPwdCookie!=''){
		var url = currentUrl+"/login/login.action";
    	var data = "username=" + encodeURIComponent(userNameCookie);
    	data = data + "&password=" + encodeURIComponent(userPwdCookie);
    	data = data + "&rnd=" + (Math.random() * 10 + 1);
    	AjaxJson(url, true, data);
	}

    H_login = {};
    H_login.openLogin = function(){
        $('.login-box').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
            $('.login').hide();
            $('.login-bg').hide();
        });
    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click',function(){
              var username = $("#username");
              var usernameValue = $("#username").val();
              var password = $("#password");
              var passwordValue = $("#password").val();
            if(usernameValue == ""){
                alert("用户名不能为空");
                username.focus();
                return false;
            }else if(usernameValue.length > 15){
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            }else if(passwordValue == ""){
                alert("密码不能为空");
                password.focus();
                return false;
            }else if(passwordValue.length < 6 || passwordValue.length > 30){
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            }else{
            	// 会员用户名和密码
            	var url = document.getElementById("form").action;
            	var data = "username=" + encodeURIComponent(usernameValue);
            	data = data + "&password=" + encodeURIComponent(passwordValue);
            	data = data + "&rnd=" + (Math.random() * 10 + 1);
            	AjaxJson(url, true, data, doresponse);
                setTimeout(function(){
                    $('.login').hide();
                    $('.login-bg').hide();
                    $('.list-input').val('');
                },2000);
            }
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();
});

function doresponse(rtnjson) {
	if (rtnjson.result == "1") {
		var usernameValue = $("#username").val();
		var passwordValue = $("#password").val();
		var rememberMe=$("input[type='checkbox']:checked").length;
		if(rememberMe==1){
			$.cookie('ly_Current_Login_UserName', usernameValue, { expires: 7 });
			$.cookie('ly_Current_Login_UserPWD', passwordValue, { expires: 7 });
		}
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