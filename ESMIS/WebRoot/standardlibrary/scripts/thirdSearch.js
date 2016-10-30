function search(){

	if (!verifyData()){
		return;	
	}
	loadGridViewData();
	var p = $('#searchData').accordion('getPanel','查询结果');
	if (p){
		var index = $('#searchData').accordion('getPanelIndex', p);
		$('#searchData').accordion('select',index);  
	}
}

function checkValue()
{
	var obj,className,verifyfuc,tValue,queryList = $("[isrequired ='true']");
	var falg =false;
	if(queryList.length==0)  falg=true;
	for ( var i = 0; i < queryList.length; i++) {
		obj = $("#" + queryList[i].id);
		className = obj.attr("class");		
		verifyfuc= obj.attr("verifyfuc");		
		if(className !=undefined && className.indexOf("easyui-combobox")>=0)
		{
			tValue = jQuery.trim($("#"+queryList[i].id).combobox("getValue"));			
		}
		else if(className !=undefined && className.indexOf("easyui-combotree")>=0){
			
			tValue = jQuery.trim($("#"+queryList[i].id).combotree("getValue"));
		}
		else if(className !=undefined && className.indexOf("easyui-datebox")>=0)
		{
			tValue = jQuery.trim($("#"+queryList[i].id).datebox("getValue"));	
		}
		else
		{
			tValue = jQuery.trim(obj.val());
		}
		if (tValue == null)
			tValue = "";
		if (obj.attr("default") == null) {
			if (tValue == "-1") {
				tValue = "";
			}
		} else {
			if (tValue == obj.attr("default")) {
				tValue = "";
			}
		}
		
		if(tValue != "")
		{
			falg=true;
			if(verifyfuc !=undefined && verifyfuc !="")
			{
				falg=eval(verifyfuc);
				if(!falg) return falg;
			}
		}
	}
	if(!falg)  
	{
		$.messager.alert("信息提示", "必填项至少选择一个!");
	}
	return falg;
}

function verifyimportTime(){
	var importtimestart = jQuery.trim($("#importTimeStart").datebox('getValue'));
	var importtimeend = jQuery.trim($("#importTimeEnd").datebox('getValue'));
	
	if(importtimestart =="" && importtimeend =="") return true;
	if(importtimestart =="" && importtimeend !="") return true;
	if(importtimestart !="" && importtimeend =="") return true;
	
	if(importtimestart !="" && importtimeend !=""){
		if(importtimestart > importtimeend){
			$.messager.alert("信息提示","导入时间段开始不能大于导入时间段结束！");
			$("#importTimeStart").focus();
			return false;
		}else{
			return true;    			
		}
	}
}

//合同网签信息--统计报表，格式化操作列
function formateOperateFGJHTWQXX(value, row, index) {
	var html = "";
	html += '<A onclick="showTaskDetailPage(\'' +row.COLUMNF+ '\',\'' + row.COLUMNG + '\',\'' +row.COLUMNK+ '\')" ';
	html += 'href="javascript:void(0);" title="查看合同网签信息详情" class="icon icon-tasks"></A>';	
	return html;
}
