function AjaxJsonEx(sendUrl,BType,cData,asyncFunctionPointer,bAsync,param1,param2,param3)
{	
	var cURL=getUrl(sendUrl);
	if(arguments.length<5)
	{
	  var bAsync=true;
		}	
	else
	{
	  var bAsync=arguments[4];
		}
	$.ajax({
		url:cURL,
		type:(BType?"post":"get"),
		data:cData,
		async:bAsync,
		dataType:"json",
		success:function(msg){
			if(typeof asyncFunctionPointer == 'function')
			{
				if(param3!=undefined){
					asyncFunctionPointer(eval(msg),param1,param2,param3);					
				}else if(param2!=undefined){
					asyncFunctionPointer(eval(msg),param1,param2);					
				}else if(param1!=undefined){
					asyncFunctionPointer(eval(msg),param1);
				}else{
					asyncFunctionPointer(eval(msg));
				}
				
			}
			else
			{
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			var rtnStr = "";
			rtnStr = "请求失败\r\n";
			rtnStr += "当前状态为：" + textStatus + "\r\n";
			rtnStr += "错误描述：" + errorThrown + "\r\n";
			alert(rtnStr);
			document.write(XMLHttpRequest.responseText);
		}
	});
}

function AjaxJson(sendUrl,BType,cData,asyncFunctionPointer,bAsync,bAlertTag)
{	
	var cURL=getUrl(sendUrl);
	
	if(arguments.length<5)
	{
	  var bAsync=true;
		}	
	else
	{
	  var bAsync=arguments[4];
		}
	if(arguments.length<6)
	{
	  var bAlertTag=true;
		}	
	else
	{
	  var bAlertTag=arguments[5];
		}
	$.ajax({
		url:cURL,
		type:(BType?"post":"get"),
		data:cData,
		async:bAsync,
		dataType:"json",
		success:function(msg){
			if(typeof asyncFunctionPointer == 'function')
			{
				asyncFunctionPointer(eval(msg));
			}
			else
			{
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			var rtnStr = "";
			rtnStr = "请求失败\r\n";
			rtnStr += "当前状态为：" + textStatus + "\r\n";
			rtnStr += "错误描述：" + errorThrown + "\r\n";
			if(bAlertTag){
				alert(rtnStr);
				document.write(XMLHttpRequest.responseText);
			}		
		}
	});
}

function AjaxText(sendUrl,BType,cData,asyncFunctionPointer,bAsync)
{	
	var cURL=getUrl(sendUrl);
	
	if(arguments.length<5)
	{
	  var bAsync=true;
		}	
	else
	{
	  var bAsync=arguments[4];
		}
	$.ajax({
		url:cURL,
		type:(BType?"post":"get"),
		data:cData,
		async:bAsync,
		dataType:"text",
		success:function(msg){		
			if(typeof asyncFunctionPointer == 'function')
			{
				asyncFunctionPointer(msg);
			}
			else
			{
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			var rtnStr = "";
			rtnStr = "请求失败\r\n";
			rtnStr += "当前状态为：" + textStatus + "\r\n";
			rtnStr += "错误描述：" + errorThrown + "\r\n";			
			alert(rtnStr);	
			document.write(XMLHttpRequest.responseText);
		}
	});
}

function getUrl(sendurl)
{
	if(sendurl==null||sendurl=="")return "";
	var url="";
	if(sendurl.indexOf(":80/")>0){
		var suffix=sendurl.substring(0,sendurl.indexOf(":80/"));
		var enffix=sendurl.substring(sendurl.indexOf(":80/")+3,sendurl.length);
		url=suffix+""+enffix;
	}else{
		url=sendurl;
	}
	return 	url;
}