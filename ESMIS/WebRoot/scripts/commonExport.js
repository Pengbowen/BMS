/*
 * 通用导出JS
 * LoadDataUrl:打开页面时的查询接口
 * param:查询数据的的参数
 * modelId:excel模板Id
 * pageCount:要导出的总页数
 * recordCount:要导出的总条数
 * Date:2016/08/02
 * author:WYL
 * */
 
//json配置文件放为map
var myConfig = {};
//通过formatter转化后的对应值
var myDataList = [];

//读取json配置文件
function config(modelId,page){
	//查询到相对应的json配置文件
	var dataroot=rootPath+'exportJson/'+modelId+'.json';
	//解析返回的json
	$.getJSON(dataroot, function(data){
		//将field定义为key，formatter定义为value
		$.each(data.columnTitle,function(key,value){
			myConfig[value.field] = value.formatter;				
		});
		if(page == 1){
			//处理异步，获取当前页面数据
			pageData(modelId);
		}
	});
}

//导出全部数据js
function exportAllData(LoadDataUrl,param,modelId){
	myDataList = [];
	//传入导出进度窗体的参数,判断LoadDataUrl中是否含有"?"。
	if(LoadDataUrl.indexOf("?")!=-1){
		strData=encodeURIComponent(encodeURIComponent(LoadDataUrl+"&"+param+"&modelId="+modelId));
	}else{
		strData=encodeURIComponent(encodeURIComponent(LoadDataUrl+"?"+param+"&modelId="+modelId));
	}
	//判断导出数据是否为空
	if(recordCount==0){
		$.messager.alert("信息提示！","无数据,请查询后重试！");
	}else{
		//导出数据进度窗体
		var url = 'excelModel/exportAllData.jsp';
	    url = url +"?LoadDataUrl="+ strData;
		ExportProgress(rootPath + url);
	}
}

//导出全部数据页面
function ExportProgress(url){
	var content = '<iframe scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;"></iframe>';
	$('#win').window({
		width : 450,
		height : 260,
		title : '导出数据',
		// 定义窗口是否可拖拽。
		draggable : true,
		// 定义是否显示最大化按钮。
		maximizable : false,
		// 定义是否显示最小化按钮。
		minimizable : false,
		// 定义是否显示折叠按钮。
		collapsible : false,
		// 定义窗口是不是模态（modal）窗口。
		modal : true,
		content : content
	});
	// 窗口居中。
	$('#win').center;
}
//获取当前页的数据
function pageData(modelId){
	//获取当前页数据 json字符串
	//var data = JSON.stringify($('#' + gridviewId).datagrid('getData').rows);
	//获取当前页数据 json对象
	var data = ($('#' + gridviewId).datagrid('getData').rows);
	var everyPageSize = data.length;
	if(everyPageSize==0){
		$.messager.alert("信息提示！","无数据,请查询后重试！");
	}else{
		for ( var int = 0; int < data.length; int++) {
	    	var item = data[int];
	    	var index = myDataList.length;
	    	var result = {};
	    	$.each(item,function(key,value)
				{
					if(myConfig[key] != undefined)
					{
						if(myConfig[key]=="")
						{
							result[key] = value;
						}else{
							//执行数据中所包含的fomatter方法
							result[key] = eval(''+myConfig[key]+'("'+value+'", '+index+', '+int+');');
						}    								
					}
				});
				myDataList[index] = result;
			}
			//执行导出
			ImplementExportOne(myDataList,modelId);
		}
}

//导出当前页
function exportThisPage(modelId){
	//重置myDataList的值，避免导出时重复数据
	myDataList = [];
	//获取对应的json文件及当前页数据，config参数为1时，导出当前页，否则视为导出全部
	config(modelId,1);
}

//post提交后台，执行导出
function ImplementExportOne(myDataList,modelId){
	var exportUrl = rootPath+'commonexport/commonExport.action';
	var cdata =encodeURIComponent(JSON.stringify(myDataList));
	$.ajax({
		url:exportUrl,
		type:'JSON',
		async: false,
		method: 'post',
		data:{param:cdata,modelId:modelId,countNum:1},
		success:function(data){
			var url = rootPath+'commonexport/exportDownload.action';
			window.location.href = url+'?modelId='+modelId;
			$.messager.show({
				title:'信息提示！',
				msg:'导出已执行，完成后请注意保存查看！',
				timeout:3000,
				showType:'slide'
			});
		}
	});
}