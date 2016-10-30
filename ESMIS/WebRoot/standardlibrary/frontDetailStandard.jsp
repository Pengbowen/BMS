<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>标准查看页面</title>
    <link  href="${pageContext.request.contextPath}/standardlibrary/css/sub-style2.css" type="text/css" rel="stylesheet"/>
    <link  href="${pageContext.request.contextPath}/standardlibrary/css/zui.css" type="text/css" rel="stylesheet" />
    <link  href="${pageContext.request.contextPath}/standardlibrary/css/my.css" type="text/css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/scripts/ajaxjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/json2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/gridview.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/templist.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/condjson.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/standardlibrary/scripts/standardlibrary.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.2.js" type="text/javascript"></script>
	<!-- Easyui -->
	<link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/easyui/jquery.min.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js" type="text/javascript" ></script>
	<script type="text/javascript">
		var rootPath="${pageContext.request.contextPath}/";
		var c_name = "collect";
		var id = $("#standardId").val();
		//收藏
		function addCollect(){
    		var cookieValue = getCookie();
    		if(cookieValue != ""){
    			cookieValue += ","+id;
    		}else{
    			cookieValue = ","+id;
    		}
    		var d = new Date();
			d.setTime(d.getTime() + '365');
		/* 	alert('collect=' + cookieValue + ';expires='+d.toUTCString()+';path=' + rootPath); */
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
    	}
		//取消收藏
		function removeCookie(){
			var cookieValue = getCookie();
			cookieValue = cookieValue.replace(","+id,"");
			var d = new Date();
			d.setTime(d.getTime() + '365');
    		document.cookie='collect=' + cookieValue + ';expires='+d.toGMTString()+';path=' + rootPath;
			window.location.reload();
		}
		
		
		//判断是否收藏过当前标准； 是 true，不是false;
		function isHaveThisOne(){
		    id = ","+id;
			var value = getCookie();
			var isHava;
			if(value != ""){
				isHava = value.indexOf(id);
			}
			if(isHava != -1){
				return true;
			}
			return false;
		}
		
		
		//检测是否有cookie
		function getCookie()
		{
		if (document.cookie.length>0)
		  {
		  c_start=document.cookie.indexOf(c_name + "=");
		  if (c_start!=-1)
		    { 
		    c_start=c_start + c_name.length+1 ;
		    c_end=document.cookie.indexOf(";",c_start);
		    if (c_end==-1) c_end=document.cookie.length;
		    return unescape(document.cookie.substring(c_start,c_end));
		    } 
		  }
		return "";
		}
		function collect(){
		}
	</script>
</head>   
<body onload="change('webIndex')">
<!--头部开始 -->
<div class="top">
  <%@ include file="../header.jsp" %>
</div>
<!--头部结束 -->
<!--banner开始 -->
<div class="sub-banner-newwrap sub-banner-04">
    <div class="sub-posit">
        <div class="sub-posit-cont">
            <b style="font-size:12px;color:#fff;">当前位置：</b>
            <a class="posit-a" href="${pageContext.request.contextPath}">首页</a>
            <i class="icon icon-angle-right" style="color:#fff;"></i>
            <b style="font-size:12px;color:#fff;">${standardName }</b>
        </div>
    </div>
