//点击表头时样式改动
$(function(){
    $('.tab-menu a').click(function(){
    $(this).addClass('hit').siblings().removeClass('hit');
    $('.pane-box>div:eq('+$(this).index()+')').show().siblings().hide();
    })
});
$(function(){
    $(".pane .nLi h3").click(function(){
    if($(this).parent(".nLi").hasClass("on")){
        $(this).next(".sub").slideUp(300,function(){
            $(this).parent(".nLi").removeClass("on")
            if($("#icon").hasClass("icon-chevron-up")){
                $("#icon").removeClass("icon-chevron-up")
                $("#icon").addClass("icon-chevron-down");
            }
        });
    }else{
        $(this).next(".sub").slideDown(300,function(){
            $(this).parent(".nLi").addClass("on")
            if($("#icon").hasClass("icon-chevron-down")){
                $("#icon").removeClass("icon-chevron-down")
                $("#icon").addClass("icon-chevron-up");
                }
            });
        }
    })
})

//点击列表动态改变字段名称
function changeTotal(sign){
	switch (sign) {
	case 1:
	LoadDataUrl = rootPath + "standardrepor/searchStatement.action?reportId=10"
	$("#ceshi").text("标准统计");
	$('#' + gridviewId).datagrid({
		fit : false,
		nowrap : true,//当数据长度超出列宽时将会自动截取
		singleSelect : true,//是否只允许选择一行
		striped : true,//交替显示行背景
		idField : 'standardcategory',//唯一列
		columns : [ [//设置gridview的列信息*
		{
			field : 'code',
			title : '序号',
			width : '40',
			align : 'center',
			formatter : showClassNo
		}, {
			field : 'standardCategory',
			title : '标准类别',
			width : '300',
			align : 'center'
		}, {
			field : 'standardCategoryName',
			title : '标准类别名称',
			width : '350',
			align : 'center'
		}, {
			field : 'quantity',
			title : '合计',
			width : '250',
			align : 'right'
		} ] ],
		pagination : false
	});
	//延迟100ms执行加载数据函数：loadingInfo
	setTimeout(loadingInfo, 100);
	break;
case 2:
	$("#ceshi").text("技术标准统计");
	break;
case 3:
	$("#ceshi").text("管理标准统计");
	break;
case 4:
	$("#ceshi").text("工作标准统计");
	break;
case 5:
	/**法律法规**/
	$("#ceshi").text("法律法规统计");
	LoadDataUrl = rootPath + "standardrepor/searchStatement.action?reportId=12"
	$('#' + gridviewId).datagrid({
		fit : false,
		nowrap : true,//当数据长度超出列宽时将会自动截取
		singleSelect : true,//是否只允许选择一行
		striped : true,//交替显示行背景
		idField : 'standardcategory',//唯一列
		columns : [ [//设置gridview的列信息*
 			{
 				field : 'code',
 				title : '序号',
 				width : '55',
 				align : 'center',
 				formatter : showClassNo
 			}, 	{
 				field : 'standardCategory',
 				title : '法规类别号',
 				width : '350',
 				align : 'center',
 				hidden:true
 			},  {
 				field : 'standardCategoryName',
 				title : '法规类别',
 				width : '500',
 				align : 'center',
 			}, {
 				field : 'quantity',
 				title : '数量',
 				width : '400',
 				align : 'right',
 			}] ],
		pagination : false
	});
	//延迟100ms执行加载数据函数：loadingInfo
		setTimeout(loadingInfo, 100);
		break;
	default:
	}
}