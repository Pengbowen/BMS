<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>此页面暂不使用</title>

</head>
<body>
	<div class="easyui-panel" style="width:100%,height:500px" border=0>
		<div style="padding:10px 60px 20px 60px">
			<form id="myForm">
				<input type="hidden" id="levelId" name="levelId" value="${levelId}" />
				<table cellpadding="5">
					<ul>
						<li class="detail01"><span>等级名称</span> <em>${levelName}</em>
						</li>
						<li class="detail01"><span>状态</span> <em> <s:if
									test="#request.state==1">启用</s:if> <s:else>停用</s:else> </em>
						</li>
					</ul>
					<ul>
						<li class="detail01"><span>提交人</span> <em>${inputMan}</em>
						</li>
						<li class="detail01"><span>提交人姓名</span> <em>${inputManName}</em>
						</li>
						<li class="detail01"><span>提交时间</span> <em>${inputTime}</em>
						</li>
						<li class="detail01"><span>提交IP</span> <em>${inputIp}</em>
						</li>
						<li class="detail01"><span>最后修改人</span> <em>${lastoperator}</em>
						</li>
						<li class="detail01"><span>最后修改人姓名</span> <em>${lastopratorName}</em>
						</li>
						<li class="detail01"><span>最后修改时间</span> <em>${lastoperateTime}</em>
						</li>
						<li class="detail01"><span>最后修改IP</span> <em>${lastOpereteIp}</em>
						</li>
					</ul>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
