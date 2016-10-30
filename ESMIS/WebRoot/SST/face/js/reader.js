///��ȡ�����
function getBrowserInfo() {
    var agent = navigator.userAgent.toLowerCase();
    var regStr_ie = /msie [\d.]+;/gi;
    var regStr_ff = /firefox\/[\d.]+/gi;
    var regStr_chrome = /chrome\/[\d.]+/gi;
    var regStr_saf = /safari\/[\d.]+/gi;
    var regStr_weixin = /micromessenger\/[\d.]+/gi;

    //IE
    if (agent.indexOf("msie") > 0) {
        return agent.match(regStr_ie);
    }
    //firefox
    if (agent.indexOf("firefox") > 0) {
        return agent.match(regStr_ff);
    }
    //Chrome
    if (agent.indexOf("chrome") > 0) {
        return agent.match(regStr_chrome);
    }
    //Safari
    if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
        return agent.match(regStr_saf);
    }
    //micromessenger
    if (agent.indexOf("micromessenger") > 0) {
        return agent.match(regStr_weixin);
    }
}

//��ȡ������İ汾��
function getBrowserVersionInfo() {
    var browser = getBrowserInfo();
    return (browser + "").replace(/[^0-9.]/ig, "");
}

///���ÿؼ��Զ����Ÿ��ؼ��ĸ߶�
function ReaderAutoHeightSize(id) {
    var pTar = $("#" + id);
    if (pTar) {
        var pParent = pTar.parent();
        if (pParent.height() > pTar.height()) {
            pTar.attr("height", pParent.height() - 10);
        }
    }
}
///��doc/xls/ppt�ļ�
function openOfficeFile(url) {
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    //���ؿؼ�  
    document.all.WebOffice1.LoadOriginalFile(url, prefix);
    //���ز���Ҫ�İ�ť
    document.all.WebOffice1.HideMenuItem(0x01 + 0x02 + 0x04 + 0x10 + 0x20);
}

///��ʾ�ļ� jpg,gif,png,bmp,mpeg
function showImageFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    switch (prefix) {
        case "jpg":
        case "gif":
        case "png":
        case "bmp":
            html += '<img id="' + id + '" src="' + url + '" height="' + height + '" width="' + width + '" border="0"/>';
            break;
        case "mpeg":
            html += '<img id="' + id + '" dynsrc="' + url + '" border="0" start="fileopen"/>';
            break;
    }
    return html;
}

