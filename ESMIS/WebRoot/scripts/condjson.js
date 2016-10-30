/**
 * 查询条件组对象集合
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function CondJson()
{
	this.group = [];
	
	this.add = function(queryObj){
		var len = this.group.length;
		this.group[len] = queryObj;
	};
	
	this.toJson = function(){
		if(!JSON.stringify){
			var header = document.getElementsByTagName("head")[0];
			var script = document.createElement("script");
			script.setAttribute("type","text/javascript");
			script.setAttribute("src",GetPath("condjson.js") + "json2.js");
			header.appendChild(script);
		}
		
		if(this.group.length>0)
		{
		    return JSON.stringify(this.group);
		}else{
			return "";
		}
	};
}

/**
 * 查询条件对象
 * @memberOf {TypeName} 
 */
function MetaJson()
{
	this.setid = "";
	this.mappingid = "";
	this.value = "";
	this.operator = "";
	this.connector = "";
	this.type = 0;
}

function OrderJson()
{
	this.group = [];
	
	this.add = function(queryObj){
		var len = this.group.length;
		this.group[len] = queryObj;
	};
	
	this.toJson = function(){
		if(!JSON.stringify){
			var header = document.getElementsByTagName("head")[0];
			var script = document.createElement("script");
			script.setAttribute("type","text/javascript");
			script.setAttribute("src",GetPath("condjson.js") + "json2.js");
			header.appendChild(script);
		}
		
		if(this.group.length>0)
		{
		    return JSON.stringify(this.group);
		}else{
			return "";
		}
	};
}
/**
 * 排序元素对象 
 */
function OrderItem()
{
	this.setid = "";
	this.mappingid = "";
	this.order = "";
}

//获取类地址	
function GetPath(JSFileName) {
	var js = document.scripts || document.getElementsByTagName("script");
	var jsPath;
	for ( var i = js.length; i > 0; i--) {
		//包含“/文件名”或=文件名称
		if ((js[i - 1].src.indexOf("/" + JSFileName) > -1)
				|| (js[i - 1].src == JSFileName)) {
			jsPath = js[i - 1].src.substring(0, js[i - 1].src
					.lastIndexOf("/") + 1);
		}
	}
	return jsPath;
}