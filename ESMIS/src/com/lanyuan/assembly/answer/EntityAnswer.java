package com.lanyuan.assembly.answer;

class EntityAnswer{

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 意见类型
	 */
	private Integer submitType;
	/**
	 * 提交标题
	 */
	private String submitTitle;
	/**
	 * 提交内容
	 */
	private String submitContent;
	/**
	 * 文件存放位置
	 */
	private String filePath;
	/**
	 * 提交人ID
	 */
	private String submitID;
	/**
	 * 提交人姓名
	 */
	private String submitName;
	/**
	 * 提交时间
	 */
	private String submitTime;
	/**
	 * 提交IP
	 */
	private String submitIP;
	/**
	 * 提交人手机号
	 */
	private String submitorMobile;
	/**
	 * 提交人邮箱
	 */
	private String submitorEmail;
	/**
	 * 是否回复
	 */
	private Integer isreceive;
	/**
	 * 回复人ID
	 */
	private String receiveID;
	/**
	 * 回复人姓名
	 */
	private String receiveName;
	/**
	 * 回复时间
	 */
	private String receiveTime;
	/**
	 * 回复人IP
	 */
	private String receiveIP;
	/**
	 * 回复内容
	 */
	private String receiveContent;


	/**
	 *  返回  编号
	 */
	public Integer getId(){
		return id;
	}

	/**
	 *  设置  编号
	 */
	public void setId(Integer id){
		this.id=id;
	}


	/**
	 *  返回  意见类型
	 */
	public Integer getSubmitType(){
		return submitType;
	}

	/**
	 *  设置  意见类型
	 */
	public void setSubmitType(Integer submitType){
		this.submitType=submitType;
	}


	/**
	 *  返回  提交标题
	 */
	public String getSubmitTitle(){
		return submitTitle;
	}

	/**
	 *  设置  提交标题
	 */
	public void setSubmitTitle(String submitTitle){
		this.submitTitle=submitTitle;
	}

	/**
	 *  返回  文件存放位置
	 */
	public String getFilePath(){
		return filePath;
	}

	/**
	 *  设置  文件存放位置
	 */
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}


	/**
	 *  返回  提交人ID
	 */
	public String getSubmitID(){
		return submitID;
	}

	/**
	 *  设置  提交人ID
	 */
	public void setSubmitID(String submitID){
		this.submitID=submitID;
	}


	/**
	 *  返回  提交人姓名
	 */
	public String getSubmitName(){
		return submitName;
	}

	/**
	 *  设置  提交人姓名
	 */
	public void setSubmitName(String submitName){
		this.submitName=submitName;
	}


	/**
	 *  返回  提交时间
	 */
	public String getSubmitTime(){
		return submitTime;
	}

	/**
	 *  设置  提交时间
	 */
	public void setSubmitTime(String submitTime){
		this.submitTime=submitTime;
	}


	/**
	 *  返回  提交IP
	 */
	public String getSubmitIP(){
		return submitIP;
	}

	/**
	 *  设置  提交IP
	 */
	public void setSubmitIP(String submitIP){
		this.submitIP=submitIP;
	}


	/**
	 *  返回  提交人手机号
	 */
	public String getSubmitorMobile(){
		return submitorMobile;
	}

	/**
	 *  设置  提交人手机号
	 */
	public void setSubmitorMobile(String submitorMobile){
		this.submitorMobile=submitorMobile;
	}


	/**
	 *  返回  提交人邮箱
	 */
	public String getSubmitorEmail(){
		return submitorEmail;
	}

	/**
	 *  设置  提交人邮箱
	 */
	public void setSubmitorEmail(String submitorEmail){
		this.submitorEmail=submitorEmail;
	}


	/**
	 *  返回  是否回复
	 */
	public Integer getIsreceive(){
		return isreceive;
	}

	/**
	 *  设置  是否回复
	 */
	public void setIsreceive(Integer isreceive){
		this.isreceive=isreceive;
	}


	/**
	 *  返回  回复人姓名
	 */
	public String getReceiveName(){
		return receiveName;
	}

	/**
	 *  设置  回复人姓名
	 */
	public void setReceiveName(String receiveName){
		this.receiveName=receiveName;
	}


	/**
	 *  返回  回复时间
	 */
	public String getReceiveTime(){
		return receiveTime;
	}

	/**
     * @return 返回 receiveID
     */
    public String getReceiveID()
    {
        return receiveID;
    }

    /**
     * @param receiveID 设置
     */
    public void setReceiveID(String receiveID)
    {
        this.receiveID = receiveID;
    }

    /**
	 *  设置  回复时间
	 */
	public void setReceiveTime(String receiveTime){
		this.receiveTime=receiveTime;
	}


	/**
	 *  返回  回复人IP
	 */
	public String getReceiveIP(){
		return receiveIP;
	}

	/**
	 *  设置  回复人IP
	 */
	public void setReceiveIP(String receiveIP){
		this.receiveIP=receiveIP;
	}

    /**
     * @return 返回 submitContent
     */
    public String getSubmitContent()
    {
        return submitContent;
    }

    /**
     * @return 返回 receiveContent
     */
    public String getReceiveContent()
    {
        return receiveContent;
    }

    /**
     * @param submitContent 设置
     */
    public void setSubmitContent(String submitContent)
    {
        this.submitContent = submitContent;
    }

    /**
     * @param receiveContent 设置
     */
    public void setReceiveContent(String receiveContent)
    {
        this.receiveContent = receiveContent;
    }
}
