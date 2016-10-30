package com.lanyuan.actionapi.standardrepor;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class IShowStandardReportAction extends ActionSupport
{
    /**
     * 通用跳转列表页面
     */
    private static final long serialVersionUID = 1L;
    /**
	 * 报表编号
	 */
	private String reportId;
	/**
	 * 标准类别
	 */
	private String standardCategory;
	/**
	 * 显示顺序
	 */
	private Integer displayOrder;
	/**
	 * 项目编号
	 */
	private String itemId;
	/**
	 * 数量
	 */
	private Integer quantity;

    public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(String standardCategory) {
		this.standardCategory = standardCategory;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	//显示页面
    public String execute()
    {
        return Action.SUCCESS;
    }
}
