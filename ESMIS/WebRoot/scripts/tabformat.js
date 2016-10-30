
//格式化鼠标点击操作
$(function() {
	tabClose();
	tabCloseEven();
});

//刷新指定tabs    url指定的tabs访问的路径   title指定的tabs名称
function refreshAppointTabs(url,title){
	//获取当前页签
	var selectTab = $('#tabs').tabs('getSelected');
	//关闭
	$('#tabs').tabs('close',title);
	//打开
	tab(title,url);
	$('#tabs').tabs('close',top.$('#tabs').tabs('getTabIndex',selectTab));
}


// 添加标签页 不存在创建 存在选中
function tab(title, url,length) {
	if (!$('#tabs').tabs('exists', title)) {
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		$('#tabs').tabs('add', {
			title : title,
			iconCls : 'icon-tip',
			content : content,
			closable : true,
		});
		//$('#tbodyLayout').layout('collapse','west'); 
	} else {
		$('#tabs').tabs('select', title);
		//如果存在刷新页面
		$('#mm-tabupdate').click();
		//隐藏菜单栏
		//$('#tbodyLayout').layout('collapse','west'); 
	}
	
	tabClose();
}

// 双击鼠标关闭当前标签页
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		var subtitle = $(this).children(".tabs-closable").text();
		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
function closeSelectedTab()
{
	var title = $(".tabs-selected").text();
	$("#tabs").tabs("close",title);
}	

//刷新选中的页签
function refreshSelTab()
{
	var currTab = $('#tabs').tabs('getSelected');
	var url = $(currTab.panel('options').content).attr('src');
	$('#tabs').tabs('update', {
		tab : currTab,
		options : {
			content : createFrame(url)
		}
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		refreshSelTab();
	});

	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if(t != "主页"){
			$('#tabs').tabs('close', t);}
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			$.messager.alert('提示', '前边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if(t != "主页"){
			$('#tabs').tabs('close', t);}
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			$.messager.alert('提示', '到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if(t != "主页"){
			$('#tabs').tabs('close', t);}
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

//点击左侧导航栏刷新路径
function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}