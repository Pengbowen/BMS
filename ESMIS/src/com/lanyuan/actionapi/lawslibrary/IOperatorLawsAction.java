package com.lanyuan.actionapi.lawslibrary;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.actionapi.basic.baseclasses.ResultOperateAction;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.opensymphony.xwork2.Action;

/**
 * 
 * @Description: 手动新增，修改法规的操作接口
 * @author
 * @date 2016-8-25 下午2:31:13
 */
public class IOperatorLawsAction extends ResultOperateAction
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
     * 数据来源
     */
    private Integer dataSource;
    /**
     * 文件存放位置
     */
    private String filePath;
    /**
     * 浏览量
     */
    private Integer browseVolume;
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
    
    public String operatorLaws() 
    {
        if (!StringUtils.isBlank(lawsId))
        {
            SingleLaws singleLaws = new SingleLaws();
            singleLaws = lawsService.selectById(lawsId);
            if (singleLaws != null)
            {
                setInfoData(singleLaws);
            }
        }
        return Action.SUCCESS;
    }
    public void setInfoData(SingleLaws single)
    {
        this.lawsId = single.getStandardId();
        this.lawsNo = single.getStandardNo();
        this.lawsName = single.getStandardName();
        this.keyWords = single.getSubjectWords();
        this.explaintd = single.getExplaintd();
        this.remark = single.getRemark();
        this.lawsType=single.getStandardCategory();
        this.dataSource = single.getDataSource();
        this.browseVolume = single.getBrowseVolume();
        this.approvedUnit = single.getApprovedUnit();
        this.approvedDate = single.getApprovedDate();
        this.effectiveDate = single.getEffectiveDate();
        this.proposedUnit = single.getProposedUnit();
        this.draftedUnit = single.getDraftedUnit();
        this.majorDrafters = single.getMajorDrafters();
        this.content = single.getContent();
    }

    public String getLawsId()
    {
        return lawsId;
    }

    public void setLawsId(String lawsId)
    {
        this.lawsId = lawsId;
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

    public String getLawsType()
    {
        return lawsType;
    }

    public void setLawsType(String lawsType)
    {
        this.lawsType = lawsType;
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
     * @return 返回 explaintd
     */
    public String getExplaintd()
    {
        return explaintd;
    }
    /**
     * @param explaintd 设置
     */
    public void setExplaintd(String explaintd)
    {
        this.explaintd = explaintd;
    }
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(Integer dataSource)
    {
        this.dataSource = dataSource;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public Integer getBrowseVolume()
    {
        return browseVolume;
    }

    public void setBrowseVolume(Integer browseVolume)
    {
        this.browseVolume = browseVolume;
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
}
