/**
 * kingeditor编辑器里 快捷键 “Ctrl+V” 粘贴 自动上传图片
 * 
 */
function df() {

	var haspicContainer = document.getElementById("has_pic");
	
	if (haspicContainer == null) {
        haspicContainer = document.createElement("div");
        haspicContainer.id = "has_pic";
        haspicContainer.innerHTML = "<input type='text' id='piclist' value='' style='display:none;'/>" +
        		"<div id='upload'><b style='width:250px;font-size:12pt;'>您有图片需要上传到服务器</b>" +
        		"<a href='javascript:;' onclick='uploadpic();' style='color:blue' >上传</a></div>" +
        		"<div id='confirm' style='width:250px;font-size:12pt;'></div>";
        $(".ke-toolbar").after(haspicContainer);
    }

    var img = $(".ke-edit-iframe").contents().find("img");
    var sstr = "";
    var piccount=0;
    
    $(img).each(function () {
        var that = $(this);
        if (that.attr("src").indexOf("file://") >= 0) {
            sstr += that.attr("src") + ",";
            piccount++;
        }
        if(that.attr("src").indexOf("data:image/png;base64,") >= 0){
        	var ss=that.attr("src").substring(that.attr("src").indexOf("base64,")+7);
        	sstr += ss + ",";
        	 piccount++;
        }
    });
    
    if(piccount>0)
    {
      sstr=sstr.substring(0, sstr.lastIndexOf(","));
      $("#piclist").val(sstr);
      document.getElementById("has_pic").style="background-color:pink;";
      $("#confirm").html("");
      document.getElementById("has_pic").style.display ="block";
      $("#upload").show();
    }
    else
    {
    	document.getElementById("has_pic").style.display ="none";
    }
    
    piccount=0;
}

//上传
function uploadpic() {
	if($("#piclist").val()=="")return false;
    var piclist = encodeURI($("#piclist").val());
    if (piclist.length == 0) return false;

    $.ajax({
        url: "../upload/upfiles.action",
        data: "imgList=" + piclist,
        type: "POST",
        dataType:"json",
        async:false,
        beforeSend: function () {
            $("#upload").hide();
            $("#confirm").text("正在上传中...");
        },
        success: function (data) {
            var jsonObj=eval(data);
        	if(jsonObj.result=="1")
			{
                var str = jsonObj.message.split(',');
                var img = $(".ke-edit-iframe").contents().find("img");
                var i=0;
                $(img).each(function () {
                    var that = $(this);
                    if (that.attr("src").indexOf("file://") >= 0 ||that.attr("src").indexOf("data:image/png;base64,") >= 0) {
                    	that.attr("src", str[i]);
                        that.attr("data-ke-src", str[i]);
                        i++;
                    }
                });

               $("#confirm").text("").html(i + "张图片已经上传成功！&nbsp;&nbsp;<a href='javascript:;' onclick='closeupload();'>关闭</a>");
			}
            else
            {
            	$("#confirm").text("上传失败！");
            }
        }
    });
}

//关闭
function closeupload() {
    $("#has_pic").hide();
    $("#upload").show();
    $("#piclist").val("");
}