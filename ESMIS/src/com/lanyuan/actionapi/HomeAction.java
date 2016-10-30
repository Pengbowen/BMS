package com.lanyuan.actionapi;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup.enumOperator;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionNormal;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.blogroll.ServiceBlogroll;
import com.lanyuan.assembly.blogroll.SingleBlogroll;
import com.lanyuan.assembly.notice.ServiceNotice;
import com.lanyuan.assembly.notice.SingleNotice;
import com.lanyuan.assembly.standardlibrary.ServiceStandardLibrary;
import com.lanyuan.assembly.standardlibrary.SingleStandardLibraryData;
import com.lanyuan.assembly.util.CookiesUtil;
import com.lanyuan.assembly.util.WebSitePro;
import com.opensymphony.xwork2.Action;

/**
 * 企业标准化管理首页
 * @author ly-two
 * @date 2016-9-18 下午6:10:13
 */
public class HomeAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceNotice noticeService;
    @Autowired
    private ServiceBlogroll blogrollService;
    @Autowired
    private ServiceStandardLibrary standardLibraryService;

    // 通知公告
    private List<SingleNotice> noticeList;
    // 友情链接
    private List<SingleBlogroll> blogrollList;
    // 热门标准:按浏览量、入库时间、发布时间倒序
    private List<SingleStandardLibraryData> hotStandardLibraryList;
    // 最新标准:实施时间大于当前时间升序提取
    private List<SingleStandardLibraryData> newStandardLibraryList;
    // 访客数量
    private int visiterCount = 0;
    // 已选专业名称
    private String[] professionalNames;
    // 已选专业名称，非数组显示
    private String professionalName;

    /**
     * 网站首页
     */
    public String execute()
    {
        // 通知公告
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        ConditionGroup cond = new ConditionGroup();
        OrderGroup order = new OrderGroup();
        order.Add(ServiceNotice.MappingList.deadline.name(), false);
        cond.addWithAnd(new ConditionNormal(ServiceNotice.MappingList.deadline.name(),
                formatter.format(currentTime), enumOperator.MoreAndEqual));
        noticeList = noticeService.selectList(cond, order);

        // 友情链接
        blogrollList = blogrollService.selectByConditionAndPage(1, 5, null, null);

        // 最新标准:实施时间大于当前时间升序提取
        cond.clear();
        cond.addWithAnd(new ConditionNormal(ServiceStandardLibrary.MappingList.effectiveDate.name(),
                                            formatter.format(currentTime), enumOperator.MoreAndEqual));
        order.Clear();
        order.Add(ServiceStandardLibrary.MappingList.effectiveDate.name(), true);
        newStandardLibraryList = standardLibraryService.selectPageList(1, 4, cond, order);
        
        // 热门标准(按浏览量、入库时间、发布时间倒序)
        order.Clear();
        order.Add(ServiceStandardLibrary.MappingList.browseVolume.name(), false);
        order.Add(ServiceStandardLibrary.MappingList.createTime.name(), false);
        order.Add(ServiceStandardLibrary.MappingList.approvedDate.name(), false);
        hotStandardLibraryList = standardLibraryService.selectPageList(1, 4, null, order);

        // 访客
        visiterCount = WebSitePro.get_visiterCount();
        WebSitePro.updateProperties(WebSitePro.getWebRoot() + WebSitePro.fileName, "visiterCount",
                                    ++visiterCount + "");

        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie professionalNameCookie = CookiesUtil.getCookieByName(request, "professionalName");
        if (professionalNameCookie != null)
        {
            professionalName = professionalNameCookie.getValue();
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
            }
        }
        return Action.SUCCESS;
    }

    public List<SingleNotice> getNoticeList()
    {
        return noticeList;
    }

    public List<SingleBlogroll> getBlogrollList()
    {
        return blogrollList;
    }

    public List<SingleStandardLibraryData> getNewStandardLibraryList()
    {
        return newStandardLibraryList;
    }

    public int getVisiterCount()
    {
        return visiterCount;
    }

    public String[] getProfessionalNames()
    {
        return professionalNames;
    }

    public void setProfessionalNames(String[] professionalNames)
    {
        this.professionalNames = professionalNames;
    }

    public List<SingleStandardLibraryData> getHotStandardLibraryList()
    {
        return hotStandardLibraryList;
    }

    public String getProfessionalName()
    {
        return professionalName;
    }

    public void setProfessionalName(String professionalName)
    {
        this.professionalName = professionalName;
    }
}
