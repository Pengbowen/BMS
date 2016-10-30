var htmlContent="";
var sstNodeMap;
var sstLayerItemsMap;
var sstSubClassifyMap;
var layerNoCountMap;
var subClassifyCountMap;
var sstStyleMap;
var centerLocation;
var SSTId=0;
var manage;
//体系表高度
var standardHeight=150;
var x=0,y=108,defaultSubClassifyHeight=120,subClassifyHeight=0;
var defaultLayerItemWidth=60,defaultLayerItemHeight=140,layerItemWidth=0,layerItemHeight=0,startLayerItemHeight=0;
var startLocationArr = new Array();
$(document).ready(function(){
	manage=document.getElementById("manage").value;
	var url = rootPath+"sst/manageStandard.action";	
	AjaxJson(url, true, "", load);
});

function load(rtnJson)
{
	//当前屏幕宽度
	var availWidth=window.screen.availWidth-150;
	SSTId=rtnJson.SSTId;
	//结点、子结点集合
	sstNodeMap=rtnJson.sstNodeMap;
	//子项目Map
	sstLayerItemsMap=rtnJson.sstLayerItemsMap;
	//子分类Map
	sstSubClassifyMap=rtnJson.sstSubClassifyMap;
	//每层子项目数
	layerNoCountMap=rtnJson.layerNoCountMap;
	//每层是否存在子分类
	subClassifyCountMap=rtnJson.subClassifyCountMap;
	//最大个数
	var layerNoMaxCount=rtnJson.layerNoMaxCount;
	//体系表样式
	sstStyleMap=rtnJson.sstStyleMap;
	//起始位置-中心位置
	centerLocation=(layerNoMaxCount*defaultLayerItemWidth+(layerNoMaxCount-1)*10)-parseInt(defaultLayerItemWidth/2);
	centerLocation=centerLocation>availWidth?centerLocation:availWidth;
	var childNodeList;
	if(sstNodeMap==null){
		if(manage==""){
			document.getElementById("standard-bnt").style.display =  "";
		}
		htmlContent="<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"+(centerLocation/2-25)+"px;\">管理标准体系表</div>";
		$("#add-standard-box").html(htmlContent);
		return;
	}
	var sstNodeList=sstNodeMap[SSTId];
	document.getElementById("add-standard-box").style.width = centerLocation + "px";
	
	centerLocation=centerLocation/2;
	x=centerLocation;
	startLayerItemHeight=sstStyleMap[1]!=undefined?sstStyleMap[1][1]:defaultLayerItemHeight;
	
	y=y+startLayerItemHeight;
	htmlContent="<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"+(centerLocation-25)+"px;\">管理标准体系表</div>";
	htmlContent +="<div class=\"standard-box"+(sstNodeMap[SSTId].length!=0?"1":"2")+" margin-auto\" style=\"padding-left:"+centerLocation+"px;padding-right:10px;\">";
	htmlContent +="<div class=\"standard-box1-top\">"+sstLayerItemsMap[SSTId].layerItemNo+"</div>";
	htmlContent +="<div class=\"standard-box1-bottom\" style=\"height:"+startLayerItemHeight+"px;\">";
	if(sstLayerItemsMap[SSTId].standardCount>0){
		if(manage==""){
			htmlContent +="<a onclick=\"joinStandard('2','"+sstLayerItemsMap[SSTId].layerItemId+"','1')\" href=\"javascript:void(0)\">"+sstLayerItemsMap[SSTId].layerItemName+"</a>";
		}else{
			htmlContent +="<a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=2&layerItemId="+sstLayerItemsMap[SSTId].layerItemId+"\">"+sstLayerItemsMap[SSTId].layerItemName+"</a>";
		}
	}else{
		htmlContent +=sstLayerItemsMap[SSTId].layerItemName;
	}
	htmlContent +="</div>";
	htmlContent +="</div>";
	
	var temp=0;
	if(sstNodeMap[SSTId].length % 2==0){
		temp=centerLocation-((sstNodeMap[SSTId].length/2-1)*(defaultLayerItemWidth+10))-defaultLayerItemWidth/2+55;
	}else{
		temp=centerLocation-((parseInt(sstNodeMap[SSTId].length/2)-1)*(defaultLayerItemWidth+10))-defaultLayerItemWidth/2+20;
	}
	
	htmlContent +="<div style=\"margin-left:"+temp+"px;width:"+((layerNoMaxCount-1)*(defaultLayerItemWidth+10))+"px;border-bottom:1px solid #dadada;content:''\"></div>";
	write(sstNodeList);
	document.getElementById("add-standard-box").style.height =  standardHeight+"px";
	if(manage==""){
		document.getElementById("standard-bnt").style.display =  "";
	}
	$("#add-standard-box").html(htmlContent);
}
function write(childNodeList){
	for(var key=0;key<childNodeList.length;key++){
		//计算每层开始位置
		if(startLocationArr[sstLayerItemsMap[childNodeList[key]].layerNo-1] == undefined){
			var count=0;
			if(sstLayerItemsMap[childNodeList[key]].layerNo==2){
				count=layerNoCountMap[sstLayerItemsMap[childNodeList[key]].layerNo];
				if(count%2==0){
					x=x-((count/2-1)*(defaultLayerItemWidth+10))-defaultLayerItemWidth/2+35;
				}else{
					x=x-((parseInt(count/2)-1)*(defaultLayerItemWidth+10))-defaultLayerItemWidth/2;
				}
			}else{
				count=sstLayerItemsMap[sstLayerItemsMap[childNodeList[key]].belongItemId].subItemCount;
				if(count%2==0){
					x=x-(((count/2)-1)*(defaultLayerItemWidth+10)+defaultLayerItemWidth/2);
				}else{
					x=x-((parseInt(count/2))*(defaultLayerItemWidth+10));
				}
			}
			startLocationArr[sstLayerItemsMap[childNodeList[key]].layerNo-1]=true;
			standardHeight=y;
			var dempLayerItemHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][1]:defaultLayerItemHeight;
			standardHeight=standardHeight+dempLayerItemHeight+78;
			if(subClassifyCountMap!=null && subClassifyCountMap[sstLayerItemsMap[childNodeList[key]].layerNo]){
				standardHeight=standardHeight+(sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][2]:defaultSubClassifyHeight);
			}
		}
		//是否有子分类
		var subItemCount=sstNodeMap[childNodeList[key]]!=""?true:false;
		htmlContent +="<div class=\"standard-box"+(subItemCount?"3":"3")+" margin-auto\" style=\"top:"+(sstLayerItemsMap[childNodeList[key]].layerNo=="2"?y+3:y)+"px;position:absolute;left:"+x+"px;padding-right:10px;\">";
		htmlContent +="<div class=\"standard-box1-top\">"+sstLayerItemsMap[childNodeList[key]].layerItemNo+"</div>";
		
		layerItemHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][1]:defaultLayerItemHeight;
		htmlContent +="<div class=\"standard-box3-bottom\" style=\"height:"+layerItemHeight+"px;\">";
		if(sstLayerItemsMap[childNodeList[key]].standardCount>0){
			if(manage==""){
				htmlContent +="<a onclick=\"joinStandard('2','"+sstLayerItemsMap[childNodeList[key]].layerItemId+"','1')\" href=\"javascript:void(0)\">"+sstLayerItemsMap[childNodeList[key]].layerItemName+"</a>";
			}else{
				htmlContent +="<a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=2&layerItemId="+sstLayerItemsMap[childNodeList[key]].layerItemId+"\">"+sstLayerItemsMap[childNodeList[key]].layerItemName+"</a>";
			}
		}else{
			htmlContent +=sstLayerItemsMap[childNodeList[key]].layerItemName;
		}
		htmlContent +="</div>";
		
		subClassifyHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][2]:defaultSubClassifyHeight;
		if(sstSubClassifyMap!=null && sstSubClassifyMap[sstLayerItemsMap[childNodeList[key]].layerItemId]!= undefined){
			htmlContent +="<div class=\"standard-box2\">";
			htmlContent +="<div class=\"standard-box1-line\"></div>";
			htmlContent +="<div class=\"standard-box1-top\">"+sstLayerItemsMap[childNodeList[key]].layerItemNo+"/1-"+sstSubClassifyMap[sstLayerItemsMap[childNodeList[key]].layerItemId].length+"</div>";
			htmlContent +="<ul class=\"standard-box2-bottom\" style=\"height:"+subClassifyHeight+"px;\">";
			var subClassifyList=sstSubClassifyMap[sstLayerItemsMap[childNodeList[key]].layerItemId];
			for(var i=0;i<subClassifyList.length;i++){
				if(subClassifyList[i].standardCount>0){
					if(manage==""){
						htmlContent +="<li><a onclick=\"joinStandard('2','"+subClassifyList[i].subClassId+"','2')\" href=\"javascript:void(0)\">"+(i+1)+"."+subClassifyList[i].subClassName+"</a></li>";
					}else{
						htmlContent +="<li><a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=2&subClassId="+subClassifyList[i].subClassId+"\">"+subClassifyList[i].subClassName+"</a></li>";
					}
				}else{
					htmlContent +="<li>"+(i+1)+"."+subClassifyList[i].subClassName+"</li>";
				}
			}
			htmlContent +="</ul>";
			htmlContent +="</div>";
		}else{
			if(subItemCount && subClassifyCountMap!=null && subClassifyCountMap[sstLayerItemsMap[childNodeList[key]].layerNo]){
				htmlContent +="<div style=\"margin-left:"+(parseInt(defaultLayerItemWidth/2))+"px;display:block;width:0px;height:"+(subClassifyHeight+30)+"px;border-right:1px solid #dadada;\"></div>";
			}
		}
		htmlContent +="</div>";
		subClassifyHeight=subClassifyHeight+30;
		if(sstSubClassifyMap!=null && subClassifyCountMap!=null && subClassifyCountMap[sstLayerItemsMap[childNodeList[key]].layerNo]==true){
			y=y+layerItemHeight+78+subClassifyHeight;
		}else{
			y=y+layerItemHeight+55;
		}
		if(sstNodeMap[childNodeList[key]]!=""){
			write(sstNodeMap[childNodeList[key]]);
		}else{
			x=x+defaultLayerItemWidth+10;
			y=108+startLayerItemHeight;
		}
	}
}