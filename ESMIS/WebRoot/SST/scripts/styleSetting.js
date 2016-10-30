var SSTId=1;
var objCount = 0;
function getList(id)
{
	SSTId=id;
	var url = rootPath+"/sst/toStyleSetting.action";	
	AjaxJson(url, true, "SSTId="+id, doResponse);
}

function doResponse(rtnJson)
{
	var ddList = rtnJson;
	var iCount=0;
	var i=0;
	var htmlContent = "";
	//生成标题
	var header = '<tr style="height:33px;">';
		header += '<td style="width:47px;">层级</td>';
		header += '<td style="width:182px;">宽度</td>';
		header += '<td style="width:200px;">高度</td>';
		header += '<td style="width:200px;">子分类高度</td>';
		header += '<td style="width:75px;">文字方向</td>';
		header += '<td style="width:75px;"><input type="button" onClick="adddom()" value="+" style="width:28px;height:22px;line-height:18px;"/></td>';
		header += '</tr>';

	//显示数据
	for(var key in ddList)
	{
		iCount++;
		htmlContent += "<tr style='height:33px;' id='tr"+iCount+"'>";
		htmlContent += "<td style='width:47px;'>"+ddList[key].level+"级<input type='hidden' name='layerNo"+iCount+"' value='" +ddList[key].level+ "'/></td>";
		htmlContent += "<td style='width:182px;'><input type='text' style='width:160px;height:22px;' name='width"+iCount+"' value='"+ddList[key].width+"' disabled/></td>";
		htmlContent += "<td style='width:210px;'><input type='text' style='width:160px;height:22px;' name='height"+iCount+"' value='"+ddList[key].height+"'/></td>";
		htmlContent += "<td style='width:210px;'><input type='text' style='width:160px;height:22px;' name='subClassifyWidth"+iCount+"' value='"+ddList[key].subTypeWidth+"'/></td>";
		htmlContent += "<td style='width:75px;'><select style='height:22px;'>";
		if(ddList[key].textDirection=="横向"){
			htmlContent += "<option selected>横向</option><option>竖向</option>";
		}else{
			htmlContent += "<option>横向</option><option selected>竖向</option>";
		}
		htmlContent += "</select></td>";
		htmlContent += "<td style='width:55px;'><input type='button' onClick='deldata("+SSTId+","+ddList[key].level+")' value='×' style='width:28px;height:22px;line-height:18px;'/></td>";
		htmlContent += "</tr>";
	}
	objCount.val(iCount);
	$("#list").html(header + htmlContent);
}



//删除
function deldata(id,level)
{
	if (confirm("您确定要删除该项吗？")) {
		//var url = $("#delAction").val();
		//var data = "&id=" + id+"&did="+did;
		//AjaxJson(url, true, data, doMinusResponse);
	}
}

function doMinusResponse(rtnJson)
{
	if(rtnJson.status == "fault")
	{
		alert(rtnJson.faultInfo);
	}
	else
	{
		alert(rtnJson.faultInfo);
		location.reload();
	}
}

//删除行
function deldom(count)
{
	$("#tr" + count).remove();
	objCount.val(parseInt(objCount.val()) - 1);
}
//添加行
function adddom(){
	var htmlContent = "";
	var iCount=0;
	var width,height,subClassifyWidth;
	iCount=parseInt(objCount.val()) + 1;
	objCount.val(iCount);
	if(SSTId==1){
		width=120;
		height=42;
		subClassifyWidth=120;
	}else if(SSTId==2){
		width=120;
		height=42;
		subClassifyWidth=120;
	}else{
		width=60;
		height=42;
		subClassifyWidth=240;
	}
	htmlContent += "<tr style='height:33px;' id='tr"+iCount+"'>";
	htmlContent += "<td style='width:47px;'>"+iCount+"级<input type='hidden' name='layerNo"+iCount+"' value='" +iCount+ "'/></td>";
	htmlContent += "<td style='width:182px;'><input value='"+width+"' type='text' style='width:160px;height:22px;' name='width"+iCount+"' disabled/></td>";
	htmlContent += "<td style='width:210px;'><input value='"+height+"'  type='text' style='width:160px;height:22px;' name='height"+iCount+"'/></td>";
	htmlContent += "<td style='width:210px;'><input value='"+subClassifyWidth+"'  type='text' style='width:160px;height:22px;' name='subClassifyWidth"+iCount+"'/></td>";
	htmlContent += "<td style='width:75px;'><select style='height:22px;'><option>横向</option><option>竖向</option></select></td>";
	htmlContent += "<td style='width:55px;'><input type='button' onClick='deldom("+ iCount +")' value='×' style='width:28px;height:22px;line-height:18px;'/></td>";
	htmlContent += "</tr>";
	$("#list").append(htmlContent);
	e.preventDefault();
}
var bflag = false;
$(document).ready(function(){
	objCount = $("#dCount");
	$("#save").click(function(){
		var url = rootPath+"/sst/saveStyleSetting.action";		
		var data = "SSTId="+SSTId+"&styleData=" + encodeURI(encodeURI(getAllArea()));
		if(bflag){
			AjaxJson(url, true, data, doAddResponse);
		}
	});
});

function getAllArea()
{
	var temp = {};
	var level,width,height=0,subTypeWidth,textDirection;
	var partrn=/^\d+$/;
	var str=",";
	var td01="";
	$("#list").find("tr").each(function(){
		level=$(this).find("input").eq(0).val();
		width=$(this).find("input").eq(1).val();
		height=$(this).find("input").eq(2).val();
		subTypeWidth=$(this).find("input").eq(3).val();
		textDirection=$(this).find("select").eq(0).val();
		if(height==""){
			alert("高度不能为空");
			bflag=false;
			return false;
		}
		if(typeof width != 'undefined'){
			if (isNaN(height))
			{
				alert("格式非法，只能是数字");
				bflag=false;
				return false;
			}
		}
		if(typeof width != 'undefined'){
			temp[level] ={"level":level,"width":width,"height":height,"subTypeWidth":subTypeWidth,"textDirection":textDirection};
			bflag=true;
		}
	});
	return JSON.stringify(temp);
}
function doAddResponse(rtnJson)
{
	if(rtnJson.result=="1"){
		alert("保存成功");
		var url="";
		if(rtnJson.message=="1"){
			url=rootPath+"sst/technicalStandard.action";
		}else if(rtnJson.message=="2"){
			url=rootPath+"sst/manageStandard.action";
		}else{
			url=rootPath+"sst/workStandard.action";
		}
		parent.$("#win").window('close', true);	
		parent.location.reload();
	}else{
		alert(rtnJson.message);
		location.reload();
	}
}
//关闭
function closeWin()
{
	parent.$("#win").window('close', true);	
}