	var rootPath = "${pageContext.request.contextPath}/";
	
	//保存数据
	function saveMessage() {
		if(!verifyData()){
			return;
		}
		var url;
		if ($("#id").val() == "") {
			url = "blogroll/addBlogroll.action";
		} else {
			url = "blogroll/updateBlogroll.action";
		}
		var strData="id="+$('#id').val()+
				 "&linkName="+$('#linkName').val()+"&linkeUrl="+$('#linkeUrl').val()+
				 "&pictureUrl="+$('#pictureUrl').val()+"&orderNumber="+$('#orderNumber').val();
		AjaxJson(rootPath + url, true, strData, message);
	}
	
	function message(data){
		if (data.result == "1") {
			$.messager.alert("信息提示", data.message,'',function(r){
				resetParent();
			});
		}else{
			$.messager.alert("信息提示！", data.message);
		}
	}
	//关闭本页面，刷新父页面
	function resetParent(){
		//关闭本窗口
		window.parent.closeWin();
		//刷新父级窗口
		window.parent.refreshSelTab();
	}
	
	// 验证数据
	function verifyData() {
		
		if (jQuery.trim($("#linkName").val()) == "") {
			$.messager.alert('提示信息', '链接名称不能为空！');
			$("#linkName").focus();
			return false;
		}
		if (jQuery.trim($("#linkeUrl").val()) == "") {
			$.messager.alert('提示信息', '链接地址不能为空！');
			$("#linkeUrl").focus();
			return false;
		}
		var url = $('#linkeUrl').val();
		var regExp1 = /^((https?|ftp|http):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
		if(!url.match(regExp1)){
			$.messager.alert('提示信息', '链接地址网址格式不正确！');
			return false;
		}
		var orderNumber = $('#orderNumber').val();
		var regExp = /^[0-9]*$/ ;
		if(!orderNumber.match(regExp)){
			$.messager.alert('提示信息', '显示顺序必须为数字！');
			return false;
		}
		/*var picture = $('#pictureUrl').val();
		if(picture.trim() != ""){
			if(!picture.match(regExp1)){
				$.messager.alert('提示信息', '图片地址网址格式不正确！');
				return false;
			}
		}*/
		return true;
	}