</div>
<!--banner结束 -->
<!--content开始 -->
<div class="sub-contnew" style="background-color:#fafafa; position:relative;">
	<div class="quanping"><a href="${pageContext.request.contextPath}/standardlibrary/frontAllPage.action?standardNo=${standardNo}"><i class="icon icon-expand-full" title="全屏显示" ></a></i></div>
    <ul class="sub-title">
  		    <input type="hidden" id="filePath" name="filePath" value="${filePath}"/> 
        	<input type="hidden" id="standardName" name="standardName" value="${standardName}"/> 
        	<input type="hidden" id="standardId" name="standardId" value="${standardId}"/> 
         <li class="sub-title-left"><i>${standardCategory }</i> &nbsp;&nbsp;<i>${standardNo }</i>
          <i>${standardName}</i><em style="  margin-left:18px;">分享人：</em><em>${sharePeople}</em>				</li>
        <li class="sub-title-right"><em>体系表位置：</em> <em>${SSTLoaction }</em>
            <i class="icon icon-download-alt"></i><a onclick="downLoad()" >下载</a>
            <i class="icon icon-star-empty"></i><a onclick="addCollect()">收藏</a>
            <i class="icon icon-star-empty"></i><a onclick="removeCookie()">收藏</a>
        </li>
    </ul>
    <ul class="sub-content">
        <li class="sub-content-text">
            <h2>工业企业厂界环境噪音排放标准</h2>
            <h3>1<i>.</i>适用范围</h3>
            <p>本标准规定了工业企业和固定设各厂界环境噪声排放限值及其测量方法。</p>
            <p>本标准适用于工业企业噪声排放的管理、评价及控制。机关、事业单位、团体等对外环境排放噪声的单位也按本标准执行。</p>
            <h3>2<i>.</i>规范性引用文件</h3>
            <p>本标准内容引用了下列文件或其中的条款。凡是不注日期的引用文件，其有效版本适用于标准。</p>
            <p>GB  3096  声环境质量标准</p>
            <p>GB  3785  声级计电、声性能及测试方法</p>
            <p>GB/T  3241  倍频程和分数倍频程滤波器</p>
            <p>GB/T  15173  声校准器</p>
            <p>GB/T  15190  城市区域环境噪声适用区划分技术规范</p>
            <p>GB/T  17181  积分平均声级计</p>
            <h3>3<i>.</i>术语和定义</h3>
            <p>下列术语和定义用于本标准。</p>
            <h3>3.1 &nbsp;&nbsp;工业企业厂界环境噪声  industrial enterprises noise</h3>
            <p>指在工业生产活动中使用固定设备等产生的、在厂界进行测量和控制的干扰周围生活环境的声音。</p>
            <h3>3.2 &nbsp;&nbsp;A 声级 A-weighted sound pressure level</h3>
            <p>用A计权网络测得的声压级，用<sub>A</sub><b>L</b>表示，单位dB(A)。</p>
            <h3>3.3 &nbsp;&nbsp;等效声级 equivalent continuous A-weighted sound pressure level</h3>
            <p>等效连续A声级的简称，指在规定测量时间<b>T</b>内A声级的能量平均值，用L<sub>Aeq，T</sub>表示，（简写为L<sub>eq</sub>，单位dB|（A）。除特别指明外，本标准中噪声皆为等效声级。</p>
            <p>根据定义，等效声级表示为：</p>
            <h4>
                l<sub>eq</sub>=101<em>g</em><b>{1/T10<sup>0.1L<sub>A</sub></sup>dt}</b>
            </h4>
            <p>式中： l<sub>A</sub>——t时刻的瞬时A声级；</p>
            <p style="margin-left:64px;">T——规定的测量时间段。</p>
            <h3>3.4 &nbsp;&nbsp;厂界 boundary</h3>
            <p>由法律文书（如土地使用证、房产证、租赁合同等）中确定的业主所拥有使用权（或所有权）的场所或建筑物边界。各种产生噪声的固定设备的厂界为其实际占地的边界。</p>
            <h3>3.5 噪声敏感建筑物 niose-sensitive buildings</h3>
            <p>指医院、学校、机关、科研单位、住宅等需要保持安静的建筑物。</p>
            <h5>3</h5>
        </li>
    </ul>
</div>
<!--底部开始 -->
<div class="bottom">
    <iframe src="${pageContext.request.contextPath}/footer.html" frameborder="0" width="100%" height="76" scrolling="no" marginwidth="0" marginheight="0"></iframe>
</div>
<!--底部结束 -->
</body>