package com.lanyuan.actionapi.commonexport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.commonexport.EntityExport;
import com.lanyuan.assembly.commonexport.ServiceExport;
import com.lanyuan.assembly.utils.encrypt.MD5Util;
import com.lanyuan.web.LoginAuthentication.LoginUser;

public class IExportDownLoadAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    private ServiceExport serviceExport = new ServiceExport();// 读写json文件并解析
    private String modelId;// modelId json文件唯一标识
    private String fileName;// 模板文件excel名
    private String tempDirectory;// 以当前日期命名的临时目录
    private String tempFileName;// 生成临时文件excel名称，
    HttpServletRequest request = ServletActionContext.getRequest();
    LoginUser user = null;
    OperatorInfo operator = null;
    public String exportDownload() throws IOException
    {
        if (StringUtils.isBlank(modelId))
        {
            this.printString("2", "配置文件标识为空！");
            return null;
        }
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)// 如果没有sessionID则直接返回
        {
            if (user == null)
            {
                printString("-4", "非法访问！");
                return null;
            }
        }
        else
        {
            operator = this.getCurrentOperator();// 获取操作对象
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
            tempDirectory = df.format(new Date());
            boolean flag=readTemplatefiles();// 读取模板配置文件
            if(flag==true)
            {
                tempFileName = MD5Util.getMD5ofStr(operator.getOperateIp() + fileName);// 获取登录i和下载文件名并加密作为临时文件名
                File file = new File(serviceExport.getWebRoot() + "/uploadfiles/tempReportExcel/"+ tempDirectory + "/" + tempFileName + ".xls");
                if (file.exists())
                {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    HttpServletResponse response = ServletActionContext.getResponse();
                    try
                    {
                        request.setCharacterEncoding("utf-8");
                        response.setContentType("text/html;charset=utf-8");
                        response.setHeader("Content-Disposition", "inline;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8") + ".xls");
                        request.getRequestDispatcher("/uploadfiles/tempReportExcel/" + tempDirectory+ "/" + tempFileName + ".xls").forward(request,response);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    this.printString("2", "模板文件为空！");
                    return null;
                }
            }else
            {
                this.printString("2", "");
                
            }
        }
        return null;
    }
    /**
     * 读取模板文件 
     */
    public boolean readTemplatefiles() throws IOException
    {
        boolean flag=false;
        List<EntityExport> list = serviceExport.dataResult(modelId);
        // 取配置文件中相应参数
        if (!list.isEmpty() && list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                fileName = list.get(i).getFileName();
            }
            flag=true;
        }
        else
        {
            this.printString("2", "模板文件为空！");
            flag=false;
        }
        return flag;
    }
  public String getModelId()
    {
        return modelId;
    }
    public void setModelId(String modelId)
    {
        this.modelId = modelId;
    }
}
