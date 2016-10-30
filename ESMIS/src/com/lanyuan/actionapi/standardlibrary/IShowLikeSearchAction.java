package com.lanyuan.actionapi.standardlibrary;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class IShowLikeSearchAction extends ActionSupport
{
    /**
     * 跳转智能查询页面
     */
    private static final long serialVersionUID = 1L;
    /**
     * 专业
     */
    private String applicableMajor;
    /**
     * 内容
     */
    private String likeSearch;
  

	//显示页面
    public String execute()
    {
    	if(!StringUtils.isBlank(likeSearch)){
            try {
            	likeSearch = URLDecoder.decode(likeSearch,"UTF-8");
            	System.out.println("----"+likeSearch);
            } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
            }
          }
    	if(!StringUtils.isBlank(applicableMajor)){
            try {
            	applicableMajor = URLDecoder.decode(applicableMajor,"UTF-8");
            } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
            }
          }
        return Action.SUCCESS;
    }
    public String getApplicableMajor() {
  		return applicableMajor;
  	}
  	public void setApplicableMajor(String applicableMajor) {
  		this.applicableMajor = applicableMajor;
  	}
	public String getLikeSearch() {
		return likeSearch;
	}
	public void setLikeSearch(String likeSearch) {
		this.likeSearch = likeSearch;
	}
}
