package com.lanyuan.actionapi.lawslibrary;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.opensymphony.xwork2.Action;

/**
 * 查看法律法规详情
 * @author PBW
 *
 */
public class ICheckLawsDetailAction extends ResultOperateAction{
    private static final long serialVersionUID = 1L;
    //法律法规ID
	private  String lawsId;
	//标题
	private String title;
	//内容
	private String content;
	//浏览量
	private Integer browseVolume;
	//创建时间
	private String createTime;
	
	private String lawsType;
	
	private String dataSource;
	@Autowired
	private ServiceLaws lawsService;
	
	public String execute(){
	    if(lawsId == null){
	        return Action.ERROR;
	    }
		SingleLaws single =  lawsService.selectById(lawsId);
		lawsService.updateBrowseVolume(lawsId);
		//如果没有则转到错误页面
        if(single==null){
            return Action.ERROR;
        }
		
		title =single.getStandardName();
		content = single.getExplaintd();
		browseVolume = single.getBrowseVolume();
		createTime = single.getCreateTime();
		lawsType = single.getStandardCategory();
		dataSource = single.getDataSource()+"";
		return Action.SUCCESS;
	}	

	public String getLawsId() {
		return lawsId;
	}

	public void setLawsId(String lawsId) {
		this.lawsId = lawsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBrowseVolume() {
		return browseVolume;
	}

	public void setBrowseVolume(Integer browseVolume) {
		this.browseVolume = browseVolume;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLawsType() {
		return lawsType;
	}

	public void setLawsType(String lawsType) {
		this.lawsType = lawsType;
	}

    /**
     * @return 返回 dataSource
     */
    public String getDataSource()
    {
        return dataSource;
    }

    /**
     * @param dataSource 设置
     */
    public void setDataSource(String dataSource)
    {
        this.dataSource = dataSource;
    }
	
}
