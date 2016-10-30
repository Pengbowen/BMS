package com.lanyuan.actionapi.commonexport;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.commonexport.EntityExport;
import com.lanyuan.assembly.commonexport.ServiceExport;
import com.lanyuan.assembly.utils.encrypt.MD5Util;
import com.lanyuan.web.LoginAuthentication.LoginUser;

public class ICommonExportAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    private String modelId;// modelId json文件唯一标识
    private String fileName;// 模板文件excel名
    private String path;// 模板文件路径
    private String tempDirectory;// 以当前日期命名的临时目录
    private String tempFileName;// 生成临时文件excel名称，
    private Integer recordCount;//传入数据总数
    private ServiceExport serviceExport = new ServiceExport();// 读写json文件并解析
    private List<HashMap<String, String>> columnTitle = null;//excel表列名
    private List<Object> fieldNameList = new ArrayList<Object>();// 存放配置文件的field
    private List<Object> alignList = new ArrayList<Object>();// 存放配置文件的field
    private List<Object> resultList = new ArrayList<Object>();// 存在param的field,单条数据存list集合
    private List<List<Object>> dataList = new ArrayList<List<Object>>();// 存储resultList集合
    private String param;//post传输数据
    private File file;//生成的临时文件
    private Integer countNum;
    HttpServletRequest request = ServletActionContext.getRequest();
    LoginUser user = null;
    OperatorInfo operator = null;
    public String commonExport() throws IOException
    {
       if (StringUtils.isBlank(modelId))
        {
            this.printString("2", "配置文件标识为空！");
            return null;
        }
       if (StringUtils.isBlank(recordCount+""))
       {
           this.printString("2", "导出数据总数为空！");
           return null;
       }
        if (param.equals("]"))
        {
            this.printString("2", "页面导出数据为空！");
            return null;
        }
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)// 如果没有sessionID则直接返回
        {
            if (user == null) {
                printString("-4", "非法访问！");
                return null;
            }
        } else {
            operator = this.getCurrentOperator();// 获取操作对象
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
            tempDirectory = df.format(new Date());
            readTemplatefiles();//读取模板配置文件
            tempFileName=MD5Util.getMD5ofStr(operator.getOperateIp()+fileName);//获取登录i和下载文件名并加密作为临时文件名
            file = new File(serviceExport.getWebRoot() + "/uploadfiles/tempReportExcel/"+ tempDirectory + "/" + tempFileName + ".xls");
            if(countNum==1)
            {
                if(file.exists())
                {
                    file.delete();
                }
            createTempFileName();//创建临时模板文件
            dataResults();//解析param,存入list结合
            writeInExcel();//向excel追加数据
            }else
            {
                dataResults();//解析param,存入list结合
                writeInExcel();//向excel追加数据
            }
        }
        return null; 
    }
    /**
     * 读取模板文件 
     */
    public void readTemplatefiles() throws IOException
    {
        List<EntityExport> list = serviceExport.dataResult(modelId);
        System.out.println(list);
        // 取配置文件中相应参数
        if (!list.isEmpty()&&list!=null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                path = list.get(i).getPath();
                fileName = list.get(i).getFileName();
                columnTitle = list.get(i).getColumnTitle();
            }
            // 将配置文件field存入list集合
            for (int i = 0; i < columnTitle.size(); i++)
            {
                String field = columnTitle.get(i).get("field");
                String align = columnTitle.get(i).get("align");
                fieldNameList.add(field);
                alignList.add(align);
            }
        }else
        {
            this.printString("2", "模板文件为空！");
            return ;
        }
    }
    /**
     * 解析前台传递的json串
     */
    private void dataResults()
    {
        // 解析param json
        try
        {
            String paramResult = java.net.URLDecoder.decode(param, "UTF-8");
            JSONArray jsonArray = JSONArray.fromObject(paramResult);
            if(!jsonArray.isEmpty()&&jsonArray!=null)
            {
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    resultList = new ArrayList<Object>();
                    // 遍历fieldNameList list集合，其中元素作为key值，从json串中取值，存入list
                    if (fieldNameList.size() > 0)
                    {
                        String result = null;
                        for (int j = 0; j < fieldNameList.size(); j++)
                        {
                            if (!jsonObject.containsKey(fieldNameList.get(j)))
                            {
                                resultList.add("");
                                continue;
                            }
                            result = jsonObject.get(fieldNameList.get(j)).toString();
                            resultList.add(result);
                        }
                        dataList.add(resultList);
                    }
                }
            }else
            {
                this.printString("2", "传递参数为空！");
                return ;
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 向临时文件中追加数据 
     */
    private void  writeInExcel()
    {
        WritableSheet wsheet = null ;
        if (file.exists() && !dataList.isEmpty()&&dataList!=null)
        {
            try
            {
                // 向创建的excel目录追加数据
                WritableCellFormat tempCellFormat = null;
                Workbook book = Workbook.getWorkbook(file);
                //  Workbook book = Workbook.getWorkbook(new File(file.toString()));
                WritableWorkbook wbook = Workbook.createWorkbook(file, book);
                wsheet = wbook.getSheet(0);
                int rsCols = wsheet.getColumns(); //列数
                int rsRows = wsheet.getRows(); //行数
                int nullCellNum;
                int afterRows = rsRows;
                for (int i = 1; i < rsRows; i++) { //统计行中为空的单元格数
                   nullCellNum = 0;
                    for (int j = 0; j < rsCols; j++) {
                        String val = wsheet.getCell(j, i).getContents();
                        val = StringUtils.trimToEmpty(val);
                        if (StringUtils.isBlank(val))
                           nullCellNum++;
                    }
                    if (nullCellNum >= rsCols) { //如果nullCellNum大于或等于总的列数
                     afterRows--;          //行数减一
                   }
                }
                Integer sheetRows = afterRows;; 
                for (int i = 0; i < dataList.size(); i++)
                {
                    for (int j = 0; j < columnTitle.size(); j++)
                    {
                        // 根据模板对齐样式，设置写入的对齐样式
                        tempCellFormat = new WritableCellFormat();
                        if (tempCellFormat != null)
                        {
                            if (alignList.get(j).equals("left"))
                            {
                                tempCellFormat.setAlignment(Alignment.LEFT);
                            }
                            if (alignList.get(j).equals("center"))
                            {
                                tempCellFormat.setAlignment(Alignment.CENTRE);
                            }
                            if (alignList.get(j).equals("right"))
                            {
                                tempCellFormat.setAlignment(Alignment.RIGHT);
                            }
                        }
                        // 数据写入
                        Integer startcolumn = Integer.parseInt(columnTitle.get(j).get("startcolumn"));
                        Label label = null;
                        if (dataList.get(i).get(j).equals("null"))
                        {
                            label = new Label(startcolumn - 1, sheetRows+i, "", tempCellFormat);
                        }
                        else
                        {
                            label = new Label(startcolumn - 1, sheetRows+i,
                                    dataList.get(i).get(j) + "", tempCellFormat);
                        }
                        wsheet.addCell(label);
                    }
                }
                wbook.write();
                wbook.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (WriteException e)
            {
                e.printStackTrace();
            }
            catch (BiffException e)
            {
                e.printStackTrace();
            }
        }
    }
    /**
     * 创建临时文件并将模板文件复制到临时文件返回临时文件路径
     */
    private void createTempFileName()
    {
        try
        {
            // 模板文件路径
            File excelModel = new File(serviceExport.getWebRoot() + path);
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdir();
            }
            file.createNewFile();
            if (file.exists() &&excelModel.exists())
            {
                FileUtils.copyFile(excelModel, file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getModelId()
    {
        return modelId;
    }

    public void setModelId(String modelId)
    {
        this.modelId = modelId;
    }

    public String getParam()
    {
        return param;
    }

    public void setParam(String param)
    {
        this.param = param;
    }
    public Integer getRecordCount()
    {
        return recordCount;
    }
    public void setRecordCount(Integer recordCount)
    {
        this.recordCount = recordCount;
    }
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
}
