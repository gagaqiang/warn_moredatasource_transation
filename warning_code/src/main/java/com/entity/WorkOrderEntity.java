package com.entity;

import java.util.Date;

public class WorkOrderEntity {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String businessType;

    private String state;

    private String businessNo;

    private Integer currentStep;

    private String fromDepartmentId;

    private String toDepartmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public String getFromDepartmentId() {
        return fromDepartmentId;
    }

    public void setFromDepartmentId(String fromDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
    }

    public String getToDepartmentId() {
        return toDepartmentId;
    }

    public void setToDepartmentId(String toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }
}