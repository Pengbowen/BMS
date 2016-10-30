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
<script src="${pageContext.request.contextPath}/scripts/commonExport.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script	src="${pageContext.request.contextPath}/standardlibrary/scripts/thirdSearch.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/standardlibrary/scripts/standardlibrary.js" type="text/javascript"></script>
<script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	var LoadDataUrl = rootPath + 'standardlibrary/searchStandard.action';
	var gridviewId = 'ViewID', pager;
	var currentPage = 1, linesOfPage = 20;
	var dataItemObject;
	$(function() {
		$('#' + gridviewId).datagrid({
			fit : true, //自适应大小
			nowrap : true,//当数据长度超出列宽时将会自动截取
			singleSelect : true,//是否只允许选择一行
			autoRowHeight : false,//行高自适应
			striped : true,//交替显示行背景
			idField : 'rowid',//唯一列
			loadMsg : '', //数据加载中，等待 展示的文字*			
			columns : [[//设置gridview的列信息*
			{field : 'No',title : '序号',width : '40',align : 'center',formatter : showClassNo},
			{field : 'standardId',title : '标准Id',width : '250',align : 'center',hidden:true},
			{field : 'standardNo',title : '标准编号',width : '200',align : 'left',formatter : detailView},
			{field : 'standardName',title : '标准名称',width : '350',align : 'left',formatter : detailView},
			{field : 'oldStandardNo',title : '代替标准',width : '120',align : 'left'},
			{field : 'standardCategoryName',title : '标准类别',width : '120',align : 'left'},
			{field : 'effectiveState',title : '有效标记',width : '120',align : 'center',formatter :showState},
			{field : 'dataSource',title : '数据来源',width : '120',align : 'center',formatter :showDataSource},
			{field : 'updated',title : '操作',width : '120',align : 'center',formatter : formateOperateCell}
            ]],
             toolbar:[{
				text:'导出当前页',
				width : '100',
				iconCls:'icon icon-download-alt',
				handler:function(){
					exportThisPage('standardLibrary');
				}
			},{
				text:'导出所有数据',
				width : '120',
				iconCls:'icon icon-download-alt',
				handler:function(){
					exportAllData(LoadDataUrl,getParameter(),'standardLibrary');
				}
			}],
			pagination : true
			//是否分页*
		});
		
		//获取gridView的分页对象
		pager = $('#' + gridviewId).datagrid('getPager');
			$(pager).pagination({
				pageSize : linesOfPage,
				pageList : [10,15,20,30,50,80,100],
				onSelectPage : function(pageNumber, pageSize) {
					currentPage = pageNumber;
					linesOfPage = pageSize;
					loadGridViewData();
				}
		});		
	});	
   function verifyData(){
		return true;
   }
</script>

