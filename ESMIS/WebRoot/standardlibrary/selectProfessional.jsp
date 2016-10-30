<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lanyuan.assembly.dictionary.ServiceDictionaryData"%>
<%@page import="com.lanyuan.assembly.basic.sqlstring.conditionresolver.OrderGroup"%>
<%@page import="com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionGroup"%>
<%@page import="com.lanyuan.assembly.basic.sqlstring.conditionresolver.ConditionNormal"%>
<%@page import="com.lanyuan.assembly.dictionary.SingleDictionaryData"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.lanyuan.assembly.util.CookiesUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
ServiceDictionaryData sdd = new ServiceDictionaryData();
OrderGroup order=new OrderGroup();
order.Add(ServiceDictionaryData.MappingList.id.name(),true);
ConditionGroup cond = new ConditionGroup();
cond.addWithAnd(new ConditionNormal(ServiceDictionaryData.MappingList.dictionaryid.name(),"1001"));
List<SingleDictionaryData> dictionaryDataList=sdd.selectByCondition(cond, order);
Cookie professionalNameCookie = CookiesUtil.getCookieByName(request, "professionalName");
String[] professionalNames;
HashMap<String,String> professionalNameMap=new HashMap<String,String>();
if (professionalNameCookie != null)
{
	String professionalName=professionalNameCookie.getValue();
    if (!StringUtils.isBlank(professionalName))
    {
        try
        {
            professionalName = URLDecoder.decode(professionalName, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        professionalNames = professionalName.split("、");
        if(professionalNames.length>0){
        	for(int i=0;i<professionalNames.length;i++){
        		professionalNameMap.put(professionalNames[i], "");
        	}
        }
    }
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>选择专业</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/min_123.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
	var rootPath = "${pageContext.request.contextPath}/";
	function saveProfessional(){
		window.location.href=rootPath+"saveProfessional.action";
		
	}
	function closeAll(){
		parent.layer.closeAll();
	}
	$(function()
	{
	 var text = window.parent.document.getElementById("applicableMajor").value;
	 arr=text.split(","); 
	 //alert(text);
	 if(text!=null&&text!=""){
		 for(var i=0;i<arr.length;i++)
		 {
			 //alert(arr[i]);
			 $("input:checkbox[name='professionalName']").eq(arr[i]-1).attr('checked',true);
		 }
	 }
	});
	
	function submitData()
	{
		if(!mycheckbox()) return;
		
		var idCollection = ",",nameCollection=",";
		var selectedList = $("input:checkbox[name='professionalName']:checked");
		for ( var i = 0; i < selectedList.length; i++) {
			var objItem = $("#" + selectedList[i].id);			
			idCollection += "," + objItem.attr("professionalId");
			nameCollection += "," + objItem.val();
		}
		window.parent.document.getElementById("professionalName").innerHTML = nameCollection.substring(2);
		window.parent.document.getElementById("applicableMajor").value = idCollection.substring(2);
		//alert(window.parent.document.getElementById("applicableMajor").value = idCollection.substring(2));
		closeAll();
		/*
        if($("#agree")..attr("checked")==true){
            $("#msg02").hide();//隐藏提示性语言
            $("#submit").removeAttr("disabled");//如果同意协议，删除disabled属性 ，使按钮可以被按下                
        }else{
            $("#msg02").show().insertAfter("#submit");//让提示性语言在“注册”栏之后显示
            $("#submit").attr('disabled',"true");//如果不同意协议，设置submit属性disabled，使按钮不能被按下
        }  	*/
	}
        
	
function mycheckbox() { 
	var falg = 0; 
	$("input:checkbox[name='professionalName']:checked").each(function () { 
		if ($(this).attr("checked")) { 
			falg += 1; 
		} 
	}) 
	if (falg > 0) {
		return true;
	}else {
		alert("请选择专业");
		return false; 
	}
} 
</script>
<style type="text/css">
.i-p-g-b-btn{
float:left; background:#666666;margin-left:16px;
}
.i-p-g-b-btn:hover{
background:#333333;
}
</style>
  </head>
  
  <body id="invest_content">
<div class="ctn-960 mg shadow-5">
    <form target="_parent" action="${pageContext.request.contextPath}/saveProfessional.action" onSubmit="return mycheckbox();" class="js-form-validate" method="post" data-arg-one="#" data-arg-two="100">
        <div class="confirm-info-list mgt clearfix" style="position: relative; margin:0 auto;">
            <%
            	if(dictionaryDataList!=null){
            		for(int i=0;i<dictionaryDataList.size();i++){
            			if(i==0 || (i)%3==0){
            			%>
            			 <div class="row">
            			<%}%>
                <label class="input-checkbox">
                    <input id="professionalId<%=dictionaryDataList.get(i).getCode() %>" name="professionalName" 
                    		value="<%=dictionaryDataList.get(i).getContent() %>" type="checkbox" 
                    		<%=professionalNameMap.containsKey(dictionaryDataList.get(i).getContent())?"checked":""%>
                    		professionalId="<%=dictionaryDataList.get(i).getCode() %>"
                    >
                    <i><%=dictionaryDataList.get(i).getContent() %></i>
                </label>
                <%if(i%3==2){
                	%>
                	</div>
                	<%
                	}
            	}
            	}
            %>
            <div style="background-color: #ddd;width:660px;height:2px;border:none;margin:0 auto 24px auto;"></div>
            <div>提示：不选择专业，默认搜索全部</div>
            <div style="text-align: center;width:340px;height:60px;margin:0 auto;">
            	<input tabindex="3" id="confirm-btn" type="button" value="确定" class="i-p-c-i-btn clearButton"  style="float:left;" onclick="submitData()">
            	<input type="button" onclick="closeAll()" value="关闭" class="i-p-c-i-btn clearButton i-p-g-b-btn" >
            </div>
        </div>
    </form>
</div>
</body>
</html:html>
