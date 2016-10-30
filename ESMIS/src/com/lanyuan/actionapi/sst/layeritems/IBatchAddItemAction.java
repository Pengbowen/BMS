package com.lanyuan.actionapi.sst.layeritems;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.actionapi.sst.FileResolve;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;
/**
 * 批量添加项目接口
 * @author PBW
 *
 */
public class IBatchAddItemAction extends ResultOperateAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceSSTLayerItems layerItemService;
	
	private String savedpath;
	
	public String execute(){
		return Action.SUCCESS;
	}
	public void batchAdd(){
		List<List<Map<String, String>>> data;
		 ServletContext sctx = ServletActionContext.getServletContext();
	     String  filepath = sctx.getRealPath(savedpath);
		//ResultMessage msg = new ResultMessage(1, "添加成功");
		//ResultMessage errorMsg = new ResultMessage(0, "添加失败");
		try {
			data = FileResolve.readExcelWithTitle(filepath);
			if(data!=null){
				List<Map<String,String>> items = data.get(0);
				for(int i=0;i<items.size();i++){
					Map<String,String> map = items.get(i);
					String belongItemId = map.get("所属父级编号");
					int index = belongItemId.indexOf(".");
					belongItemId = belongItemId.substring(0, index);
					SingleSSTLayerItems s = generateBean(belongItemId);
					s.setLayerItemName(map.get("项目名称"));
					int j = layerItemService.insert(s);
					if(j<=0){
						this.printString("0", "添加失败");
					}
				}
			}
			this.printString("1", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.printString("0", "添加失败");
		}finally{
			File file = new File(filepath);
			file.delete();
		}
	}
	
	
	/**
	 * 根据所属父级编号,生成子项目实体
	 * @return
	 */
	private SingleSSTLayerItems generateBean(String belongItemId){
		SingleSSTLayerItems s = new SingleSSTLayerItems();
		s = layerItemService.selectById(belongItemId);
		if(s==null){//父项目编号不存在
			return null;
		}
		
		//验证非法登录
		 HttpServletRequest request = ServletActionContext.getRequest();
	        LoginUser user = null;
	        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
	        {
	            if (user == null)
	            {
	                printString( "-4", "非法访问！");
	                return null;
	            }
	        }
			
			int  SSTId = s.getSSTId(); //所属体系表编号与父项目相同
			int layerNo = s.getLayerNo()+1;//所属层级 = 父项目层级+1
			int isEnabled = 1;	//可用状态默认为启用
			int isVisible = 1;	//显示状态默认为显示
			OperatorInfo operator = this.getCurrentOperator();
			String modifyer = operator.getOperator();	//修改人
			String modifyerName = operator.getOperatorName();//修改人名称
			String modifyTime = operator.getOperateTime("YYYY-MM-dd  HH:mm:ss");//修改时间
			String modifyIp = operator.getOperateIp();//修改人IP
			 //显示顺序
		       //根据层级查找本层级最大显示顺序
		    int maxDisplayOrder =   layerItemService.selectMaxDisplayOrderByLayerNo(layerNo);
			int displayOrder = maxDisplayOrder+1;
			SingleSSTLayerItems x = new SingleSSTLayerItems();
			x.setSSTId(SSTId);
			x.setLayerNo(layerNo);
			x.setDisplayOrder(displayOrder);
			x.setIsEnabled(isEnabled);
			x.setIsVisible(isVisible);
			x.setModifyer(modifyer);
			x.setModifyerName(modifyerName);
			x.setModifyTime(modifyTime);
			x.setModifyIP(modifyIp);
			
		return x;
	}
	public String getSavedpath() {
		return savedpath;
	}
	public void setSavedpath(String savedpath) {
		this.savedpath = savedpath;
	}
	
	
}