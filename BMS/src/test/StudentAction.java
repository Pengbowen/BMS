package test;

import com.opensymphony.xwork2.ActionSupport;
/**
 * ѧ����,������ɾ�Ĳ鷽��
 * @author Administrator
 *
 */
public class StudentAction extends ActionSupport{
	/**
	 * ������ͼ����,���ڷ���
	 */
	private String viewName;
	/**
	 * ����
	 */
	private String name;
	/**
	 * Ĭ��ִ�з���
	 */
	public String execute (){
		return SUCCESS;
	}
	
	/**
	 * ���ӷ���
	 * @return
	 */
	public String add (){
		return SUCCESS;
	}
	/**
	 * ɾ������
	 * @return
	 */
	public String delete (){
		return SUCCESS;
	}
	/**
	 * ��ת����
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