</head>
<body>
<div class="easyui-layout" fit="true">	
		<div data-options="fit:true,border:false" id="searchData" class="easyui-accordion" style="margin-top:1px">
			<div title="查询条件" id='RAP_Search' data-options='selected:true' style="border:1px solid #ddd;">
				<ul class="s-search" style="width:40%;float:left;">
					<div class="s-search-title">标准档案信息</div>
				<li><span>标&nbsp;&nbsp;准&nbsp;&nbsp;编&nbsp;&nbsp;号：&nbsp;</span>
				<em> <input class="easyui-textbox" prompt="请输入标准编号" id="standardNo" name="standardNo"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				 <li><span>标&nbsp;&nbsp;准&nbsp;&nbsp;名&nbsp;&nbsp;称：&nbsp;</span>
				<em> <input class="easyui-textbox" prompt="请输入标准名称" id="standardName" name="standardName"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				<li><span>替&nbsp;&nbsp;代&nbsp;&nbsp;标&nbsp;&nbsp;准：&nbsp;</span>
				<em> <input class="easyui-textbox" prompt="请输入替代标准" id="oldStandardNo" name="oldStandardNo"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" 
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				<li><span>标&nbsp;&nbsp;准&nbsp;&nbsp;类&nbsp;&nbsp;别：&nbsp;</span>
				<em><input class="easyui-combobox" prompt="请选择标准类型" inQuery="true" panelHeight="auto" 
						id="standardCategory" name="standardCategory"  editable="true" style="width:200px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchCategory.action'">
					</input>
				</em></li>
				<li><span >分&nbsp;&nbsp;&nbsp;类&nbsp;&nbsp;&nbsp;号：&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<em> <input class="easyui-textbox" prompt="请输入分类号" id="standardClassNo" name="standardClassNo" 
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				<li><span>采&nbsp;&nbsp;标&nbsp;&nbsp;程&nbsp;&nbsp;度：&nbsp;</span>
				<em> <input class="easyui-combobox" prompt="请选择采标程度" inQuery="true" panelHeight="auto" 
						id="adoptionDegree" name="adoptionDegree" editable="false"  style="width:200px;height:28px;" 
						data-options="valueField:'id',textField:'text',url:rootPath+'dictionary/searchDegree.action'">
						</input>	
				</em></li>
				<li><span>采用国际标准：</span>
				<em><input class="easyui-textbox" prompt="请输入国际标准" id="adoptIS" name="adoptIS"
					style="width:200px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'"
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				</ul>	
				<!-- 标准发布信息 -->
				<ul class="s-search" style="width:40%;float:left;">
					<div class="s-search-title">标准发布信息</div>
				<li><span>批准部门：</span>
				<em> <input class="easyui-textbox" prompt="请输入批准部门" id="approvedUnit" name="approvedUnit"
					style="width:224px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" 
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				 <li><span>批准时间：</span>
				<em> <input type="text" class="easyui-datebox" isrequired="true" verifyfuc="verifyimportTime()" editable="false" id="approvedDateStart" inQuery="true" style="width:100px;height:27px" alias="approvedDateStart" /> 
						<label>&nbsp;&nbsp;—&nbsp;&nbsp;</label>
				<input type="text" class="easyui-datebox" isrequired="true" verifyfuc="verifyimportTime()" editable="false" id="approvedDateEnd" inQuery="true" style="width:100px;height:27px" alias="approvedDateEnd" />
				</em></li>
				<li><span>实施时间：</span>
				<em><input type="text" class="easyui-datebox" isrequired="true" verifyfuc="verifyimportTime()" editable="false" id="effectiveDateStart" inQuery="true" style="width:100px;height:27px" alias="effectiveDateStart" /> 
						<label>&nbsp;&nbsp;—&nbsp;&nbsp;</label>
				<input type="text" class="easyui-datebox" isrequired="true" verifyfuc="verifyimportTime()" editable="false" id="effectiveDateEnd" inQuery="true" style="width:100px;height:27px" alias="effectiveDateEnd" />
				</em></li>
				<li><span>起草部门：</span>
				<em><input class="easyui-textbox" prompt="请输入起草部门" id="draftedUnit" name="draftedUnit" 
					style="width:224px;height:27px;vertical-align:middle;"
					onfocus="this.classNameName='input_selected'" 
					type="text" inQuery="true" isrequired="true" 
					operator="like"></input>
				</em></li>
				<li><span>标准状态：</span>
				<em><select class="easyui-combobox" inQuery="true" panelHeight="auto" prompt="请选择标准状态"
						id="effectiveState" name="effectiveState" editable="false" style="width:224px;height:28px;" >
						    <option value="-1">全部</option>
							<option value="1">有效</option>
							<option value="0">作废</option>
					</select>	
				</em></li>
				</ul>	
				<div class="s-search-button">
					<button class="btn btn-primary" type="button" style="width:100px;height:30px;" onclick="search();">
						<i class="icon icon-search"></i>&nbsp;&nbsp;搜索
					</button>
				</div>
			</div>
			
			<div title='查询结果' id='RAP_Result' data-options='selected:false'>
				<div></div>
				<table id="ViewID" ></table>
			</div>
		</div>
	</div>

	<!-- 点击新增所显示页面 -->
	<div id="win"></div>

</body>
</html>
