/**
 * 创建上传窗口 公共方法
 * @param chunk 是否分割大文件
 * @param callBack 上传成功之后的回调
 */
function Uploader(chunk,callBack,filters,parentobjectNo){
	//alert(parentobjectNo);
	var amyfiltersWin = $('<div style="overflow: himyfiltersen;"/>');
	var upladoer = $('<iframe/>');
	upladoer.attr({'src':rootPath+'uploader.jsp?chunk='+chunk+'&myfilters='+encodeURIComponent(encodeURIComponent(filters))+'&parentobjectNo='+parentobjectNo,width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
	amyfiltersWin.window({
		title:"上传文件",
		height:350,
		width:550,
		minimizable:false,
		modal:true,
		collapsible:false,
		maximizable:false,
		resizable:false,
		content:upladoer,
		onClose:function(){
			var fw = GetFrameWindow(upladoer[0]);
			var files = fw.files;
			$(this).window('destroy');
			callBack.call(this,files);
		},
		onOpen:function(){
			var target = $(this);
			setTimeout(function(){
				var fw = GetFrameWindow(upladoer[0],filters);
				fw.target = target;
			},100);
		}
	});
}

/**
 * 根据iframe对象获取iframe的window对象
 * @param frame
 * @returns {Boolean}
 */
function GetFrameWindow(frame,myfilters){
	return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}
/**
 * 上传文件方法
 * false：不分割上传，ture:分割上传
 * 点击上传的页面需要有一个id为“res”的div
 * filters:自定义上传数据的类型（".doc",".xls"等）,若为空则使用默认类型
 */
function makerUpload(chunk,filters,parentobjectNo){
 //alert(parentobjectNo);
 Uploader(chunk,function(files){
	 if(files && files.length>0){
		 $("#res").text("成功上传："+files.join(","));
	 }
 },filters,parentobjectNo);
}