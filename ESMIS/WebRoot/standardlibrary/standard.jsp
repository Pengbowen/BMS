<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>数据模板查询页面</title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/public.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script	src="${pageContext.request.contextPath}/standardlibrary/scripts/thirdSearch.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/standardlibrary/scripts/standardlibrary.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/standardlibrary/scripts/respond.min.js" type="text/javascript"></script>
 <!-- core CSS -->
  <link href="css/add_1.css" rel="stylesheet">
    <script type="text/javascript"> 
    var rootPath = "${pageContext.request.contextPath}/";
    //修改时隐藏文件上传功能
	 $(function() {
		if($("#standardId").val() !=""){
		$("#filearea").css('display','none');
	}
	$("#standardNo").keyup(function(){
		var No=$("standardNo").val();
		alert(No);
		$("#standardCategory").val(No);
		
	});
});  
function getPath(url){
$("#filePath").val(url);
}
  function addEvent(eventHandler)
    {
        var tags = document.getElementsByTagName('input');
        for(var i=0;i<tags.length;i++)
        {
            if(tags[i].getAttribute('url') == 'true')
            {
                if(tags[i].addEventListener)
                {
                    tags[i].addEventListener('keyup',eventHandler,true);
                }
                else
                {
                    tags[i].attachEvent('onkeyup',eventHandler);
                }
            }
        }
}
 function addInput(e)
    {
        var obj = e.target ? e.target : e.srcElement;
        var tags = document.getElementsByTagName('input');
        for(var i=0;i<tags.length;i++)
        {
            if(tags[i].getAttribute('url') == 'true'&&tags[i]!=obj)
            {
                tags[i].value = obj.value;
            }
        }
    }
    window.onload = function()
    {
        addEvent(addInput);
    }
    </script>
