
package com.lanyuan.actionapi.basic.baseclasses.mybatis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultBaseAction;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.ConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.JsonConvertConditionGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.JsonConvertOrderGroup;
import com.lanyuan.assembly.basic.sqlstring.mybatis.conditionresolver.OrderGroup;
import com.lanyuan.assembly.utils.file.FileOpt;
import com.lanyuan.assembly.utils.string.JsonUtils;
import com.lanyuan.assembly.view.WebSite;

/**
 * 带查询json字符串的查询父类
 * 
 * @author ly-two
 * @author qinye
 * @date 2014-12-15 上午11:02:41
 */
public class ResultSearchAction extends ResultBaseAction
{
    private static final long serialVersionUID = 1L;
    /**
     * 生成查询结果的静态文件，文件名带着后缀
     */
    private String fileName;
    /**
     * 查询条件的json字符串
     */
    private String conditionString;
    /**
     * 排序 条件的json字符串
     */
    private String orderString;
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页多少行
     */
    private int linesOfPage;
    /**
     * response对象
     */
    private HttpServletResponse response = ServletActionContext.getResponse();

    /**
     * @param 查询条件的json字符串 设置
     */
    public void setConditionString(String conditionString)
    {
        this.conditionString = conditionString;
    }

    /**
     * @param 排序 条件的json字符串 设置
     */
    public void setOrderString(String orderString)
    {
        this.orderString = orderString;
    }

    /**
     * @return 返回 当前页码
     */
    protected int getCurrentPage()
    {
        return currentPage <= 0 ? 1 : currentPage;
    }

    /**
     * @param 当前页码 设置
     */
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    /**
     * @return 返回 每页多少行
     */
    protected int getLinesOfPage()
    {
        return linesOfPage <= 0 ? 10 : linesOfPage;
    }

    /**
     * @param 每页多少行 设置
     */
    public void setLinesOfPage(int linesOfPage)
    {
        this.linesOfPage = linesOfPage;
    }

    /**
     * 查询接口的分页标记<br/>
     * 
     * @return 返回 true是分页查询，返回 false不分页查询
     */
    public boolean isBreakPage()
    {
        if (this.currentPage == -1 && this.linesOfPage == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 通过查询json字符串，获取 ConditionGroup对象
     */
    protected ConditionGroup getConditionGroupObject()
    {
        String json = this.conditionString;
        if (null != json)
        {
            if (json.trim().equals("")) json = null;
        }
        if (null == json) return null;

        JsonConvertConditionGroup temp = null;
        temp = new JsonConvertConditionGroup(json);

        return temp.getCondition();
    }

    /**
     * 通过排序json字符串，获取 OrderGroup对象
     */
    protected OrderGroup getOrderGroupObject()
    {
        String json = this.orderString;
        if (null != json)
        {
            if (json.trim().equals("")) json = null;
        }
        if (null == json) return null;

        JsonConvertOrderGroup temp = null;
        temp = new JsonConvertOrderGroup(json);

        return temp.getOrderList();
    }

    /**
     * 输出查询接口的json格式结果
     * 
     * @param datalist 查询的数据集合
     * @param recordCount 记录总数
     * @param result 查询状态标记，1成功，非1失败
     * @param message 查询状态的说明信息
     */
    public void printString(List<HashMap<String, String>> datalist, int recordCount, String result,
            String message)
    {
        HashMap<String, Object> resultList = new HashMap<String, Object>();

        if (datalist == null || datalist.isEmpty())
        {
            resultList.put("datalist", "");// 数据
        }
        else
        {
            resultList.put("datalist", datalist);// 数据
        }

        int totalPages = 0;
        if (recordCount % this.getLinesOfPage() == 0)
        {
            totalPages = recordCount / this.getLinesOfPage();
        }
        else
        {
            totalPages = recordCount / this.getLinesOfPage() + 1;
        }

        resultList.put("currentPage", this.getCurrentPage());// 当前页
        resultList.put("linesOfPage", this.getLinesOfPage());// 每页行数
        resultList.put("pageCount", totalPages);// 页数
        resultList.put("recordCount", recordCount);// 记录数
        resultList.put("result", result == null ? "" : result);
        resultList.put("message", message == null ? "" : message);

        String str = JsonUtils.objectToJsonStr(resultList);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try
        {
            if (fileName != null && fileName.length() >= 40)
            {
                String name = WebSite.getWebSitePath();
                FileOpt.writeFile(name + fileName, str);// 字符集UTF-8
            }

            this.response.getWriter().write(str);
            this.response.getWriter().close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

}
