package com.lanyuan.actionapi.lawslibrary;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.basic.commonmethods.OperatorInfo;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.lanyuan.web.LoginAuthentication.LoginUser;
import com.opensymphony.xwork2.Action;

/**
 * @Description: 修改法律法规实现接口
 * @author
 * @date 2016-8-25 下午2:00:07
 */
public class IModifyLawsAction extends ResultOperateAction
{
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServiceLaws lawsService;
    /**
     * 法规id
     */
    private String lawsId;
    /**
     * 法规编号
     */
    private String lawsNo;
    /**
     * 法规名称
     */
    private String lawsName;
    /**
     * 法规类别
     */
    private String lawsType;
    /**
     * 关键字
     */
    private String keyWords;
    /**
     * 说明
     */
    private String explaintd;
    /**
     * 备注
     */
    private String remark;
    /**
     * 文件存放位置
     */
    private String filePath;
    /**
     * 批准单位
     */
    private String approvedUnit;
    /**
     * 批准时间
     */
    private String approvedDate;
    /**
     * 实施时间
     */
    private String effectiveDate;
    /**
     * 标准提出部门
     */
    private String proposedUnit;
    /**
     * 标准起草部门
     */
    private String draftedUnit;
    /**
     * 主要起草人
     */
    private String majorDrafters;
    /**
     * 内容
     */
    private String content;
    public String  modifyLaws()
    {
        if (StringUtils.isBlank(lawsNo))
        {
            this.printString("2", "法规编号为空！");
            return Action.SUCCESS;
        }
        if (StringUtils.isBlank(lawsName))
        {
            this.printString("2", "法规名称为空！");
            return Action.SUCCESS;
        }
        if (StringUtils.isBlank(lawsType + ""))
        {
            this.printString("2", "法规类别为空！");
            return Action.SUCCESS;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        LoginUser user = null;
        if (request.getSession().getAttribute(LoginUser.SESSIONID) == null)
        {
            if (user == null)
            {
                this.printString( "-4", "非法访问！");
                return Action.SUCCESS;
            }
        }
        SingleLaws singleLaws = new SingleLaws();
        // 获取当前操作人信息
        OperatorInfo operater = this.getCurrentOperator();
        /* 最后修改人 */
        String modifyer = operater.getOperator();
        /* 最后修改人姓名 */
        String modifyerName = operater.getOperatorName();
        /* 最后修改时间*/
        String modifyTime = operater.getOperateTime("yyyy-MM-dd HH:mm:ss");
        /* 最后修改IP */
        String modifyIP = operater.getOperateIp();
        
        String standardCategoryName = "";
        
        singleLaws.setStandardId(lawsId);
        singleLaws.setStandardName(lawsName);
        singleLaws.setStandardNo(lawsNo);
        singleLaws.setSubjectWords(keyWords);
        singleLaws.setStandardCategory(lawsType);
        switch (Integer.valueOf(lawsType))
        {
        case 1:
            standardCategoryName = "贯彻执行法规";
            break;
        case 2:
            standardCategoryName = "适用执行法规";
            break;
        }
        singleLaws.setStandardCategoryName(standardCategoryName);
        singleLaws.setRemark(remark);
        singleLaws.setExplaintd(explaintd);
        singleLaws.setApprovedUnit(approvedUnit);
        singleLaws.setApprovedDate(approvedDate);
        singleLaws.setEffectiveDate(effectiveDate);
        singleLaws.setProposedUnit(proposedUnit);
        singleLaws.setDraftedUnit(draftedUnit);
        singleLaws.setMajorDrafters(majorDrafters);
        singleLaws.setContent(content);
        singleLaws.setDocumentType("2");
        singleLaws.setModifyer(modifyer);
        singleLaws.setModifyerName(modifyerName);
        singleLaws.setModifyTime(modifyTime);
        singleLaws.setModifyIP(modifyIP);
        int data = lawsService.update(singleLaws);
        if (data > 0)
        {
            this.printString("1", "修改成功！");
            return Action.SUCCESS;
        }
        else
        {
            this.printString("2", "修改失败！");
            return Action.SUCCESS;
        }
    }
    
    public String cancellation(){
        
        if (StringUtils.isBlank(lawsId))
        {
            this.printString("2", "法规id为空！");
            return Action.SUCCESS;
        }
        int data = lawsService.cancellation(lawsId);
        if (data > 0)
        {
            this.printString("1", "作废成功！");
            return Action.SUCCESS;
        }
        else
        {
            this.printString("2", "作废失败！");
            return Action.SUCCESS;
        }
    }
    

    public String getLawsNo()
    {
        return lawsNo;
    }

    public void setLawsNo(String lawsNo)
    {
        this.lawsNo = lawsNo;
    }

    public String getLawsName()
    {
        return lawsName;
    }

    public void setLawsName(String lawsName)
    {
        this.lawsName = lawsName;
    }


    public String getKeyWords()
    {
        return keyWords;
    }

    public void setKeyWords(String keyWords)
    {
        this.keyWords = keyWords;
    }


    /**
     * @return 返回 lawsType
     */
    public String getLawsType()
    {
        return lawsType;
    }

    /**
     * @return 返回 explaintd
     */
    public String getExplaintd()
    {
        return explaintd;
    }

    /**
     * @return 返回 approvedUnit
     */
    public String getApprovedUnit()
    {
        return approvedUnit;
    }

    /**
     * @return 返回 approvedDate
     */
    public String getApprovedDate()
    {
        return approvedDate;
    }

    /**
     * @return 返回 effectiveDate
     */
    public String getEffectiveDate()
    {
        return effectiveDate;
    }

    /**
     * @return 返回 proposedUnit
     */
    public String getProposedUnit()
    {
        return proposedUnit;
    }

    /**
     * @return 返回 draftedUnit
     */
    public String getDraftedUnit()
    {
        return draftedUnit;
    }

    /**
     * @return 返回 majorDrafters
     */
    public String getMajorDrafters()
    {
        return majorDrafters;
    }

    /**
     * @return 返回 content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param lawsType 设置
     */
    public void setLawsType(String lawsType)
    {
        this.lawsType = lawsType;
    }

    /**
     * @param explaintd 设置
     */
    public void setExplaintd(String explaintd)
    {
        this.explaintd = explaintd;
    }

    /**
     * @param approvedUnit 设置
     */
    public void setApprovedUnit(String approvedUnit)
    {
        this.approvedUnit = approvedUnit;
    }

    /**
     * @param approvedDate 设置
     */
    public void setApprovedDate(String approvedDate)
    {
        this.approvedDate = approvedDate;
    }

    /**
     * @param effectiveDate 设置
     */
    public void setEffectiveDate(String effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @param proposedUnit 设置
     */
    public void setProposedUnit(String proposedUnit)
    {
        this.proposedUnit = proposedUnit;
    }

    /**
     * @param draftedUnit 设置
     */
    public void setDraftedUnit(String draftedUnit)
    {
        this.draftedUnit = draftedUnit;
    }

    /**
     * @param majorDrafters 设置
     */
    public void setMajorDrafters(String majorDrafters)
    {
        this.majorDrafters = majorDrafters;
    }

    /**
     * @param content 设置
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    public String getLawsId()
    {
        return lawsId;
    }

    public void setLawsId(String lawsId)
    {
        this.lawsId = lawsId;
    }
}
