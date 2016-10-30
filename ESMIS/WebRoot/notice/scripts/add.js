
	
	//保存数据
	function saveMessage() {
		if(!verifyData()){
			return;
		}
		var url;
		var noticeContent = editor.html().replace(new RegExp(/(\n)/g),'');
		if ($("#id").val() == "") {
			url = "notice/addNotice.action";
		} else {
			url = "notice/updateNotice.action";
		}
		var strData="id="+$('#id').val()+
				 "&noticeTitle="+$('#noticeTitle').val()+"&noticeContent="+encodeURIComponent(noticeContent)+
				 "&deadline="+$('#deadline').datebox('getValue');
		AjaxJson(rootPath + url, true, strData, message);
	}
	
	function message(data){
		if (data.result == "1") {
			$.messager.alert("信息提示", data.message,'',function(r){
				top.refreshAppointTabs(rootPath+"notice/skipToNotice.action","通知公告");
			});
		}else{
			$.messager.alert("信息提示！", data.message);
		}
	}

	// 验证数据
	function verifyData() {
		if (jQuery.trim($("#noticeTitle").val()) == "") {
			$.messager.alert('提示信息', '标题不能为空！');
			$("#noticeTitle").focus();
			return false;
		}
		if (editor.html().trim() == "") {
			$.messager.alert('提示信息', '内容不能为空！');
			$("#noticeContent").focus();
			return false;
		}
		if (jQuery.trim($('#deadline').datebox('getValue')) == "") {
			$.messager.alert('提示信息', '截止时间不能为空！');
			$("#deadline").focus();
			return false;
		}
		return true;
	}