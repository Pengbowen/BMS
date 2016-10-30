	
	function reply(id){
		var url = rootPath+"answer/skipToReply.action?id="+id;
		top.tab("回复信息",url);
	}
	function detail(id){
		var url = rootPath+"answer/skipToDetail.action?id="+id;
		top.tab("查看详情",url);
	}
	
	function add(submitType,title){
		var url = rootPath+"answer/skipToAdd.action?submitType="+submitType;
		top.tab(title,url);
	}
	
	//操作
	function operateFormatter(index,row,value){
		var html = '';
		var id = row.id;
		var submitTitle = row.submitTitle;
		var isreceive = row.isreceive;
		var deleteUrl = rootPath+"answer/deleteAnswer.action?id="+id;
		html += '<a class="icon icon-search" title="查看详情" href="javascript:void(0);" onclick="detail('+id+')"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		if(isreceive == 1){
			html += '<a href="javascript:void(0);" title="回复信息" class="icon icon-share-alt" style="color:#DDDDDD;"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		}
		if(isreceive == 0){
			html += '<a onclick="reply('+id+')" href="javascript:void(0);" title="回复信息" class="icon icon-share-alt"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		}
		html += '<a onclick="DeleteData(\''+deleteUrl+'\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		
		return html;
	}	
	
	//回复状态
	function isreceiveFormatter(index,row,value){
		var isreceive = row.isreceive;
		var state = "";
		if(isreceive == 1){
			state = "已回复";
		}
		if(isreceive == 0){
			state = "未回复";
		}
		return state;
	}
	
	//提交时间
	function submitTimeFormatter(index,row,value){
		var submitTime = row.submitTime;
		return submitTime.substring(0,10);
	}
	
	//标题
	function submitTitleFormatter(index,row,value){
		var html = '';
		var id = row.id;
		html += '<a title="查看详情" href="javascript:void(0);" onclick="detail('+id+')">'+row.submitTitle+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';
		return html;
	}
	
	// 弹窗方法
	function openNow(title,url,width,height) {
	  var content = '<iframe scrolling="auto" frameborder="0"  src="'
	      + url + '" style="width:100%;height:100%;"></iframe>';
	  if(arguments.length<4){
	  	width = 450;
	  	height = 480;
	  }    
	  $('#win').window({
	    width : width,
	    height : height,
	    title : title,
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
