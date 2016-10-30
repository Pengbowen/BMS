<%@page import="com.lanyuan.assembly.view.control.FileUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
    String path = WebSite.getWebSiteUrl(request); //网站地址
	String basePath = WebSite.getManageUrl(request);//后台管理页面地址
%>

<%@page import="com.lanyuan.assembly.view.WebSite"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>extjs/resources/css/ext-all.css"
	mce_href="<%=basePath%>extjs/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=basePath%>extjs/adapter/ext/ext-base.js"
	mce_src="<%=basePath%>extjs/adapter/ext/ext-base.js"></script>
<script src="<%=basePath%>extjs/ext-all-debug.js"
	mce_src="<%=basePath%>extjs/ext-all-debug.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>extjs/examples/ux/FileUploadField.js"
	mce_src="<%=basePath%>extjs/examples/ux/FileUploadField.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajaxjson.js"> </script>
<style type=text/css>
.upload-icon {
	background: url('<%=basePath%>images/image_add.png') no-repeat 0 0 !important;
}

.x-form-file-wrap {
	position: relative;
	height: 60px;
}

.x-form-file-wrap .x-form-file {
	position: absolute;
	right: 0;
	-moz-opacity: 0;
	filter: alpha(opacity :     0);
	opacity: 0;
	z-index: 2;
	height: 60px;
}

.x-form-file-wrap .x-form-file-btn {
	position: absolute;
	right: 0;
	z-index: 1;
}

.x-form-file-wrap .x-form-file-text {
	position: absolute;
	left: 0;
	z-index: 3;
	color: #777;
}
</style>

<script type="text/javascript">       
	var rootPath="<%=basePath%>";
	Ext.onReady(function() {
				Ext.QuickTips.init();
				var msg = function(title, msg) {
					Ext.Msg.show({
						title : title,
						msg : msg,
						minWidth : 200,
						modal : true,
						icon : Ext.Msg.INFO,
						buttons : Ext.Msg.OK
					});
				};
				var fp = new Ext.FormPanel(
						{
						    url: rootPath+"extjs/upload/uploadController.jsp?t="+ Math.random()+"&fileURL=uploadfiles/sst",
							renderTo : 'fi-form',
							fileUpload : true,
							width : 500,
							frame : true,
							autoHeight : true,
							bodyStyle : 'padding: 10px 10px 0 10px;',
							labelWidth : 50,
							defaults : {
								anchor : '95%',
								allowBlank : false,
								msgTarget : 'side'
							},
							items : [  {
								xtype : 'fileuploadfield',
								id : 'filepath',
								emptyText : '请选择xls或xlsx文件',
								fieldLabel : '文件',
								name : 'filepath',
								buttonText : '',
								validator: function(value){
								 var maxsize = 5*1024*1024;//5M  
						         var arr = value.split('.');
						         if(arr[arr.length-1] != 'xls' && arr[arr.length-1] != 'xlsx'){
						             return '文件不合法！！！';
						           }else{
						             return true;
						            }
						            
	          },
								buttonCfg : {
									iconCls : 'upload-icon'
								}
							} ],
							buttons : [
									{
										text : '开始上传',//点击'开始上传'之后，将由这个function来处理。
										handler : function() {
											if (fp.getForm().isValid()) {//验证form
												//显示进度条
												Ext.MessageBox.show({
													title : '正在上传文件',
													width : 240,
													progress : true,
													closable : false,
													buttons : {
														cancel : '取消'
													}
												});

												fp.getForm().submit();//form提交
												//设置一个定时器，每100毫秒向processController发送一次ajax请求
												var timer = setInterval(
														function() {
															Ext.Ajax.request({
														
																		//以后凡是在ajax的请求的url上面都要带上日期戳，否则极有可能每次出现的数据都是一样的，这和浏览器缓存有关
																		url : rootPath+ "extjs/upload/processController.jsp?t="+ Math.random(),
																		method : "get",
																		success : function(
																				response,
																				options) {
																			var obj = Ext.util.JSON.decode(response.responseText);
																			if (obj.success != false) {
																				if (obj.finished) {
																					clearInterval(timer);
																					document.getElementById("savedpath").value=obj.newfile;
																					saveMessage();
																					Ext.MessageBox.updateProgress(1,'正在分析处理文件...','finished');
																					Ext.MessageBox.hide();
																				} else {
																					Ext.MessageBox.updateProgress(obj.percentage,obj.msg);
																				}
																			}
																		},
																		failure : function() {
																			clearInterval(timer);
																			Ext.Msg.alert('错误','发生错误了。');
																		}
																	});

														}, 300);

											} else {
												Ext.Msg.alert("消息",
														"请上传\"xls或xlsx\"格式的文件.");
											}
										}

									}, {
										text : '重置',
										handler : function() {
											fp.getForm().reset();
										}
									} ]
						});
			});
			
			
	var data={};
	function saveMessage() {
		 if(!checkData()){
			 return;
		 }
		var url = "";
		var strData="savedpath=" + $("#savedpath").val();
		url = "<%=basePath%>sst/uploadStandard.action";
		AjaxJson(url, true, strData, doDocAdd);
	}
	function doDocAdd(data) {
		if (data.result == "1"){
			Ext.Msg.alert(data.message);
		} else {
			Ext.Msg.alert(data.message);
		}
	}
	function checkData(data) {
		if(jQuery.trim($("#savedpath").val())==""){
			alert("请选择上传文件");
			$("#savedpath").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="fi-form"></div>
    <input type="hidden" id="savedpath" />
</body>
</html>
