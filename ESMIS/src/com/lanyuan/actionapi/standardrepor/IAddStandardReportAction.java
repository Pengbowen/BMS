package com.lanyuan.actionapi.standardrepor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.standardrepor.ServiceStandardReport;
import com.lanyuan.assembly.standardrepor.SingleStandardReport;
/**
 * @Description: 发布生成总库报表/体系报表数据
 * @author
 * @date 2016-9-8 下午6:39:37
 */
public class IAddStandardReportAction extends ResultOperateAction
{

    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceStandardReport standardReportService;
    private String param;
    private String reportId;
    public String addStandardrepor()
    {
        if(StringUtils.isBlank(reportId)){
            this.printString("2", "报表编号为空！");
              return null ;
        }
        if(!"]".equals(param))
        {
            List<SingleStandardReport> list=JSONToEntity(param);  
            if(list!=null&&!list.isEmpty())
            {
                int a =standardReportService.insertbatch(list,reportId);
                if (a>0)
                {
                    this.printString("1", "发布生成报表成功!");
                }
                else
                {
                    this.printString("2", "发布生成报表失败!");
                }
            }
        }else{
            this.printString("2", "没有数据!");
        }
        return param; 
    }
 // 将json字符串转换成对象
    private List<SingleStandardReport> JSONToEntity(String jsonstr) {
        List<SingleStandardReport> list = new ArrayList<SingleStandardReport>();
        JSONArray arr = JSONArray.fromObject(jsonstr);// 先转化成json数组
        if (arr!=null&&!arr.isEmpty()) {
            for (int i = 0; i < arr.size(); i++) {
                JSONObject result = JSONObject.fromObject(arr.get(i).toString());// 获取数组json的字符串
                 //体系表生成报表数据解析
                if(!reportId.equals("10")&&!reportId.equals("12")){
                    Integer SSTcount=0;
                    Iterator it = result.keys();
                    while (it.hasNext()) { 
                        String key = it.next().toString(); 
                        if(!key.equals("standardCategoryName")&&!key.equals("standardCategory")&&!key.equals("code")){
                            SingleStandardReport SSTsingle = new SingleStandardReport();
                            SSTsingle.setStandardCategory(result.getString("standardCategory"));
                            SSTsingle.setStandardCategoryName(result.getString("standardCategoryName"));
                            SSTsingle.setReportId(reportId);
                            SSTsingle.setItemId(key);
                            String counts=result.getString(key);
                            SSTcount=Integer.parseInt(counts);
                            SSTsingle.setQuantity(SSTcount);
                            if(!result.getString("standardCategory").equals("合计"))
                            {
                                list.add(SSTsingle); 
                            }
                        }
                    } 
                }else
                {
                    //标准库报表保存数据解析
                    SingleStandardReport single = new SingleStandardReport();
                    Integer count=Integer.parseInt(result.getString("standardcategorycount"));
                    single.setStandardCategory(result.getString("standardcategory"));
                    single.setQuantity(count);
                    single.setReportId(reportId);
                    single.setStandardCategory(result.getString("standardcategory"));
                    String standardcategoryname =result.getString("standardcategoryname");
                    if(StringUtils.isBlank(standardcategoryname))
                    {
                    	single.setStandardCategoryName("");
                    }else
                    {
                    	single.setStandardCategoryName(result.getString("standardcategoryname"));
                    }
                    if(!result.getString("standardcategory").equals("合计"))
                    {
                        list.add(single); 
                    }
                }
            }
        }
        return list;
    }
    public String getParam()
    {
        return param;
    }
    public void setParam(String param)
    {
        this.param = param;
    }
    public String getReportId()
    {
        return reportId;
    }
    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }
}