</head>
<body>
<div style="margin-left:10px;padding:16px 30px 16px 0;">
<form id="myForm">
<!-- 标准Id  -->
<input type="hidden" id="standardId" name="standardId" value="${standardId}"/>
<input type="hidden" id="filePath" name="filePath" />
    <!--标准编号-->
    <div class="group">
        <div class="row-list">
            <label class="control-label">标准编号：</label>
           <input class="easyui-textbox" prompt="请输入标准编号" id="standardNo" name="standardNo"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${standardNo}"
					type="text" inQuery="true" isrequired="true"></input>
            <p class="must">*</p>
            <p class="text-must">必填项</p>
        </div>
    </div>
    <!--标准类别-->
    <div class="group">
        <div class="row-list">
            <label class="control-label">标准类别：</label>
           <input class="easyui-combobox" prompt="请选择标准类型" inQuery="true" panelHeight="auto"  value="${standardCategory}"
						id="standardCategory" name="standardCategory"  editable="true" style="width:350px;height:28px;"  
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
					</input>
            <p class="must">*</p>
            <p class="text-must">必填项</p>
        </div>
    </div>
    <!--替代标准-->
    <div class="group">
        <div class="row-list">
            <label class="control-label">替代标准：</label>
           <input class="easyui-textbox" prompt="请输入替代标准" id="oldStandardNo" name="oldStandardNo"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${oldStandardNo}"
					type="text" inQuery="true" isrequired="true" ></input>
            <input type="checkbox"  onfocus="this.classNameName='input_selected'" 
					type="text" inQuery="true" isrequired="true" style="color:#666666;" id="effectiveState" name="effectiveState">
            <p class="delete" >作废</p>
        </div>
    </div>

    <!--标准名称(中文)-->
    <div class="group">
        <div class="row-list">
            <label class="control-label">标准名称(中文)：</label>
            <input class="easyui-textbox" prompt="请输入标准名称" id="standardName" name="standardName"
					style="width:724px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${standardName}"
					type="text" inQuery="true" isrequired="true" ></input>
			 <p class="must">*</p>
            <p class="text-must">必填项</p>
        </div>
    </div>

    <!--标准名称(英文)-->
    
    <div class="group">
        <div class="row-list">
            <label class="control-label">标准名称(英文)：</label>
            <input class="easyui-textbox" prompt="请输入标准名称" id="standardNameEN" name="standardNameEN"
					style="width:724px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${standardNameEN}"
					type="text" inQuery="true" isrequired="true" ></input>
        </div>
    </div>

    <!--主题词-->
    <div class="group">
        <div class="row-list">
            <label class="control-label">主题词：</label>
               <input class="easyui-textbox" prompt="请输入主题词" id="subjectWords" name="subjectWords"
					style="width:724px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${subjectWords}"
					type="text" inQuery="true" isrequired="true" ></input>
        </div>
    </div>


    <div class="group-box">
        <div class="group_content" style="float: left;margin-right:66px;">
            <!--采标程度-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">采标程度：</label>
                   <input class="easyui-combobox" prompt="请选择采标程度" inQuery="true" panelHeight="auto"  value="${adoptionDegree}"
						id="adoptionDegree" name="adoptionDegree" editable="false"  style="width:350px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchDegree.action'">
						</input>
                </div>
            </div>
            <!--采用国际标准-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">采用国际标准：</label>
                 <input class="easyui-textbox" prompt="请输入国际标准" id="adoptIS" name="adoptIS"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
                </div>
            </div>
            <!--批准部门-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">批准部门：</label>
                    <input class="easyui-textbox" prompt="请输入批准部门" id="approvedUnit" name="approvedUnit"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${approvedUnit}"
					type="text" inQuery="true" isrequired="true" ></input>
                    <p class="must">*</p>
                    <p class="text-must">必填项</label>
                </div>
            </div>
            <!--标准退出部门-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">标准提出部门：</label>
                    <input class="easyui-textbox" prompt="请输入标准提出部门" id="proposedUnit" name="proposedUnit"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${proposedUnit}"
					type="text" inQuery="true" isrequired="true" ></input>
                    <p class="must">*</p>
                    <p class="text-must">必填项</p>
                </div>
            </div>
            <!--标准起草部门-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">标准起草部门：</label>
                        <input class="easyui-textbox" prompt="请输入标准起草部门" id="draftedUnit" name="draftedUnit" 
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${draftedUnit}"
					type="text" inQuery="true" isrequired="true" ></input>
                </div>
            </div>
            <!--主要起草人-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">主要起草人：</label>
                       <input class="easyui-textbox" prompt="请输入主要起草人" id="majorDrafters" name="majorDrafters" 
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${majorDrafters}"
					type="text" inQuery="true" isrequired="true" ></input>
                </div>
            </div>
            <!--所属专业-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">所属专业：</label>
                   <input class="easyui-combobox" prompt="请选择专业" inQuery="true" panelHeight="auto" multiple="true" value="${applicableMajor}"
						id="applicableMajor" name="applicableMajor" editable="false"  style="width:350px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchApplicableMajor.action'">
						</input>	
                <p class="text-must">可多选</p>
                </div>
            </div>
            <!--标准分享人-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">标准分享人：</label>
                       <input class="easyui-textbox" prompt="请输入标准分享人" id="sharePeople" name="sharePeople"
					style="width:350px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"  value="${sharePeople}"
					type="text" inQuery="true" isrequired="true" ></input>
                </div>
            </div>
        </div>
        <div class="group_content" style="float: left;">
            <!--分类号-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">分类号：</label>
                       <input class="easyui-textbox" prompt="请输入分类号" id="standardClassNo" name="standardClassNo" 
					style="width:190px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"  value="${standardClassNo}"
					type="text" inQuery="true" isrequired="true" ></input>
                </div>
            </div>
            <!--门类号-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">门类号：</label>
                       <input class="easyui-textbox" prompt="请输入门类号" id="standardKindNo" name="standardKindNo"
					style="width:190px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" value="${standardKindNo}"
					type="text" inQuery="true" isrequired="true" ></input>
                </div>
            </div>
            <!--批准部门-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">批准时间：</label>
                   <input type="text" class="easyui-datebox" isrequired="true" value="${approvedDate}"
                   verifyfuc="verifyimportTime()" editable="false" id="approvedDate" name="approvedDate" inQuery="true" style="width:190px;height:27px" alias="approvedDate" /> 
                    <p class="must">*</p>
                    <p class="text-must">必填项</p>
                </div>
            </div>
            <!--标准退出部门-->
            <div class="group">
                <div class="row-list">
                    <label class="control-label">标准实施时间：</label>
                    <input type="text" class="easyui-datebox" isrequired="true" value="${effectiveDate}"
                     verifyfuc="verifyimportTime()" editable="false" id="effectiveDate" name="effectiveDate" inQuery="true" style="width:190px;height:27px" alias="effectiveDate"  /> 
                    <p class="must">*</p>
                    <p class="text-must">必填项</p>
                </div>
            </div>
            <div id="filearea" class="group" style="text-align: center;border:dashed 1px #dddddd;height:180px;">
               <span ><a onclick="upLoad()" style="width:120px;height:30px; font-size: 20px; ">上传文档</a></span>
           		                <div  style="text-align: center;margin-top:10px;font-size:12px;color:#999;line-height:18px;">
                   				 注意：可上传PDF格式文件<br>最大不超过5MB。
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div  style="background-color: #999;height: 1px;margin-top:16px;"></div>
    <div class="col-sm-11" style="text-align: right;margin-top:16px;" >
    	<input type="button" value="提交" class="btn-effect" onclick="saveMessage()" />
		<input type="button" value="关闭" class="btn-effect" onclick="top.closeSelectedTab()" /> 	
		
    </div>
</div>
<!-- 点击新增所显示页面 -->
<div id="win"></div>
</body>
</html>
