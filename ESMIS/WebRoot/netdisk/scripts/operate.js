/**
 * @Title: operate.js
 * @Package netdisk/scripts/operate.js
 * @Description: 知识库操作类js
 * @author WYL
 * @date 2016年10月09日
 */

//将以后所需要的值定义为全局变量,稍后取值使用
	var iobjectNo="";
	var iobjectName="";
	var iparentobjectNo="";
	var ichirldItemCount="";
	var iobjectType="";
//通过对象名查找对象，只查当前目录下，方法实现调用ConditionGroup查询
function searchByObjectName() {
	var parentobjectNo = $("#parentobjectNo").val();
	var objectName;
	if ($("#objectName").val() == "请输入文件名称" || $("#objectName").val() == "") {
		alert("请输入文件名称！");
	} else {
		objectName = $("#objectName").val();
		JSLoadData({
			parentobjectNo : parentobjectNo,
			objectName : objectName
		},"netdisk/searchByAll.action");
	}
}
//鼠标悬浮时赋值
function getParams(no,name,pno,cont,type){
	iobjectNo=no;
	iobjectName=name;
	iparentobjectNo=pno;
	ichirldItemCount=cont;
	iobjectType=type;
}
//下载文件
function Download() {
	if (iobjectNo == "" || iobjectNo == undefined) {
		alert("请选择要需要下载的文件！");
	} else {
		//根据文件编号，查找到文件执行下载
		var URL = rootPath + 'netdisk/downloadNetDisk.action?objectNo=' + iobjectNo;
		window.location.href = URL;
	}
}

//contetn为要显示的内容
//height为离窗口顶部的距离
//time为多少秒后关闭的时间，单位为秒
function showTips(content, height, time) {
	//窗口的宽度
	var windowWidth = $(window).width();
	var tipsDiv = '<div class="tipsClass">' + content + '</div>';
	$('body').append(tipsDiv);
	$('div.tipsClass').css({
		'top' : height + 'px',
		'left' : (windowWidth / 2) - 350 / 2 + 'px',
		'position' : 'absolute',
		'padding' : '3px 5px',
		'background' : '#8FBC8F',
		'font-size' : 24 + 'px',
		'margin' : '0 auto',
		'text-align' : 'center',
		'z-index': '998',
		'width' : '350px',
		'height' : 'auto',
		'color' : 'red',
		'opacity' : '0.8'
	}).show();
	setTimeout(function() {
		$('div.tipsClass').fadeOut();
	}, (time * 1000));
}