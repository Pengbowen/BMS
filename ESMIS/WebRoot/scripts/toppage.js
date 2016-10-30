	//修改系统的上下框架
	function HideTop(set)
	{
		
		if ($("#hidebtn").attr("class") == "linksPic")
		{
			$("#logo").css("display","none");
			$("#usershow").css("display","none");
			$("#top_logo").removeClass("topLogo").addClass("top_logo_small");
			$("#hidebtn").removeClass("linksPic").addClass("linksPic1");
			window.parent.document.getElementById("frameMain").rows="42,6,*,0";
			window.parent.parent.document.getElementById("leftButtonDiv_LySoftPopuDiv").style.top="52px";
			if(set){
				setCookie('tophide','hide');
			}
		}
		else
		{
			$("#logo").css("display","");
			$("#usershow").css("display","");
			$("#top_logo").removeClass("top_logo_small").addClass("topLogo");
			$("#hidebtn").removeClass("linksPic1").addClass("linksPic");
			window.parent.document.getElementById("frameMain").rows="90,6,*,30";
			window.parent.parent.document.getElementById("leftButtonDiv_LySoftPopuDiv").style.top="100px";
			if(set){
				setCookie('tophide','show');
			}
		}
	}
	
	$("#topnav li").mouseover(function(){	
	  	if(this.className!="Iselected"){
	  		this.className="selected";
	  		if(this.id=="li_home"){$("#imghome").attr("src","images/home02.png");}
	  	}  	
	});
		
	$("#topnav li").mouseleave(function(){
		if(this.className!="Iselected"){
			this.className="unselected";
			if(this.id=="li_home"){$("#imghome").attr("src","images/home01.png");}
		}	
		
	});
	
	$("#topnav li").click(function(){
		showTopNav(this.id,true);
	}); 
	
	function showTopNav(obj,bGoto)
	{	
		var menuIco = $("#img" + obj.substring(2)); 
		menuIco.attr("src","images/top_ico05.png");
			
		$("#"+obj).attr("class","Iselected");
	
		if(bGoto){    
			var frm = window.parent.frames;
			for (i=0; i< frm.length;i++)
			{
				if (frm[i].name == "middle"){
					frm[i].location.replace($("#"+obj.substring(2)).attr("href"));
				}
			}		
		}	
		var parent = $("#topnav");
		var nodeList = $("#topnav li");
		var num = $("#topnav li").length;
		for(var i = 0; i < num; i ++)
		{
			var node = nodeList[i];
			if(node != null){			
				if( obj != node.id){
					$("#"+node.id).attr("class","unselected");
												
					var menuIco = $("#img" + node.id.substring(2)); 
					menuIco.attr("src","images/top_ico04.png");
				}
			}
		}
		
		if(obj=="li_home"){
			$("#imghome").attr("src","images/home02.png");
		}else{			
			$("#imghome").attr("src","images/home01.png");
		}		
	}

	if(screen.width<=1024){
		$("#usershow").removeClass("user");
	}else{
		$("#usershow").addClass("user");
	}	
	
	