///��ʾ�ļ� mp3/wma/ram/mid/wmv/asf
function showAudioFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    if (isIExplorer) {
        switch (prefix) {
            case "mp3":
            case "wma":
                html += '<embed id="' + id + '" src="' + url + '" height="' + height + '" width="' + width + '" border="0" autostart="ture" loop="true">';
                html += '</embed>';
                break;
            case "ram":
                html += '<embed id="' + id + '" src="' + url + '" height="' + height + '" width="' + width + '"';
                html += ' autostart="true" loop="true" controls="PlayButton" ';
                html += ' type="audio/x-pn-realaudio-plugin" src="' + url + '"> </embed>';
                break;
            case "mid":
                html += '<embed src="' + url + '" align="center" border="0" height="' + height + '" width="' + width + '" autostart="true" loop="true"></embed>';
                break;
            case "wmv":
            case "asf":
                html += '<embed src="' + url + '" autostart="true" loop="true"  height="' + height + '" width="' + width + '" ></embed>';
                break;
            default:
                html += showOtherFile(id, url, width, height);
        }
    } else {
        switch (prefix) {
            case "mp3":
            case "wav":
                html += '<audio id="' + id + '" width="' + width + '" height="' + height + '" controls="controls" autoplay="autoplay">';
                html += '<source src="' + url + '" type="video/mpeg">';
                html += 'your browser does not support the video tag.';
                html += '</audio>';
                break;
            case "ogg":
                html += '<audio id="' + id + '" width="' + width + '" height="' + height + '" controls="controls" autoplay="autoplay">';
                html += '<source src="' + url + '" type="video/ogg">';
                html += 'your browser does not support the video tag.';
                html += '</audio>';
                break;
            default:
                html += showOtherFile(id, url, width, height);
        }
    }
    return html;
}
///��ʾ�ļ� rm/rmvb
function showMediaFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);
    html += '<center>';
    if (isIExplorer) {

        switch (prefix) {
            case "rm":
                html += '<OBJECT classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA" id="' + id + '" name="' + id + '" width="' + width + '" height="' + height + '" VIEWASTEXT>';
                html += '<param name="wmode" value="transparent"/>';
                html += '<param name="_ExtentX" value="5503">';
                html += '<param name="_ExtentY" value="1588">';
                html += '<param name="AUTOSTART" value="-1">';
                html += '<param name="SHUFFLE" value="0">';
                html += '<param name="PREFETCH" value="0">';
                html += '<param name="NOLABELS" value="0">';
                html += '<param name="SRC" value="' + url + '">';
                //html += '<param name="CONTROLS" value="Imagewindow,StatusBar,ControlPanel">';
                html += '<param name="CONTROLS" value="Imagewindow,ControlPanel">';
                html += '<param name="CONSOLE" value="RAPLAYER">';
                html += '<param name="LOOP" value="0">';
                html += '<param name="NUMLOOP" value="0">';
                html += '<param name="CENTER" value="0">';
                html += '<param name="MAINTAINASPECT" value="0">';
                html += '<param name="BACKGROUNDCOLOR" value="#000000"></object>';
                break;
            case "rmvb":
                html += '<object classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA" id="' + id + '" width="' + width + '" height="' + height + '">';
                html += '<param name="wmode" value="transparent"/>';
                html += '<param name="_ExtentX" value="10001">';
                html += '<param name="_ExtentY" value="6879">';
                html += '<param name="AUTOSTART" value="-1">';
                html += '<param name="SHUFFLE" value="0">';
                html += '<param name="PREFETCH" value="0">';
                html += '<param name="NOLABELS" value="-1">';
                html += '<param name="SRC" value="" +url+ "">';
                html += '<param name="CONTROLS" value="Imagewindow">';
                html += '<param name="CONSOLE" value="clip1">';
                html += '<param name="LOOP" value="0">';
                html += '<param name="NUMLOOP" value="0">';
                html += '<param name="CENTER" value="0">';
                html += '<param name="MAINTAINASPECT" value="0">';
                html += '<param name="BACKGROUNDCOLOR" value="#000000"></object>';
                html += '<object classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA" width="' + width + '" height="50" id="' + id + 'RP2">';
                html += '<param name="wmode" value="transparent"/>';
                html += '<param name="_ExtentX" value="10001">';
                html += '<param name="_ExtentY" value="1270">';
                html += '<param name="AUTOSTART" value="-1">';
                html += '<param name="SHUFFLE" value="0">';
                html += '<param name="PREFETCH" value="0">';
                html += '<param name="NOLABELS" value="-1">';
                html += '<param name="SRC" value="' + url + '">';
                html += '<param name="CONTROLS" value="ControlPanel,StatusBar">';
                html += '<param name="CONSOLE" value="clip1">';
                html += '<param name="LOOP" value="0">';
                html += '<param name="NUMLOOP" value="0">';
                html += '<param name="CENTER" value="0">';
                html += '<param name="MAINTAINASPECT" value="0">';
                html += '<param name="BACKGROUNDCOLOR" value="#000000"></object>';
                break;
        }
    } else {
        switch (prefix) {
            case "ogg":
            case "mp4":
                html += '<video  id="' + id + '" width="' + width + '" height="' + height + '" controls="controls" autoplay="autoplay">';
                html += '<source src="' + url + '" type="video/' + prefix + '">';
                html += 'your browser does not support the video tag.';
                html += '</video>';
                break;
            default:
                html += showOtherFile(id, url, width, height);
        }
    }
    html += '</center>';
    return html;

}

///��ʾ�ļ� flv/swf
function showFlashFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    switch (prefix) {
        case "flv":
            html += '<script src="script/swfobject.js" type="text/javascript"></script>';
            html += 'var s5 = new SWFObject("flash/FlvPlayer201002.swf","' + id + '","' + width + '", "' + height + '", "7");';
            html += 's5.addParam("allowfullscreen", "true");';
            html += 's5.addVariable("file", "' + url + '");';
            html += 's5.addVariable("width", "' + width + '");';
            html += 's5.addVariable("height", "' + height + '");';
            html += 's5.write("myplay");';
            html += ' </script>';
            break;
        case "swf":
            html = '<embed src="' + url + '" quality=high ';
            html += 'pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" ';
            html += 'type="application/x-shockwave-flash" width="' + width + '" height="' + height + '"></embed>';
            break;
    }
    return html;
}

