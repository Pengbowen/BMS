	function operateFormatter(index,row,value){
		var html = '';
		var id = row.id;
		var deleteUrl = rootPath+"notice/deleteNotice.action?id="+id;
		html += '<a class="icon icon-pencil" title="修改" href="javascript:void(0);" onclick="update('+id+')"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="DeleteData(\''+deleteUrl+'\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		return html;
	}	
	
	function noticeTitleFormatter(index,row,value){
		var html = '';
		var id = row.id;
		var noticeTitle = row.noticeTitle;
		html += '<a onclick="detail(\''+id+'\')" href="javascript:void(0);" title="查看详情">'+noticeTitle+'</a>';
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
	
	function add(){
		var url = rootPath+"notice/skipToAdd.action";
		top.tab("新增通知公告",url);
	}
	function update(id){
		var url = rootPath+"notice/skipToUpdate.action?id="+id;
		top.tab("修改通知公告",url);
	}
	function detail(id){
		var url = rootPath+"notice/skipToDetail.action?id="+id;
		top.tab("查看通知公告",url);
	}
	