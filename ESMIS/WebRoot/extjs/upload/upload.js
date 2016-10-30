function upoladFile(formId,successFilePath,widthVal,heightVal,saveFilePath,importFun)
{
	//兼容IE9
	if ((typeof Range !== "undefined") && !Range.prototype.createContextualFragment)  
	{  
	  Range.prototype.createContextualFragment = function(html){  
	     var frag = document.createDocumentFragment(),    
	       div = document.createElement("div");  
           frag.appendChild(div);  
           div.outerHTML = html;  
            return frag;  
	    };  
	}  
	
	var fm = new Ext.FormPanel({
		 // title: "上传文件",
		 url: rootPath+"meetingModule/extjs/upload/uploadController.jsp?t="+ Math.random()+"&fileURL="+saveFilePath,
		 autoScroll:true,
		 applyTo: formId,//id 呈现
		 height: heightVal,
		 width: widthVal,
		 frame:true,//圆角和浅蓝色背景
		 fileUpload: true,
		 defaultType:'textfield',
		 labelWidth:130,
		 labelAlign:"right",
		 bodyStyle:'padding:50px 0 0 0;', //上－右－下－左
		 items:[{
			  id:'file',
			  xtype:'textfield',
			  fieldLabel:'请选择excel文件 ',
			  allowBlank:false,//false则不能为空，默认为true
			  blankText: '请浏览文件',
			  qtip:'请浏览文件',
			  inputType:'file',
			  validateOnBlur: true,
			  regex: new RegExp("(.xls|.XLS|.xlsx|.XLSX)$"),
			  regexText : "请上传正确格式的文件", 
			  name:'file'
		 }],
		 buttons: [{
			 text: '开始上传',//点击'开始上传'之后，将由这个function来处理。
			 width:60,
		     height:30,	
			 handler: function(){ 
			      if(fm.getForm().isValid()){//验证form
			     	  //显示进度条
					  Ext.MessageBox.show({ 
					       title: '正在上传文件', 
					       width:240, 
					       progress:true, 
					       closable:false, 
					       buttons:{cancel:'取消'} 
					  }); 
					  
		              fm.getForm().submit();//form提交
		             
		     	      //设置一个定时器，每100毫秒向processController发送一次ajax请求
		     	      var timer = setInterval(function(){
				           Ext.Ajax.request({
					            //以后凡是在ajax的请求的url上面都要带上日期戳，否则极有可能每次出现的数据都是一样的，这和浏览器缓存有关
					            url: rootPath+"meetingModule/extjs/upload/processController.jsp?t=" +Math.random(),
					            method: "get",
					      		success: function(response, options){
					      				var obj = Ext.util.JSON.decode(response.responseText);
					       				if(obj.success!=false){
					        				if(obj.finished){
					        						clearInterval(timer); 
					         						Ext.MessageBox.updateProgress(1, '正在分析处理文件...', 'finished');
					         						Ext.MessageBox.hide();
					         						document.getElementById(successFilePath).value=obj.newfile;
					       				     }else{
					        				        Ext.MessageBox.updateProgress(obj.percentage, obj.msg); 
					        			     }
					       		        }
					             },
					             failure: function(){
					                  clearInterval(timer);
					                  Ext.Msg.alert('错误', '发生错误了。');
					             } 
				           });
					         
			           }, 300);
			           
			       }else{
			            Ext.Msg.alert("消息","请上传\".xls\"格式的文件.");
			      }
			  } 
		 },
		 {
			text:"导入数据", 
			width:60,
	        height:30, 
	        handler:importFun
		 }]
	});
}

