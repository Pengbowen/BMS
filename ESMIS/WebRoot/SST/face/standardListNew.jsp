<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
    <title>高级查询页面</title>
    <link  href="${pageContext.request.contextPath}/css/zui.css" type="text/css" rel="stylesheet"/>
       <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link  href="${pageContext.request.contextPath}/css/list-1.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.js"></script>
    <script type="text/javascript">
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
    </script>
</head>
<body>
<!--头部导航start-->
<div class="nav">
    <div class="nav-top">
        <div class="nav-left">
            <div class="logo"></div>
            <a class="active" href="javascript:vido(0);">首页</a>
            <a  href="javascript:vido(0);">体系表</a>
            <a  href="javascript:vido(0);">资源展示</a>
            <a  href="javascript:vido(0);">资源统计</a>
            <a  href="javascript:vido(0);">知识库</a>
        </div>
        <div class="nav-right">
            <a href="javascript:vido(0);">登录<i class="icon icon-lock"></i></a>
            <a href="javascript:vido(0);">我的收藏<i class="icon icon-star"></i></a>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!--头部导航end-->
<!--头部tab选项卡开始-->
<div class="tabPanel">
        <div class="tab-menu">
            <a class="hit" href="javascript:vido(0);">标准查询</a>
            <a href="javascript:vido(0);">法律法规查询</a>
        </div>
</div>
<!--头部tab选项卡结束-->

<!--头部tab选项卡内容开始-->
<div class="search-table">
    <div class="pane-box">
        <div class="pane" style="display:block;">
            <div class="tab-posit-cont">
                <b style="font-size:12px;color:#fff;">当前位置：</b>
                <a class="posit-b" href="javascript:vido(0);">首页</a>
                <i class="icon icon-angle-right" style="color:#fff;"></i>
                <a class="posit-b" href="javascript:vido(0);">精准查询</a>
                <i class="icon icon-angle-right" style="color:#fff;"></i>
                <a class="posit-a" href="javascript:vido(0);">标准查询</a>
            </div>
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">
                    <h3>
                        查询条件
                        <i class="icon icon-chevron-up" id="icon"></i>
                    </h3>
                    <ul class="sub">
                        <li>
                            <label>标准编号 :</label>
                            <input   value="" class="search-1"/>
                            <label>标准名称 :</label>
                            <input   value="" class="search-2"/>
                             <label>标准类别 :</label>
                            <select class="search-1">
                                <option></option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </li>
                        <li class="search-btn">
                            <button>查询</button>
                        </li>
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->

            <div class="search-list GB detele">
                <img src="image/zuofei-bg.png" class="ph-zuofei"/>
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                   <!-- <li class="text-content">
                        <a href="javascript:vido(0);">
                            《<em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准》GB12348-2008，是行业行政法规，
                            是为了防治<em class="key-word">环境</em>噪音污染，保护和改善生活<em class="key-word">环境</em>，保障人体健康，促进经济和社会可持续发展，而放标准，是由国建
                            <em class="key-word">环境</em>保护部与国家质量监督检验疫总局联合发布的。
                        </a>
                    </li>-->
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list SSL">
                <span>SSL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list DL">
                <span>DL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list Q">
                <span>Q</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list SSL detele">
                <img src="image/zuofei-bg.png" class="ph-zuofei"/>
                <span>SSL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB detele">
                <img src="image/zuofei-bg.png" class="ph-zuofei"/>
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <!-- <li class="text-content">
                         <a href="javascript:vido(0);">
                             《<em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准》GB12348-2008，是行业行政法规，
                             是为了防治<em class="key-word">环境</em>噪音污染，保护和改善生活<em class="key-word">环境</em>，保障人体健康，促进经济和社会可持续发展，而放标准，是由国建
                             <em class="key-word">环境</em>保护部与国家质量监督检验疫总局联合发布的。
                         </a>
                     </li>-->
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list SSL">
                <span>SSL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list DL">
                <span>DL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list Q">
                <span>Q</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list SSL detele">
                <img src="image/zuofei-bg.png" class="ph-zuofei"/>
                <span>SSL</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list GB">
                <span>GB</span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);"><i>GB-T</i><i>3524-2015</i><i><em class="key-word">工业企业</em>厂界<em class="key-word">环境</em>噪音排放标准</i></a></li>
                    <li class="text-other">
                        <a class="tidai"><em>替代标准：</em><em>GB/T</em><em>3524-2016</em></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


        </div>
        <div class="pane">
            <div class="tab-posit-cont">
                <b style="font-size:12px;color:#fff;">当前位置：</b>
                <a class="posit-b" href="javascript:vido(0);">首页</a>
                <i class="icon icon-angle-right" style="color:#fff;"></i>
                <a class="posit-b" href="javascript:vido(0);">精准查询</a>
                <i class="icon icon-angle-right" style="color:#fff;"></i>
                <a class="posit-a" href="javascript:vido(0);">法律法规查询</a>
            </div>
            <!--收缩列表start-->
            <div class="search-box">
                <div class="nLi on">
                    <h3>
                        查询条件
                        <i class="icon icon-chevron-up" id="icon"></i>
                    </h3>
                    <ul class="sub">
                        <li>
                            <label>法规编号 :</label>
                            <input   value="" class="search-1"/>
                            <label>法规名称 :</label>
                            <input   value="" class="search-2"/>
                            <label>分类 :</label>
                            <input   value="" class="search-4"/>
                        </li>
                        <li class="search-btn">
                            <button>查询</button>
                        </li>
                    </ul>
                </div>
            </div>
            <!--收缩列表end-->

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star"></i><i>已收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="search-list law">
                <span></span>
                <ul class="text">
                    <li class="title"><a href="javascript:vido(0);">官方：公考报考者如作弊不允许进入公务员队伍</a></li>
                    <li class="text-other">
                        <i class="time">2016-09-28 10:31:26</i>
                        <a class="shoucang"><i class="icon icon-star-empty"></i><i>加入收藏</i></a>
                        <a class="download"><i class="icon icon-download"></i><i>下载</i></a>
                        <i class="liulan"><i class="icon icon-eye-open"></i><i>20</i></i>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>


        </div>
        <!--<div class="pane">4</div>-->
        <!--<div class="pane">5</div>-->
    </div>

</div>
<!--头部tab选项卡内容结束-->


<!--尾部页脚开始-->
<div class="footer">
    <div class="footer-box">
        <ul class="footer-left">
            <li>COPYRIGHT·2014沪ICO备101145号-26</li>
            <li><i>技术支持：郑州市蓝元软件科技有限公司</i><i>联系电话：0371-60985700</i></li>
        </ul>
        <ul class="footer-right">
            <img src="image/saoma-2.png"/>
            <li>扫码绑定</li>
        </ul>
        <ul class="footer-right">
            <img src="image/saoma-1.png"/>
            <li>扫码下载</li>
        </ul>
        <div class="clearfix"></div>
    </div>
</div>
<!--尾部页脚结束-->
</body>
</html>