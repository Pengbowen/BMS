<%@page import="org.apache.poi.ss.formula.functions.Count"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lanyuan.assembly.commonmodule.permission.*"%>
<%@page import="com.lanyuan.web.LoginAuthentication.LoginUser"%>
<%@page import="com.lanyuan.actionapi.userlogin.ConstApplication"%>
<%@page import="com.lanyuan.assembly.view.WebSite"%>
<%
	String basePath = WebSite.getWebSiteUrl(request);

	//退出系统 清除session
	if (!(request.getSession().getAttribute(LoginUser.SESSIONID) == null)) {

		String param = null;

		if (!"".equals(org.apache.commons.lang.StringUtils
				.defaultString(request.getParameter("param")))) {
			param = request.getParameter("param");
			if ("out".equals(param)) {
				request.getSession().setAttribute(LoginUser.SESSIONID,null);
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
	}

	//如果没有登录跳转到登陆页面
	if (request.getSession().getAttribute(LoginUser.SESSIONID) == null) {
		request.getRequestDispatcher("login.jsp").forward(
				request, response);
		return;
	}

	LoginUser user = (LoginUser) request.getSession().getAttribute(
			LoginUser.SESSIONID);
	//获取当前登陆人的名
	String userName = (user.getRealName() == null ? user.getLoginName()
			: user.getRealName());
	//获取当前登陆人登陆某个应用的权限	
	Set<String> limitSet = user
			.getAppPermissions(ConstApplication.ApplicationName);

	//*************************读取一级的权限Start**********************************
	LinkedHashMap<String, String> topArr = new LinkedHashMap<String, String>();

	//获取系统的所有权限
	ApplicationPermission appPer = ApplicationPermission.getInstance();

	if (limitSet != null) {
		if (appPer.size() > 0) {
			for (SinglePermission cls : appPer) {
				if (limitSet.contains(cls.getPermissionId())) {
					if (cls.getEnable() && cls.getParentId() == null) {
						topArr.put(cls.getPermissionId(),
								cls.getPerName());
					}
				}
			}
		}
	}

	//*************************读取一级的权限End**********************************

	int index = 0;

	StringBuilder sb = new StringBuilder();

	String strTpl = "<div title='{name}' id='{key}' data-options='selected:{select}'><ul class=\"tree easyui-tree\" data-options=\"animate:true,lines:true\">";
	String strTemp = "";

	for (Map.Entry<String, String> item : topArr.entrySet()) {

		String stb = getchildren(appPer, limitSet, item.getKey(),
				basePath);

		strTemp = strTpl;

		strTemp = strTemp.replace("{key}",
				item.getKey().replaceAll("\\.", "_"));

		strTemp = strTemp.replace("{name}", item.getValue());
		//if(index == 0){
		strTemp = strTemp.replace("{select}", "true");
		//}else{
		strTemp = strTemp.replace("{select}", "false");
		strTemp += stb;
		strTemp += "</ul>";
		strTemp += "</div>\n";
		sb.append(strTemp);
		//}

		index++;
	}

	//System.out.println("stringbuilder:" + sb.toString());

	/*----------------------------------！！！！！！！！！！！！！！！！！！分割线！！！！！！！！！！！！！！！！-----------------------------*/
%>
<%!//*************************读取二级的权限Start**********************************
	private String getchildren(ApplicationPermission appPer,
			Set<String> limitSet, String currentLimit, String basePath) {
		StringBuilder strb = new StringBuilder();
		String strParam = "<li id='{key}' data-options=\"iconCls:'{img}'\"><span><a \nonclick=\"tab('{biaoti}','{url}')\">{name}</a></span>";
		String strHtml = "";
		LinkedHashMap<String, HashMap<String, String>> treeMap = new LinkedHashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		if (limitSet != null) {
			if (appPer.size() > 0) {
				for (SinglePermission cls : appPer) {
					if (limitSet.contains(cls.getPermissionId())) {
						if (cls.getParentId() != null
								&& currentLimit.equals(cls.getParentId())
								&& cls.getEnable()) {
							strHtml = strParam;
							if (!cls.isAction()) {
								strHtml = strHtml.replace(
												"tab('{biaoti}','{url}')", "");
							}
							strHtml = strHtml.replace("{key}", cls
									.getPermissionId().replaceAll("\\.", "_"));
							strHtml = strHtml
									.replace("{img}", "icon-group_add");
							strHtml = strHtml.replace("{biaoti}",
									cls.getPerName());
							strHtml = strHtml.replace("{url}",
									basePath + cls.getTargetsToURL());
							strHtml = strHtml.replace("{name}",
									cls.getPerName());
							if (!cls.isAction()) {
								strHtml += "<ul>\n";
								String str = getchildren(appPer, limitSet,
										cls.getPermissionId(), basePath);
								strHtml += str;
								strHtml += "</ul>\n";
							}
							strHtml += "</li>\n";
							strb.append(strHtml);
							map.put("name", cls.getPerName());
							map.put("url", basePath + cls.getTargetsToURL());
							treeMap.put(cls.getPermissionId(), map);
						}
					}
				}
			}
		}
		return strb.toString();
	}

	//*************************读取二级的权限End**********************************%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!--  引easyui中内容-->
<script src="easyui/jquery.min.js" type="text/javascript"></script>
<script src="easyui/jquery.easyui.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="easyui/themes/icon.css" type="text/css" />
<link id="uiTheme" rel="stylesheet" href="easyui/themes/default/easyui.css" type="text/css" />
<script src="scripts/jquery.cookie.js" type="text/javascript"></script>
<script src="scripts/toppage.js" type="text/javascript" ></script>

<!--  本系统自有内容-->
<link href="${pageContext.request.contextPath}/style/public.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/frame.css"
	rel="stylesheet" type="text/css" />

<link href="style/tree.css" rel="stylesheet" type="text/css" />
<script src="scripts/tabformat.js" type="text/javascript"></script>
<script src="scripts/login.js" type="text/javascript"></script>

<title>企业标准管理系统—后台</title>
</head>
<!--  页面主题部分-->
<body class="easyui-layout" style="overflow-y: hidden" id="tbodyLayout">
	<!-- 进入主页的遮罩层，便于左侧树形优化视觉效果 -->
	<div id='Loading' style="position: absolute; z-index: 1000; top: 0px; left: 0px;  
    width: 100%; height: 100%; background: gray; text-align: center;">  
	    <h1 style="top: 48%; position: relative;">  
	        <font color="#15428B">欢迎进入《企业标准管理系统—后台》页面···</font>  
	    </h1>  
	</div> 
	<noscript>
		<div
			style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="false" border="false" style="overflow: hidden;width:100%;height:57px;">
       <div id="top" style="width:100%;">
		<div class="top">
			<div id="top_logo" class="topLogo">
				<div class="logo" id="logo">
					<img src="${pageContext.request.contextPath}/images/logo.png" />
					<span><font size="4px">『<i>企业标准管理系统—后台</i>』</font></span>
				</div>
				<div id="links" class="operate1">
					<div class="operate">
							<ul>
								<li><img
									src="${pageContext.request.contextPath}/images/top_ico01.png" />
									<a href="javascript:void(0);" onclick="win()"> 修改密码 </a>
								</li>
								<li><img
									src="${pageContext.request.contextPath}/images/top_ico02.png" />
									<a href="javascript:void(0);" onclick="LogOut();"> 退出系统 </a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div data-options="region:'south'" split="true" style="height: 30px;">
		<div class="footer">技术支持：蓝元软件科技有限公司 &nbsp;&nbsp;&nbsp;&nbsp;
			联系电话：0371-60985700</div>
	</div>

	<!--  导航栏信息-->
	<div data-options="region:'west',hide:true,split:true" title="导航菜单"
		style="width:280px;" id="west">
		<div data-options="animate:false,fit:true,border:false" id="nav"
			class="easyui-accordion">
			<%=sb.toString()%>
		</div>
	</div>
	<!--  主页显示内容-->
	<div id="mainPanle" data-options="region:'center'"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs"
			data-options="fit:true,border:false">
			<div title="主页" iconCls="icon-hamburg-home">
				<!-- 主页显示内容 -->
				<iframe src="<%= basePath%>homePage.jsp" style="width: 100%;height:100%" frameborder="0">
				</iframe>
			</div>
		</div>
	</div>

	<!-- 右击鼠标显示 -->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<div id="win"></div>
	<script type="text/javascript">
		//关闭遮罩层
		function closes() {  
	        $("#Loading").fadeOut("normal", function () {  
	            $(this).remove();  
	        });  
	    }  
	    //easyui插件加载完成后关闭遮罩层
	    var pc;  
	    $.parser.onComplete = function () {  
	        if (pc) clearTimeout(pc);  
	        pc = setTimeout(closes, 100);  
	    } 
		// 退出系统
		function LogOut() {
			$.messager.confirm('退出系统', '确认退出?', function(r) {
				if (r) {
					$.messager.alert('信息提示', '退出成功！');
					window.location="${pageContext.request.contextPath}/login/loginOut.action";
				}
			});
		}
		// 修改密码
		function win() {
			var rootPath = "${pageContext.request.contextPath}/";
			var url = rootPath+'/modifypasswd.jsp';
			showDialogPage('修改密码',url,'450','300');
		}
		//关闭
		function closeWin()
		{
			$("#win").window('close', true);			
		}
		//独立于整个页面
		function showDialogPage(title,url,width,height)
		{
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';			
			$('#win').window({
				width : width,
				height : height,
				title : title,
				// 定义窗口是否可拖拽。
				draggable : true,
				// 定义是否显示最大化按钮。
				maximizable : true,
				// 定义是否显示最小化按钮。
				minimizable : false,
				// 定义是否显示折叠按钮。
				collapsible : false,
				// 定义窗口是不是模态（modal）窗口。
				modal : true,
				content : content,
			});
			// 窗口居中。
			$('#win').center;			
		}
	</script>
</body>
</html>