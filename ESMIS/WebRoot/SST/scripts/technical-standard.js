var manage;
$(document).ready(function() {
	manage = document.getElementById("manage").value;
	var url = rootPath + "sst/technicalStandard.action";
	AjaxJson(url, true, "", load);
});

var htmlContent = "";
//子项目集合
var sstLayerItemsList;
//子分类集合
var sstSubClassifyMap;
//每层子项目个数 
var layerNoCountMap;
//最大子项目数
var layerNoMaxCount;
//每层是否有子分类 
var subClassifyCountMap;
//每层体系表样式 
var sstStyleMap;
//中心位置
var centerLocation;
//当前X坐标值
var currentXLocation;
//当前y坐标值
var currentYLocation=55;
//框图默认高度
var layerItemHeight=42;
//框图默认宽度
var layerItemWidth=120;
///子分类高度
var subClassifyHeight=120;
//每层子项目计数器
var layerNoCount=0;
//框图模块间隔，默认10px
var space=10;
//框图模块样式
var standardBoxStyle;
//当前层级
var currentArr = new Array();
//两层之前最大子项目数
var maxLayerCount=0;
//当前层级个数
var currentLayerIndex=0;

function load(rtnJson) {
	//当前屏幕宽度
	var availWidth=window.screen.availWidth-150;
	sstLayerItemsList=rtnJson.sstLayerItemsList;
	sstSubClassifyMap=rtnJson.sstSubClassifyMap;
	layerNoCountMap=rtnJson.layerNoCountMap;
	layerNoMaxCount=rtnJson.layerNoMaxCount;
	subClassifyCountMap=rtnJson.subClassifyCountMap;
	sstStyleMap=rtnJson.sstStyleMap;
	centerLocation=(layerNoMaxCount*layerItemWidth+(layerNoMaxCount-1)*space)-layerItemWidth/2;
	centerLocation=centerLocation>availWidth?centerLocation:availWidth;
	centerLocation=centerLocation/2-110;
	currentXLocation=centerLocation;
	if (sstLayerItemsList == null) {
		htmlContent = "<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"
				+ (centerLocation +95) + "px;\">技术标准体系表</div>";
		$("#add-standard-box").html(htmlContent);
		return;
	}
	htmlContent ="<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"+(centerLocation+95)+"px;\">技术标准体系表</div>";
	var existSubClassify;
	for(var i=0;i<sstLayerItemsList.length;i++){
		layerNoCount++;
		//是否有子分类
		if(currentArr[sstLayerItemsList[i].layerNo] == undefined){
			++currentLayerIndex;
			//计算两层间最大子项目数
			if(maxLayerCount<layerNoCountMap[sstLayerItemsList[i].layerNo]){
				maxLayerCount=layerNoCountMap[sstLayerItemsList[i].layerNo];
			}
			existSubClassify=subClassifyCountMap!=null && subClassifyCountMap[sstLayerItemsList[i].layerNo]!= undefined;
			currentArr[sstLayerItemsList[i].layerNo]=layerNoCountMap[sstLayerItemsList[i].layerNo];
			subClassifyHeight=sstStyleMap[sstLayerItemsList[i].layerNo]!=undefined?sstStyleMap[sstLayerItemsList[i].layerNo][2]:subClassifyHeight;
			//获取每层第一个框图位置
			getLocation(sstLayerItemsList[i].layerNo,layerNoCountMap[sstLayerItemsList[i].layerNo],existSubClassify);
			
			var layerItemCount=layerNoCountMap[sstLayerItemsList[i].layerNo];
			htmlContent+="<div>";
		}
		var subClassifyCount=true;
		if(existSubClassify){
			subClassifyCount=sstSubClassifyMap!=null && sstSubClassifyMap[sstLayerItemsList[i].layerItemId]!= undefined;
		}
		
		var dempLayerItemHeight=0;
		if(subClassifyCount){
			if(sstSubClassifyMap[sstLayerItemsList[i].layerItemId] == undefined){
				dempLayerItemHeight=layerItemHeight;
			}else{
				if(sstLayerItemsList[i].standardCount>0){
					dempLayerItemHeight=layerItemHeight;
				}else{
					dempLayerItemHeight=layerItemHeight+subClassifyHeight+53;
				}
			}
		}else{
			if(sstLayerItemsList[i].standardCount>0){
				dempLayerItemHeight=layerItemHeight;
			}else{
				dempLayerItemHeight=layerItemHeight+subClassifyHeight+53;
			}
		}

		getStandardBoxStyle(sstLayerItemsList[i].layerNo);
		htmlContent +="<div class=\"standard-box"+standardBoxStyle+" margin-auto\" style=\"top:"+currentYLocation+"px;position:absolute;left:"+(currentXLocation+layerNoCount*layerItemWidth+(layerNoCount-1)*space)+"px;padding-right:10px;\">";
		htmlContent +="<div class=\"standard-box1-top\" style=\"position:relative;\">"+sstLayerItemsList[i].layerItemNo;
		if(layerNoCount==1 && currentLayerIndex!=1){
			if(maxLayerCount!=layerNoCountMap[sstLayerItemsList[i].layerNo]){
				htmlContent +="<div style=\"position:absolute;top:-25px;left:"+((maxLayerCount-1)*(layerItemWidth+10)/2*(-1)+(layerNoCountMap[sstLayerItemsList[i].layerNo]-1)*(layerItemWidth+10)/2+60)+"px;height:1px;background-color:#ddd;width:"+((maxLayerCount-1)*(layerItemWidth+10))+"px\"></div>";
			}else{
				htmlContent +="<div style=\"position:absolute;top:-25px;left:60px;height:1px;background-color:#ddd;width:"+((maxLayerCount-1)*(layerItemWidth+10))+"px\"></div>";
			}
		}
		htmlContent +="</div>";
		
		htmlContent +="<div class=\"standard-box1-bottom\" style=\"height:"+dempLayerItemHeight+"px;\">";
		if(sstLayerItemsList[i].standardCount>0){
			if(manage==""){
				htmlContent +="<a onclick=\"joinStandard('1','"+sstLayerItemsList[i].layerItemId+"','1')\" href=\"javascript:void(0)\">"+sstLayerItemsList[i].layerItemName+"</a>";
			}else{
				htmlContent +="<a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=1&layerItemId="+sstLayerItemsList[i].layerItemId+"\">"+sstLayerItemsList[i].layerItemName+"</a>";
			}
			htmlContent +="</div>";
		}else{
			//子项目不包含标准时样式
			htmlContent +=sstLayerItemsList[i].layerItemName;
			if(existSubClassify && subClassifyCount){
				htmlContent +="<ul style=\"line-height:20px;text-align:left;padding:0 5px;height:"+subClassifyHeight+"px;\">";
				var subClassifyList=sstSubClassifyMap[sstLayerItemsList[i].layerItemId];
				for(var j=0;j<subClassifyList.length;j++){
					if(subClassifyList[j].standardCount>0){
						if(manage==""){
							htmlContent +="<li><a onclick=\"joinStandard('1','"+subClassifyList[j].subClassId+"','2')\" href=\"javascript:void(0)\">"+(j+1)+"."+subClassifyList[j].subClassName+"</a></li>";
						}else{
							htmlContent +="<li><a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=1&subClassId="+subClassifyList[j].subClassId+"\">"+subClassifyList[j].subClassName+"</a></li>";
						}
					}else{
						htmlContent +="<li>"+(j+1)+"."+subClassifyList[j].subClassName+"</li>";
					}
				}
				htmlContent +="</ul>";
			}
			htmlContent +="</div>";
		}
		
		//子分类显示
		if(existSubClassify && subClassifyCount && sstLayerItemsList[i].standardCount>0){
			//子项目包含标准样式
			htmlContent +="<div class=\"standard-box2\">";
			htmlContent +="<div class=\"standard-box1-line\"></div>";
			htmlContent +="<div class=\"standard-box1-top\">"+sstLayerItemsList[i].layerItemNo+"/1-"+sstSubClassifyMap[sstLayerItemsList[i].layerItemId].length+"</div>";
			htmlContent +="<ul class=\"standard-box2-bottom\" style=\"height:"+subClassifyHeight+"px;\">";
			var subClassifyList=sstSubClassifyMap[sstLayerItemsList[i].layerItemId];
			for(var j=0;j<subClassifyList.length;j++){
				if(subClassifyList[j].standardCount>0){
					if(manage==""){
						htmlContent +="<li><a onclick=\"joinStandard('1','"+sstLayerItemsList[i].layerItemId+"','2')\" href=\"javascript:void(0)\">"+(j+1)+"."+subClassifyList[j].subClassName+"</a></li>";
					}else{
						htmlContent +="<li><a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId=1&subClassId="+subClassifyList[j].subClassId+"\">"+subClassifyList[j].subClassName+"</a></li>";
					}
				}else{
					htmlContent +="<li>"+(j+1)+"."+subClassifyList[j].subClassName+"</li>";
				}
			}
			htmlContent +="</ul>";
			htmlContent +="</div>";
		}
		htmlContent +="</div>";
		if(currentArr[sstLayerItemsList[i].layerNo]==layerNoCount){
			htmlContent +="</div>";
			maxLayerCount=layerNoCount;
			//清空层计数器
			layerNoCount=0;
		}
	}
	//console.log(htmlContent);
	//计算最后一层的高度
	var lastLayerHight=0;
	var lastLayerNO=currentArr.length-1;
	layerItemHeight=sstStyleMap[lastLayerNO]!=undefined?sstStyleMap[lastLayerNO][1]:layerItemHeight;
	currentYLocation+=layerItemHeight;
	if(subClassifyCountMap[(currentArr.length-1)]){
		currentYLocation+=(sstStyleMap[layerItemHeight]!=undefined?sstStyleMap[layerItemHeight][2]:subClassifyHeight)+100;
	}else{
		currentYLocation+=60;
	}
	document.getElementById("add-standard-box").style.height =  (currentYLocation)+"px";
	if(manage==""){
		document.getElementById("standard-bnt").style.display =  "";
	}
	$("#add-standard-box").html(htmlContent);
}
//计算currentXLocation 
function getLocation(layerNo,sumCount,existSubClassify) {
	if(sumCount%2==0){
		currentXLocation=centerLocation-((sumCount/2-1)*(layerItemWidth+space)+(layerItemWidth/2))+space/2;
	}else{
		currentXLocation=centerLocation-((parseInt(sumCount/2))*(layerItemWidth+space))+space;
	}
	//框图子项目高度
	layerItemHeight=sstStyleMap[layerNo]!=undefined?sstStyleMap[layerNo][1]:layerItemHeight;
	//上层是否存在子分类
	if(subClassifyCountMap!=null && subClassifyCountMap[layerNo-1]){
		//上层高度
		currentYLocation=currentYLocation+layerItemHeight;
		//子分类高度
		currentYLocation=currentYLocation+(sstStyleMap[layerNo-1]!=undefined?sstStyleMap[layerNo-1][2]:subClassifyHeight);
		if(!existSubClassify){
			currentYLocation=currentYLocation+130;
		}else{
			currentYLocation=currentYLocation+130;
		}
	}else{
		currentYLocation=currentYLocation+layerItemHeight+77;
	}
	if(layerNo=="1"){
		currentYLocation=currentYLocation-110;
	}else if(layerNo=="2"){
		currentYLocation=currentYLocation-23;
	}
}
//获取框图class样式
function getStandardBoxStyle(layerNo){
	if(layerNo=="1"){
		standardBoxStyle="1";
	}else{
		if((Object.getOwnPropertyNames(layerNoCountMap).length!=currentLayerIndex)){
			standardBoxStyle="4";
		}else{
			standardBoxStyle="6";
		}
	}
}