/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ptj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Area;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 兼职信息Entity
 * @author lbk
 * @version 2017-12-10
 */
public class XlfPartTimeJob extends DataEntity<XlfPartTimeJob> {
	
	private static final long serialVersionUID = 1L;
	private String jobId;		// 唯一id
	private String jobName;		// 兼职名称
	private String limitNumber;		// 兼职限定人数
	private String sexDemand;		// 性别要求 1：男 ，2：女 3：不限制
	private String jobTypeId;		// 兼职类型id，关联兼职类型表
	private String wage;		// 兼职工资
	private String wageType;		// 工资单位
	private String workStreet;		// 工作街道
	private String calculateMoneyType;		// 结款方式 1：日结 ，2：周结，3：月结
	private String calculateMoneyDate;		// 结款日期
	private String timeType;		// 时间类型 1:短期不包含周末 ，2：短期包含周末，3：长期
	private Date releaseTime;		// 发布时间
	private Date endWorkDate;		// end_work_date
	private Date startWorkDate;		// 开始工作日期
	private String jobContent;		// 工作内容
	private String auditStatus;		// 审核状态，0：未审核，1：审核通过 ，2：已取消
	private String workTime;		// 工作时间段
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private int areaId;
	private int userId;
	
	private String userName;
	private String userCompany;
	private String jobType;
	private String area;
	
	
	
	public XlfPartTimeJob() {
		super();
	}

	public XlfPartTimeJob(String id){
		super(id);
	}
	
	
	
	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Length(min=1, max=64, message="唯一id长度必须介于 1 和 64 之间")
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@Length(min=0, max=256, message="兼职名称长度必须介于 0 和 256 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=0, max=64, message="兼职限定人数长度必须介于 0 和 64 之间")
	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}
	
	@Length(min=0, max=3, message="性别要求 1：男 ，2：女 3：不限制长度必须介于 0 和 3 之间")
	public String getSexDemand() {
		return sexDemand;
	}

	public void setSexDemand(String sexDemand) {
		this.sexDemand = sexDemand;
	}
	
	@Length(min=0, max=64, message="兼职类型id，关联兼职类型表长度必须介于 0 和 64 之间")
	public String getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(String jobTypeId) {
		this.jobTypeId = jobTypeId;
	}
	
	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}
	
	@Length(min=0, max=32, message="工资单位长度必须介于 0 和 32 之间")
	public String getWageType() {
		return wageType;
	}

	public void setWageType(String wageType) {
		this.wageType = wageType;
	}
	
	@Length(min=0, max=256, message="工作街道长度必须介于 0 和 256 之间")
	public String getWorkStreet() {
		return workStreet;
	}

	public void setWorkStreet(String workStreet) {
		this.workStreet = workStreet;
	}
	
	@Length(min=0, max=10, message="结款方式 1：日结 ，2：周结，3：月结长度必须介于 0 和 10 之间")
	public String getCalculateMoneyType() {
		return calculateMoneyType;
	}

	public void setCalculateMoneyType(String calculateMoneyType) {
		this.calculateMoneyType = calculateMoneyType;
	}
	
	@Length(min=0, max=10, message="结款日期长度必须介于 0 和 10 之间")
	public String getCalculateMoneyDate() {
		return calculateMoneyDate;
	}

	public void setCalculateMoneyDate(String calculateMoneyDate) {
		this.calculateMoneyDate = calculateMoneyDate;
	}
	
	@Length(min=0, max=3, message="时间类型 1:短期不包含周末 ，2：短期包含周末，3：长期长度必须介于 0 和 3 之间")
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(Date endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	
	@Length(min=0, max=1024, message="工作内容长度必须介于 0 和 1024 之间")
	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	
	@Length(min=0, max=10, message="审核状态，0：未审核，1：审核通过 ，2：已取消长度必须介于 0 和 10 之间")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	@Length(min=0, max=128, message="工作时间段长度必须介于 0 和 128 之间")
	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}