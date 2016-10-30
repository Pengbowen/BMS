	function operateFormatter(index,row,value){
		var html = '';
		var id = row.id;
		var deleteUrl = rootPath+"blogroll/deleteBlogroll.action?id="+id;
		html += '<a class="icon icon-pencil" title="修改" href="javascript:void(0);" onclick="update('+id+')"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
		html += '<a onclick="DeleteData(\''+deleteUrl+'\',\'确认要删除吗?\')" href="javascript:void(0);" title="删除" class="icon icon-trash"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
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
		var url = rootPath+"blogroll/skipToAdd.action";
		openNow("新增友情链接",url,"460","260");
	}
	function update(id){
		var url = rootPath+"blogroll/skipToUpdate.action?id="+id;
		openNow("修改友情链接",url,"460","260");
	}