/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ptj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ptj.entity.XlfPartTimeJob;
import com.thinkgem.jeesite.modules.ptj.dao.XlfPartTimeJobDao;

/**
 * 兼职信息Service
 * @author lbk
 * @version 2017-12-10
 */
@Service
@Transactional(readOnly = true)
public class XlfPartTimeJobService extends CrudService<XlfPartTimeJobDao, XlfPartTimeJob> {

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
		super.save(xlfPartTimeJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(XlfPartTimeJob xlfPartTimeJob) {
		super.delete(xlfPartTimeJob);
	}
	
}