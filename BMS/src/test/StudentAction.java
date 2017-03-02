package test;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 学生类,包含增删改查方法
 * @author Administrator
 *
 */
public class StudentAction extends ActionSupport{
	/**
	 * 配置视图名称,用于返回
	 */
	private String viewName;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 默认执行方法
	 */
	public String execute (){
		return SUCCESS;
	}
	
	/**
	 * 增加方法
	 * @return
	 */
	public String add (){
		return SUCCESS;
	}
	/**
	 * 删除方法
	 * @return
	 */
	public String delete (){
		return SUCCESS;
	}
	/**
	 * 跳转方法
	 * @return
	 */
	public String skip(){
		viewName = "success";
		return SUCCESS;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
