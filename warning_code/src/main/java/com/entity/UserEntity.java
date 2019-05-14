package com.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

public class UserEntity {
    private String id;

    private String loginName;

    private String name;

    private String password;

    private String phoneNo;

    private String groupId;

    private Integer companyId;

    private String companySn;

    private Integer status;

    private String avatar;

    private Date lastLoginTime;

    private Date thisLoginTime;

    private Date stopDate;

    private Integer errorCount;

    private String companyOfficialId;

    private String branchDots;

    private String companyIds;

    private String branchDotId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanySn() {
        return companySn;
    }

    public void setCompanySn(String companySn) {
        this.companySn = companySn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getThisLoginTime() {
        return thisLoginTime;
    }

    public void setThisLoginTime(Date thisLoginTime) {
        this.thisLoginTime = thisLoginTime;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getCompanyOfficialId() {
        return companyOfficialId;
    }

    public void setCompanyOfficialId(String companyOfficialId) {
        this.companyOfficialId = companyOfficialId;
    }

    public String getBranchDots() {
        return branchDots;
    }

    public void setBranchDots(String branchDots) {
        this.branchDots = branchDots;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getBranchDotId() {
        return branchDotId;
    }

    public void setBranchDotId(String branchDotId) {
        this.branchDotId = branchDotId;
    }
}