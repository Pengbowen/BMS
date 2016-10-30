package com.lanyuan.actionapi.lawslibrary;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.lanyuan.assembly.lawslibrary.ServiceLaws;
import com.lanyuan.assembly.lawslibrary.SingleLaws;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description: 显示法律法规详情
 * @author
 * @date 2016-8-25 下午2:00:07
 */
public class IShowLawsDetailAction extends ActionSupport
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
    private String explains;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 文件存放位置
     */
    private String filePath;
    /**
     * 浏览量
     */
    private Integer browseVolume;
    /**
     * 升级版本号
     */
    private String upgradeVersion;
    /**
     * 升级用户
     */
    private String upgradeUser;
    /**
     * 升级时间
     */
    private String upgradeDate;
    /**
     * 旧Id
     */
    private String oldLawsId;
    /**
     * 旧法规编号
     */
    private String oldLawsNo;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建IP
     */
    private String createIP;


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
    
    
    public String showLawsDetail()
    {
        if (!StringUtils.isBlank(lawsNo) || !StringUtils.isBlank(lawsId))
        {
            SingleLaws singleLaws = new SingleLaws();
            if (!StringUtils.isBlank(lawsId))
            {
                singleLaws = lawsService.selectById(lawsId);
            }
            if (singleLaws != null)
            {
                setInfoData(singleLaws);
                browseVolume=singleLaws.getBrowseVolume();
                singleLaws.setBrowseVolume(browseVolume+1);
                lawsService.update(singleLaws);
            }
        }
        return Action.SUCCESS;
    }
    public void setInfoData(SingleLaws single)
    {
       Integer lawsTypeNo = Integer.valueOf(single.getStandardCategory());
       String lawsTypeName=null;
        if(lawsTypeNo==1)
        {
           lawsTypeName="企业贯彻法规";
        }
        if(lawsTypeNo==2)
        {
           lawsTypeName="企业适用法规";
        }
        Integer dataSourceNo=single.getDataSource();
        String dataSourceName=null;
        if(dataSourceNo==0)
        {
            dataSourceName="升级入库";
        }
        if(dataSourceNo==1)
        {
            dataSourceName="手工添加";
        }
        this.lawsNo = single.getStandardNo();
        this.lawsName = single.getStandardName();
        this.lawsType = lawsTypeName;
        this.keyWords = single.getSubjectWords();
        this.explains = single.getExplaintd();
        this.remark = single.getRemark();
        this.dataSource =dataSourceName;
        this.filePath = single.getFilePath();
        this.browseVolume = single.getBrowseVolume();
        this.creatorName = single.getCreatorName();
        this.createTime = single.getCreateTime();
        this.upgradeDate=single.getUpgradeDate();
        this.upgradeUser=single.getUpgradeUser();
        this.upgradeVersion=single.getUpgradeVersion();
        this.approvedUnit = single.getApprovedUnit();
        this.approvedDate = single.getApprovedDate();
        this.effectiveDate = single.getEffectiveDate();
        this.proposedUnit = single.getProposedUnit();
        this.draftedUnit = single.getDraftedUnit();
        this.majorDrafters = single.getMajorDrafters();
        this.content = single.getContent();
        
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
    public String getExplains()
    {
        return explains;
    }

    public void setExplains(String explains)
    {
        this.explains = explains;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getDataSource()
    {
        return dataSource;
    }
    public void setDataSource(String dataSource)
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

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreatorName()
    {
        return creatorName;
    }

    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getCreateIP()
    {
        return createIP;
    }

    public void setCreateIP(String createIP)
    {
        this.createIP = createIP;
    }

    public String getUpgradeVersion()
    {
        return upgradeVersion;
    }

    public void setUpgradeVersion(String upgradeVersion)
    {
        this.upgradeVersion = upgradeVersion;
    }

    public String getUpgradeUser()
    {
        return upgradeUser;
    }

    public void setUpgradeUser(String upgradeUser)
    {
        this.upgradeUser = upgradeUser;
    }

    public String getUpgradeDate()
    {
        return upgradeDate;
    }

    public void setUpgradeDate(String upgradeDate)
    {
        this.upgradeDate = upgradeDate;
    }

    public String getOldLawsId()
    {
        return oldLawsId;
    }

    public void setOldLawsId(String oldLawsId)
    {
        this.oldLawsId = oldLawsId;
    }

    public String getOldLawsNo()
    {
        return oldLawsNo;
    }

    public void setOldLawsNo(String oldLawsNo)
    {
        this.oldLawsNo = oldLawsNo;
    }
}
