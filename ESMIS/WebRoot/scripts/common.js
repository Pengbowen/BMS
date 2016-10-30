
//页面公用的js，css样式集合
__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
};

var bootPATH = __CreateJSPath("common.js");


document.write('<script src="' + bootPATH + 'calendar.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'condjson.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'templist.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'controlfun.js" type="text/javascript" ></sc' + 'ript>');
//公用js
document.write('<script src="' + bootPATH + 'ajaxjson.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'jquery-1.8.2.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'gridview.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'changeField.js" type="text/javascript" ></sc' + 'ript>');

//easyui中css样式和js文件
document.write('<link href="../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />');
document.write('<script src="../easyui/jquery.min.js" type="text/javascript" ></sc' + 'ript>');
document.write('<script src="../easyui/jquery.easyui.min.js" type="text/javascript" ></sc' + 'ript>');

//新增样式
document.write('<link href="../style/frame.css" rel="stylesheet" type="text/css" />');
document.write('<link href="../style/style.css" rel="stylesheet" type="text/css" />');
document.write('<link href="../style/css/zui.css" rel="stylesheet" type="text/css" />');