///��ʾ�ļ� doc/xsl/ppt/pdg
function showOfficeFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    html += '<object id="' + id + '" height="' + height + '" width="' + width + '" style="left:0px;TOP:0px" ';
    html += 'classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5" codebase="js/WebOffice.ocx">';
    html += '<param name="_ExtentX" value="6350">';
    html += '<param name="_ExtentY" value="6350">';
    html += '</object>';

    html += '    <script type="text/javascript"> ';
    html += '    openOfficeFile("' + url + '","' + prefix + '");';
 //   html += '    ReaderAutoHeightSize("' + id + '");';
    html += '    </script>';
    return html;
}

///��ʾ�ļ� pdf
function showPDFFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    if (isIExplorer) {
        html += '<OBJECT ID="' + id + '" data="' + url + '" WIDTH="' + width + '" HEIGHT="' + height + '"  type="application/pdf" >';
        html += "</OBJECT>";
    } else {
        //"ShowFileDetail.aspx?file=" & FileName & "&fexc=" & AppendFexc & ""
        html += '<iframe id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" style="width:' + width + ';height:' + height + ';"';
        html += ' allowTransparency="true"></iframe> ';
    }
    html += '    <script type="text/javascript"> ';
    html += '    ReaderAutoHeightSize("' + id + '");';
    if (isIExplorer) {
        html += '     var pdfReader = document.getElementById("' + id + '"); ';
        html += '     pdfReader.setShowToolbar(false);  ';
        html += '     pdfReader.setShowScrollbars(false); ';
    }
    html += '    </script>';
    return html;
}

///��ʾ�ļ� txt/log/html/htm
function showTextFile(id, url, width, height) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    html += '<iframe id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" '
    html += ' style="width:' + width + ';height:' + height + ';" allowTransparency="true"></iframe>';

    html += '    <script type="text/javascript"> ';
    html += '    ReaderAutoHeightSize("' + id + '");';
    html += '    </script>';
    return html;
}

///��ʾ�ļ� ����δ֪�����ļ�
function showOtherFile(id, url, width, height) {
    var html = "";
    //var prefix = url.substring(url.indexOf(".")+1);
    var str = getBrowserInfo() + "";
    var isIExplorer = (str.indexOf("msie") >= 0);

    html += '<iframe id="' + id + '" frameborder="0" src="' + url + '" ';
    html += ' scrolling="yes" style="width:' + width + ';height:' + height + ';" allowTransparency="true"></iframe>';
    html += '    <script type="text/javascript"> ';
    html += '    ReaderAutoHeightSize("' + id + '");';
    html += '    </script>';
    return html;
}

function showFile(id, url) {
    var html = "";
    var prefix = url.substring(url.indexOf(".") + 1).toLowerCase();
    switch (prefix) {
        case "jpg":
        case "gif":
        case "png":
        case "bmp":
        case "mpeg":
            ///��ʾ�ļ� jpg,gif,png,bmp,mpeg
            html = showImageFile(id, url, 600, 500);
            break;
        case "mp3":
        case "wma":
            html = showAudioFile(id, url, 600, 45);
            break;
        case "ram":
        case "mid":
        case "wmv":
        case "asf":
            ///��ʾ�ļ� mp3/wma/ram/mid/wmv/asf
            html = showAudioFile(id, url, 600, 45);
            break;
        case "rm":
        case "rmvb":
            ///��ʾ�ļ� rm/rmvb
            return showMediaFile(id, url, 900, 600);
        case "flv":
        case "swf":
            ///��ʾ�ļ� flv/swf
            html = showFlashFile(id, url, 800, 600);
            break;
        case "doc":
        case "xsl":
        case "ppt":
            ///��ʾ�ļ� doc/xsl/ppt/pdg
            html = showOfficeFile(id, url, "100%", 800);
            break;
        case "pdf":
            ///��ʾ�ļ� pdf
            html = showPDFFile(id, url, "100%", 600);
            break;
        case "txt":
        case "log":
            ///��ʾ�ļ� txt/log
            url = 'ShowFileDetail.aspx?file=' + encodeURIComponent(url) + '&fexc=' + prefix;
            html = showTextFile(id, url, "100%", 800);
            break;
        case "htm":
        case "html":
            ///��ʾ�ļ� html/htm
            html = showOtherFile(id, url, "100%", 800);
            break;
        default:
            ///��ʾ�ļ� ����δ֪�����ļ�pdg
            html = showOtherFile(id, url, "100%", 800);
    }
    //alert(html);
    return html;
}