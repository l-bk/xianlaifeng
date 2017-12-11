/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ptj.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.modules.ptj.entity.XlfPartTimeJob;
import com.thinkgem.jeesite.modules.area.dao.XlfAreaDao;
import com.thinkgem.jeesite.modules.area.entity.XlfArea;
import com.thinkgem.jeesite.modules.area.service.XlfAreaService;
import com.thinkgem.jeesite.modules.ptj.dao.XlfPartTimeJobDao;

/**
 * 兼职信息Service
 * @author lbk
 * @version 2017-12-10
 */
@Service
@Transactional(readOnly = true)
public class XlfPartTimeJobService extends CrudService<XlfPartTimeJobDao, XlfPartTimeJob> {
	
	
	@Autowired
	private XlfAreaDao areaDao;

	public XlfPartTimeJob get(String id) {
		return super.get(id);
	}
	
	public List<XlfPartTimeJob> findList(XlfPartTimeJob xlfPartTimeJob) {
		return super.findList(xlfPartTimeJob);
	}
	
	public Page<XlfPartTimeJob> findPage(Page<XlfPartTimeJob> page, XlfPartTimeJob xlfPartTimeJob) {
		return super.findPage(page, xlfPartTimeJob);
	}
	
	@Transactional(readOnly = false)
	public void save(XlfPartTimeJob xlfPartTimeJob) {
		if(xlfPartTimeJob == null) { //添加信息
			xlfPartTimeJob.setReleaseTime(new Date());
			xlfPartTimeJob.setAuditStatus("1");//管理员录入的东西默认审核通过
		}
		xlfPartTimeJob.setId(xlfPartTimeJob.getJobId());//id用来判断是添加还是修改
		if(StringUtils.isNotBlank(xlfPartTimeJob.getJobContent())) {//将内容转码为html标签
			xlfPartTimeJob.setJobContent(Encodes.unescapeHtml(xlfPartTimeJob.getJobContent()));
		}
		//时间类型设置
		if(null != xlfPartTimeJob.getStartWorkDate() && !"".equals(xlfPartTimeJob.getStartWorkDate())
				&& null != xlfPartTimeJob.getEndWorkDate() && !"".equals(xlfPartTimeJob.getEndWorkDate())) {
			int days = TimeUtils.differentDaysByMillisecond(xlfPartTimeJob.getEndWorkDate(), xlfPartTimeJob.getStartWorkDate());
			if(days < 5){
	               xlfPartTimeJob.setTimeType("1");
	               Calendar calendar=Calendar.getInstance();
	               calendar.setTime(xlfPartTimeJob.getStartWorkDate());
	               Date date= xlfPartTimeJob.getStartWorkDate();
	               int result=0;
	               for(int i=0;i<days;i++){
	                   try {
	                       result=TimeUtils.dayForWeek(date);
	                       if(result == 7 || result == 6){
	                           xlfPartTimeJob.setTimeType("2");
	                           break;
	                       }
	                      calendar.add(Calendar.DAY_OF_MONTH,1);
	                       date=calendar.getTime();

	                   } catch (Exception e) {
	                       e.printStackTrace();
	                   }
	               }


	            }else{//大于五天，表示存在周末
	               if(days <15){
	                    xlfPartTimeJob.setTimeType("2");//小于15天大于5天，表示短期包含周末
	               }else{
	                    xlfPartTimeJob.setTimeType("3");//长期，大于15天
	               }
	           }

	        }
		if(StringUtils.isNotBlank(xlfPartTimeJob.getWorkStreet())) {
			String address=xlfPartTimeJob.getWorkStreet();
			String province=address.substring(0,address.indexOf("省")+1);
			String city=address.substring(address.indexOf("省")+1,address.indexOf("市")+1);
			String district=address.substring(address.indexOf("市")+1,address.indexOf("区")+1);
			String street=address.substring(address.indexOf("区")+1);
			XlfArea area= new XlfArea();
			area.setProvince(province);
			area.setCity(city);
			area.setDistrict(district);
			area = areaDao.selectByArea(area);
			if(area != null) {
				xlfPartTimeJob.setAreaId(area.getAreaId());
			}
		}
		xlfPartTimeJob.setUserId(10001);
		super.save(xlfPartTimeJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(XlfPartTimeJob xlfPartTimeJob) {
		super.delete(xlfPartTimeJob);
	}
	
}