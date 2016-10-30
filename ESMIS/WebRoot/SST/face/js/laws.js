function search() {
	var url = rootPath+"lawslibrary/frontLikeSearch.action?linesOfPage=20";
	var name = $("#lawsName").val();
	var lawsType = $("input[name='law']:checked").val();
	name = encodeURIComponent(encodeURIComponent(name));
	var searchUrl = url + "&likeSearch=" + name + "&lawsType=" + lawsType;
	AjaxJson(searchUrl,true,null,loadData,false,false);
}

function loadData(json){
	alert(json);
}