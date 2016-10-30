
var arrSelected = new Array();
function copyToClipboard(txt) {
	if (window.clipboardData) {
		window.clipboardData.clearData();
		clipboardData.setData("Text", txt);
		// alert("复制成功！");
	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager
					.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1']
				.createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1']
				.createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;

		trans.addDataFlavor("text/unicode");
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"]
				.createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode", str, copytext.length * 2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans, null, clipid.kGlobalClipboard);
		// alert("复制成功！");
	}
}

// 提交查询表单-常规查询
function cxSubmit() {
	var frm = document.getElementById("from_find");
	var numargs = arguments.length;
	if (numargs > 0) {
		if (arguments[0] != '') {
			frm.action = arguments[0];
		}
	}

	$("#conditionString").val(getNormalJsonString());
	frm.submit();
}

// 根据设置的查询条件，生成json字符串
function getNormalJsonString() {
	var j = 0;

	var objList = new CondJson(), item = new MetaJson();
	var filter = ",-1,,";
	var tStart = "", tEnd = "";
	var temp = "", tValue;

	var objData = JSON.parse($("#defaultString").val());
	for ( var i = 0, len = objData.length; i < len; i++) {
		elem = objData[i];
		temp = elem.mappingid;
		tStart = temp + "-start";
		tEnd = temp + "-end";
		if (filterStr(filter, temp)) {
			tValue = "";
			if (elem.type == 1) {
				tStart = jQuery.trim($("#" + elem.mappingid + "-start").val());
				tEnd = jQuery.trim($("#" + elem.mappingid + "-end").val());
				if (tEnd.length == 10) {
					tEnd += " 23:59:59";
				}

				if (tStart.length > 0 || tEnd.length > 0) {
					tValue = {
						"start" : encodeURI(tStart),
						"end" : encodeURI(tEnd)
					};
				}
			} else {
				tValue = encodeURI(jQuery.trim($("#" + elem.mappingid).val()));
			}
			if (tValue != "") {
				item = new MetaJson();
				item.setid = elem.setd;
				item.mappingid = elem.mappingid;
				item.value = tValue;
				item.operator = elem.operator;
				item.connector = (elem.connector == "" ? "" : elem.connector);
				item.type = elem.type;
				objList.add(item);
			}
		}
	}
	var i = objList.group.length;
	if (i > 0) {
		objList.group[0].connector = "";
	}
	return objList.toJson();
}

// 根据参与查询的控件，生成json字符串
function compareConditionByQuery() {
	var position = 0, isRange = false;
	var obj, queryList = $("[inQuery='true']");

	var tMappingId, tValue, tOperator;
	var objList = new CondJson(), item = new MetaJson();

	var className;
	for ( var i = 0; i < queryList.length; i++) {
		obj = $("#" + queryList[i].id);
		tMappingId = obj.attr("alias");
		if (tMappingId == null || tMappingId == "") {
			tMappingId = obj.attr("id");
		}
		className = obj.attr("class");		
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
		else if(className !=undefined && className.indexOf("easyui-datetimebox")>=0)
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

		tOperator = obj.attr("operator");
		if (tOperator == null)
			tOperator = "";
		
//		alert("classNameINDEX::"+className.indexOf("easyui-datebox"));
		if(className !=undefined && className.indexOf("easyui-datebox")>=0)
		{
//			alert("tMappingId::"+tMappingId);
			// 判断范围查询-起始
			position = tMappingId.indexOf("Start", 0);
//			alert("position::"+position);
			if (position > 0) {
				tMappingId = tMappingId.substring(0, position);
//				alert(tMappingId);
				if (tValue.length == 10)
					tValue += " 00:00:00";
				if (tOperator == "")
					tOperator = ">=";
//				alert("start::"+tValue);
			} else {
				// 判断范围查询-结束
				position = tMappingId.indexOf("End", 0);
				if (position > 0) {
					tMappingId = tMappingId.substring(0, position);
					if (tValue.length == 10)
						tValue += " 23:59:59";
					if (tOperator == "")
						tOperator = "<=";
//					alert("end::"+tValue);
				}
			}
		}
		if(className !=undefined && className.indexOf("easyui-datetimebox")>=0)
		{
			// 判断范围查询-起始
			position = tMappingId.indexOf("Start", 0);
			if (position > 0) {
				tMappingId = tMappingId.substring(0, position);
				if (tOperator == "") tOperator = ">=";
			} else {
				// 判断范围查询-结束
				position = tMappingId.indexOf("End", 0);
				if (position > 0) {
					tMappingId = tMappingId.substring(0, position);
					if (tOperator == "") tOperator = "<=";
				}
			}
		}
		
		// 判断时间段查询-开始时间
		position = tMappingId.indexOf("-TimeStart", 0);
		if (position > 0) {
			tMappingId = tMappingId.substring(0, position);
			if (tOperator == "")
				tOperator = ">=";
		} else {
			// 判断时间段查询-结束时间
			position = tMappingId.indexOf("-TimeEnd", 0);
			if (position > 0) {
				tMappingId = tMappingId.substring(0, position);
				if (tValue.length == 10)
					tValue += " 23:59:59";
				if (tOperator == "")
					tOperator = "<=";
			} else {
				// 判断范围查询-起始
				position = tMappingId.indexOf("-Start", 0);
				if (position > 0) {
					tMappingId = tMappingId.substring(0, position);
					if (tOperator == "")
						tOperator = ">=";
				} else {
					// 判断范围查询-结束
					position = tMappingId.indexOf("-End", 0);
					if (position > 0) {
						tMappingId = tMappingId.substring(0, position);
						if (tOperator == "")
							tOperator = "<=";
					}
				}
			}
		}

		if (tValue != "") {
			item = new MetaJson();
			item.setid = "";
			item.mappingid = tMappingId;
			item.value = encodeURI(tValue);
			item.operator = tOperator == "" ? "=" : tOperator;
			item.connector = "and";
			item.type = "0";
			objList.add(item);
		}
	}
	i = objList.group.length;
	if (i > 0)
		objList.group[0].connector = "";
	return objList.toJson();
}

function compareOrderByColumn(sort, order) {
	var objList = new OrderJson(), item = new OrderItem();
	item.setid = "";
	item.mappingid = sort;
	item.order = order;
	objList.add(item);
	$("#orderString").val(objList.toJson());
	searchByCondition();
}

function filterStr(track, need) {
	return track.indexOf("," + $("#" + need).val() + ",") == -1;
}

// easyui 操作 刷新页面
function OptEasyUIToAjax(url, MsgTitle) {
	$.messager.confirm('提示', MsgTitle, function(r) {
		if (r) {
			AjaxJson(url, true, "", function(data){
				$.messager.alert("提示信息！",data.message,"",function(e){
					location.reload();
				});
			});
		}
	});
}
