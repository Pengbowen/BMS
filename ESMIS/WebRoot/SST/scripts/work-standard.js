var htmlContent="";
var sstNodeMap;
var sstLayerItemsMap;
var sstSubClassifyMap;
var layerNoCountMap;
var subClassifyCountMap;
var sstStyleMap;
var centerLocation=0;
var SSTId=0;
var manage;
var subClassifyCount=0;
var subClassifyCountWith=0;
var childNodeString="";//同父同母层子项目集合
//体系表高度
var standardHeight=150;
var x=0,y=108,defaultSubClassifyHeight=120,subClassifyHeight=0;
var tag=false;
var defaultLayerItemWidth=120,defaultLayerItemHeight=42,layerItemWidth=0,layerItemHeight=0,startLayerItemHeight=0,startLayerItemWidth=0,startLayerItemWidthCount=0;
var startLocationArr = new Array();
var startLocationForThiredLayerItem = new Array();//第三层子项目位置
var sumWidth=0;
var indexCount=1;
$(document).ready(function(){
	manage=document.getElementById("manage").value;
	var url = rootPath+"sst/workStandard.action";	
	AjaxJson(url, true, "", load);
});

function load(rtnJson)
{
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
	//第二层子项目最大子项目数
	subClassifyCount=rtnJson.subClassifyCount;
	//最大个数
	var layerNoMaxCount=rtnJson.layerNoMaxCount;
	//体系表样式
	sstStyleMap=rtnJson.sstStyleMap;
	//起始位置-中心位置,最小宽度为1024px
	if(sstNodeMap==null){
		if(manage==""){
			document.getElementById("standard-bnt").style.display =  "";
		}
		htmlContent="<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"+(1024/2-25)+"px;\">工作标准体系表</div>";
		$("#add-standard-box").html(htmlContent);
		return;
	}
	var childNodeList=sstNodeMap[SSTId];
	for(var i=0;i<childNodeList.length;i++){
		var subClassifyWidth=0;
		
		if(sstNodeMap[childNodeList[i]].length>0){
			subClassifyWidth=(sstNodeMap[childNodeList[i]].length*60+(sstNodeMap[childNodeList[i]].length-1)*10);
		}else{
			subClassifyWidth=120;
		}
		subClassifyWidth=subClassifyWidth>120?subClassifyWidth:120;
		
		if(i==0 || i+1==childNodeList.length){
			sumWidth+=(subClassifyWidth>120?subClassifyWidth:120)/2;
		}else{
			sumWidth+=subClassifyWidth>120?subClassifyWidth:120;
		}
		centerLocation+=subClassifyWidth>120?subClassifyWidth:120;
		sumWidth+=20;
		centerLocation+=20;
	}
	sumWidth-=20;
	centerLocation=centerLocation-20+30;
	var sstNodeList=sstNodeMap[SSTId];
	
	centerLocation=centerLocation/2;
	x=centerLocation;
	startLayerItemHeight=sstStyleMap[1]!=undefined?sstStyleMap[1][1]:defaultLayerItemHeight;
	y=y+startLayerItemHeight;
	htmlContent="<div style=\"line-height:48px;font-size:24px;color:#333333;padding-left:"+(centerLocation-80)+"px;\">工作标准体系表</div>";
	htmlContent +="<div class=\"standard-box"+(sstNodeMap[SSTId].length!=0?"1":"2")+" margin-auto\" style=\"padding-left:"+(centerLocation-60)+"px;padding-right:10px;\">";
	htmlContent +="<div class=\"standard-box1-top\">"+sstLayerItemsMap[SSTId].layerItemNo+"</div>";
	htmlContent +="<div class=\"standard-box1-bottom\" style=\"height:"+startLayerItemHeight+"px;\">";
	if(sstLayerItemsMap[SSTId].standardCount>0){
		if(manage==""){
			htmlContent +="<a onclick=\"joinStandard('layerItemId','"+sstLayerItemsMap[SSTId].layerItemId+"')\" href=\"javascript:void(0)\">"+sstLayerItemsMap[SSTId].layerItemName+"</a>";
		}else{
			htmlContent +="<a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId="+SSTId+"&layerItemId="+sstLayerItemsMap[SSTId].layerItemId+"\">"+sstLayerItemsMap[SSTId].layerItemName+"</a>";
		}
	}else{
		htmlContent +=sstLayerItemsMap[SSTId].layerItemName;
	}
	htmlContent +="</div>";
	htmlContent +="</div>";
	
	write(sstNodeList);
	document.getElementById("add-standard-box").style.height =  standardHeight+"px";
	if(manage==""){
		document.getElementById("standard-bnt").style.display =  "";
	}
	console.log(htmlContent);
	$("#add-standard-box").html(htmlContent);
}
function write(childNodeList){
	for(var key=0;key<childNodeList.length;key++){
		var layerNo=sstLayerItemsMap[childNodeList[key]].layerNo;
		//计算每层开始位置
		if(startLocationArr[sstLayerItemsMap[childNodeList[key]].layerNo-1] == undefined){
			if(sstLayerItemsMap[childNodeList[key]].layerNo!=1){
				tag=true;
				if(startLocationForThiredLayerItem[childNodeList[key]]== undefined && layerNo==3){
					if(sstNodeMap[sstLayerItemsMap[childNodeList[key]].belongItemId]!=""){
						//第二层子项目是否存在
						startLocationForThiredLayerItem[sstLayerItemsMap[childNodeList[key]].belongItemId]=false;
					}
				}
			}
			var subClassifyWidth=(sstNodeMap[childNodeList[key]].length*60+(sstNodeMap[childNodeList[key]].length-1)*10);
			subClassifyWidth=subClassifyWidth>120?subClassifyWidth:120;
			
			var count=sstNodeMap[SSTId].length;
			if(sstLayerItemsMap[childNodeList[key]].layerNo==2){
				//计算当前宽度
				var subClassifyWidth=(sstNodeMap[childNodeList[key]].length*60+(sstNodeMap[childNodeList[key]].length-1)*10);
				subClassifyWidth=(subClassifyWidth>120?parseInt(subClassifyWidth/2):120)-45;
				//第二层最新宽度
				x=subClassifyWidth;
				startLayerItemWidth=x;
			}else{
				count=sstLayerItemsMap[sstLayerItemsMap[childNodeList[key]].belongItemId].subItemCount;
				var subClassifyWidth=(count*60+(count-1)*10);
				x=x-(count==1?(30*-1):(parseInt(subClassifyWidth/2)-60));
			}
			startLocationArr[sstLayerItemsMap[childNodeList[key]].layerNo-1]=true;
			standardHeight=y;
			var dempLayerItemHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][1]:defaultLayerItemHeight;
			standardHeight=standardHeight+dempLayerItemHeight+78;
			if(subClassifyCountMap!=null && subClassifyCountMap[sstLayerItemsMap[childNodeList[key]].layerNo]){
				standardHeight=standardHeight+(sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][2]:defaultSubClassifyHeight);
			}
		}else{
			if(layerNo==2){
				++startLayerItemWidthCount;
				
				//计算左兄弟宽度
				var leftSubClassifyWidth=(sstNodeMap[childNodeList[key-1]].length*60+(sstNodeMap[childNodeList[key-1]].length-1)*10);
				leftSubClassifyWidth=leftSubClassifyWidth>120?leftSubClassifyWidth:120;
				
				//计算当前宽度
				var subClassifyWidth=(sstNodeMap[childNodeList[key]].length*60+(sstNodeMap[childNodeList[key]].length-1)*10);
				subClassifyWidth=subClassifyWidth>120?(subClassifyWidth-120)/2+120:120;
				
				subClassifyCountWith+=leftSubClassifyWidth>120?(leftSubClassifyWidth-120)/2+subClassifyWidth:subClassifyWidth;
				
				//第二层最新宽度
				x=startLayerItemWidth+subClassifyCountWith+startLayerItemWidthCount*20;
				y=108+startLayerItemHeight;
				if(startLocationForThiredLayerItem[childNodeList[key]]== undefined){
					if(sstNodeMap[childNodeList[key]]!=""){
						//第二层子项目是否存在
						startLocationForThiredLayerItem[childNodeList[key]]=true;
					}
				}
			}else{
				if(startLocationForThiredLayerItem[sstLayerItemsMap[childNodeList[key]].belongItemId]){
					tag=true;
					//计算第三层子项目开始位置
					count=sstLayerItemsMap[sstLayerItemsMap[childNodeList[key]].belongItemId].subItemCount;
					var subClassifyWidth=(count*60+(count-1)*10);
					x=x-(count==1?(30*-1):(parseInt(subClassifyWidth/2)-60));
					startLocationForThiredLayerItem[sstLayerItemsMap[childNodeList[key]].belongItemId]=false;
				}
			}
		}
		++indexCount;//z-index计数器
		//是否有子分类
		var subItemCount=sstNodeMap[childNodeList[key]]!=""?true:false;
		
		var standardBoxStyle="4";
		if(layerNo==3){
			standardBoxStyle="3";
		}else{
			if(!subItemCount){
				standardBoxStyle="6";
			}
		}
		htmlContent +="<div class=\"standard-box"+standardBoxStyle+" margin-auto\" style=\"top:"+(sstLayerItemsMap[childNodeList[key]].layerNo=="2"?y+3:y)+"px;position:absolute;padding-left:"+x+"px;padding-right:10px;"+(sstLayerItemsMap[childNodeList[key]].standardCount>0?"z-index:"+(10000-indexCount)+";":"")+"\">";
		htmlContent +="<div class=\"standard-box1-top\" style=\"position:relative;\">"+sstLayerItemsMap[childNodeList[key]].layerItemNo;
		
		if(tag){
			var width=0;
			var count=sstNodeMap[sstLayerItemsMap[childNodeList[key]].belongItemId].length;
			var demp=0;
			if(layerNo=="2"){
				width=sumWidth+1;
				demp=60;
			}else{
				width=(count-1)*(10+60)+1;
				demp=30;
			}
			htmlContent +="<div style=\"position:absolute;top:-25px;left:"+demp+"px;height:1px;background-color:#ddd;width:"+width+"px\"></div>";
			tag=false;
		}
		htmlContent +="</div>";
		
		
		layerItemHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][1]:defaultLayerItemHeight;
		htmlContent +="<div class=\"standard-box"+(standardBoxStyle=="3"?"3":"1")+"-bottom\" style=\"height:"+layerItemHeight+"px;\">";
		if(sstLayerItemsMap[childNodeList[key]].standardCount>0){
			if(manage==""){
				htmlContent +="<a onclick=\"joinStandard('layerItemId','"+childNodeList[key]+"')\" href=\"javascript:void(0)\">"+sstLayerItemsMap[childNodeList[key]].layerItemName+"</a>";
			}else{
				htmlContent +="<a target=\"_blank\" href=\""+rootPath+"standardDetail/toStandardList.action?SSTId="+SSTId+"&layerItemId="+childNodeList[key]+"\">"+sstLayerItemsMap[childNodeList[key]].layerItemName+"</a>";
			}
		}else{
			htmlContent +=sstLayerItemsMap[childNodeList[key]].layerItemName;
		}
		htmlContent +="</div>";
		subClassifyHeight=sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo]!=undefined?sstStyleMap[sstLayerItemsMap[childNodeList[key]].layerNo][2]:defaultSubClassifyHeight;
		
		htmlContent +="</div>";
		subClassifyHeight=subClassifyHeight+30;

		if(layerNo==3 && childNodeString.indexOf(childNodeList[key])>=0){
			//preY=y;
		}else{
			y=y+layerItemHeight+80;
		}
		
		if(sstNodeMap[childNodeList[key]]!=""){
			if(layerNo==2){
				childNodeString=sstNodeMap[childNodeList[key]];
			}
			write(sstNodeMap[childNodeList[key]]);
		}else{
			//x:二次迭代控制间距
			if(layerNo==3){
				x=x+70;
			}
		}
	}
	
}