/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.join.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.activity.entity.XlfActivity;
import com.thinkgem.jeesite.modules.activity.service.XlfActivityService;
import com.thinkgem.jeesite.modules.join.entity.XlfJoin;
import com.thinkgem.jeesite.modules.join.service.XlfJoinService;
import com.thinkgem.jeesite.modules.ptj.entity.XlfPartTimeJob;
import com.thinkgem.jeesite.modules.ptj.service.XlfPartTimeJobService;

/**
 * 报名信息Controller
 * @author lbk
 * @version 2017-12-14
 */
@Controller
@RequestMapping(value = "${adminPath}/join/xlfJoin")
public class XlfJoinController extends BaseController {

	@Autowired
	private XlfJoinService xlfJoinService;
	
	@Autowired
	private XlfPartTimeJobService xlfPartTimeJobService;
	
	@Autowired
	private XlfActivityService xlfActivityService;
	
	@ModelAttribute
	public XlfJoin get(@RequestParam(required=false) String id) {
		XlfJoin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xlfJoinService.get(id);
		}
		if (entity == null){
			entity = new XlfJoin();
		}
		return entity;
	}
	
	@RequiresPermissions("join:xlfJoin:view")
	@RequestMapping(value = {"list", ""})
	public String list(XlfJoin xlfJoin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XlfJoin> page = xlfJoinService.findPage(new Page<XlfJoin>(request, response), xlfJoin); 
		List<XlfJoin> joinList= page.getList();
		for(XlfJoin join:joinList) {
			join.setApplySumNum( xlfJoinService.selectCoumnApply(join));
			XlfJoin newJoin=join;
			newJoin.setStatus(0);
			join.setApplyUncheck( xlfJoinService.selectCoumnApply(newJoin));
		}
		model.addAttribute("moduleType",xlfJoin.getModuleType());
		model.addAttribute("page", page);
		return "modules/join/xlfJoinList";
	}

	@RequiresPermissions("join:xlfJoin:view")
	@RequestMapping(value = "form")
	public String form(XlfJoin xlfJoin, Model model) {
		model.addAttribute("xlfJoin", xlfJoin);
		return "modules/join/xlfJoinForm";
	}

	@RequiresPermissions("join:xlfJoin:edit")
	@RequestMapping(value = "save")
	public String save(XlfJoin xlfJoin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xlfJoin)){
			return form(xlfJoin, model);
		}
		xlfJoinService.save(xlfJoin);
		addMessage(redirectAttributes, "保存报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/join/xlfJoin/?repage";
	}
	
	@RequiresPermissions("join:xlfJoin:edit")
	@RequestMapping(value = "delete")
	public String delete(XlfJoin xlfJoin, RedirectAttributes redirectAttributes) {
		xlfJoinService.delete(xlfJoin);
		addMessage(redirectAttributes, "删除报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/join/xlfJoin/?repage";
	}

	
	@RequiresPermissions("join:xlfJoin:view")
	@RequestMapping(value = {"moduleList", ""})
	public String moduleList(XlfPartTimeJob xlfPartTimeJob, HttpServletRequest request, HttpServletResponse response, Model model,String methodId) {
		if("2".equals(methodId)) {
			xlfPartTimeJob.setUserId(10001);
			Page<XlfPartTimeJob> page = xlfPartTimeJobService.findPage(new Page<XlfPartTimeJob>(request, response), xlfPartTimeJob); 
			model.addAttribute("page", page);
		}else  if("1".equals(methodId)){
			Page<XlfActivity> page = xlfActivityService.findPage(new Page<XlfActivity>(request, response), new XlfActivity()); 
			model.addAttribute("page", page);
		}
		return "modules/join/xlfPartTimeJobList";
	}

}