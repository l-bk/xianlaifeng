/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.activity.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.activity.entity.XlfActivity;
import com.thinkgem.jeesite.modules.activity.dao.XlfActivityDao;

/**
 * 活动信息管理Service
 * @author lbk
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class XlfActivityService extends CrudService<XlfActivityDao, XlfActivity> {

	public XlfActivity get(String id) {
		return super.get(id);
	}
	
	public List<XlfActivity> findList(XlfActivity xlfActivity) {
		return super.findList(xlfActivity);
	}
	
	public Page<XlfActivity> findPage(Page<XlfActivity> page, XlfActivity xlfActivity) {
		return super.findPage(page, xlfActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(XlfActivity xlfActivity) {
		xlfActivity.setCreateTime(new Date());
		xlfActivity.setStatus("1");
		xlfActivity.setCreateUser(10001);
		if(StringUtils.isNotBlank(xlfActivity.getDetails())) {
			xlfActivity.setDetails(Encodes.unescapeHtml(xlfActivity.getDetails()));
		}
		super.save(xlfActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(XlfActivity xlfActivity) {
		super.delete(xlfActivity);
	}
	
}