package com.lanyuan.assembly.notice;


public class EntityNotice{

    /**
     * 通知公告id
     */
    private Integer id;
    /**
     * 通知公告标题
     */
    private String noticeTitle;
    /**
     * 通知公告内容
     */
    private String noticeContent;
    /**
     * 显示顺序
     */
    private Integer showNum;
    /**
     * 截止时间
     */
    private String deadline;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 发布姓名
     */
    private String publisherrName;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 发布IP
     */
    private String publishIP;
    /**
     * 最后修改人
     */
    private String modifyer;
    /**
     * 最后修改人姓名
     */
    private String modifyerName;
    /**
     * 最后修改时间
     */
    private String modifyTime;
    /**
     * 最后修改IP
     */
    private String modifyIP;

    /**
     *  返回  通知公告id
     */
    public Integer getId(){
        return id;
    }

    /**
     *  设置  通知公告id
     */
    public void setId(Integer id){
        this.id=id;
    }


    /**
     *  返回  通知公告标题
     */
    public String getNoticeTitle(){
        return noticeTitle;
    }

    /**
     *  设置  通知公告标题
     */
    public void setNoticeTitle(String noticeTitle){
        this.noticeTitle=noticeTitle;
    }

    /**
     *  返回  通知公告内容
     */

     public String getNoticeContent()
        {
            return noticeContent;
        }

    /**
     *  设置  通知公告内容
     */
      public void setNoticeContent(String noticeContent)
        {
            this.noticeContent = noticeContent;
        }
    /**
     *  返回  显示顺序
     */
    public Integer getShowNum(){
        return showNum;
    }

    /**
     *  设置  显示顺序
     */
    public void setShowNum(Integer showNum){
        this.showNum=showNum;
    }


    /**
     *  返回  截止时间
     */
    public String getDeadline(){
        return deadline;
    }

    /**
     *  设置  截止时间
     */
    public void setDeadline(String deadline){
        this.deadline=deadline;
    }


    /**
     *  返回  发布人
     */
    public String getPublisher(){
        return publisher;
    }

    /**
     *  设置  发布人
     */
    public void setPublisher(String publisher){
        this.publisher=publisher;
    }


    /**
     *  返回  发布姓名
     */
    public String getPublisherrName(){
        return publisherrName;
    }

    /**
     *  设置  发布姓名
     */
    public void setPublisherrName(String publisherrName){
        this.publisherrName=publisherrName;
    }


    /**
     *  返回  发布时间
     */
    public String getPublishTime(){
        return publishTime;
    }

    /**
     *  设置  发布时间
     */
    public void setPublishTime(String publishTime){
        this.publishTime=publishTime;
    }


    /**
     *  返回  发布IP
     */
    public String getPublishIP(){
        return publishIP;
    }

    /**
     *  设置  发布IP
     */
    public void setPublishIP(String publishIP){
        this.publishIP=publishIP;
    }


    /**
     *  返回  最后修改人
     */
    public String getModifyer(){
        return modifyer;
    }

    /**
     *  设置  最后修改人
     */
    public void setModifyer(String modifyer){
        this.modifyer=modifyer;
    }


    /**
     *  返回  最后修改人姓名
     */
    public String getModifyerName(){
        return modifyerName;
    }

    /**
     *  设置  最后修改人姓名
     */
    public void setModifyerName(String modifyerName){
        this.modifyerName=modifyerName;
    }


    /**
     *  返回  最后修改时间
     */
    public String getModifyTime(){
        return modifyTime;
    }

    /**
     *  设置  最后修改时间
     */
    public void setModifyTime(String modifyTime){
        this.modifyTime=modifyTime;
    }


    /**
     *  返回  最后修改IP
     */
    public String getModifyIP(){
        return modifyIP;
    }

    /**
     *  设置  最后修改IP
     */
    public void setModifyIP(String modifyIP){
        this.modifyIP=modifyIP;
    }

    public EntityNotice(){
       super();
    }
}
