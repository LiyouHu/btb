package com.btb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.pojo.Emp;
import com.btb.pojo.EmpGroup;
import com.btb.service.EmpGroupService;
import com.btb.service.EmpService;
import com.btb.web.pojo.EmpGroupCustom;

/**
 * 雇员组管理
 */
@Controller
public class EmpGroupController {
	@Autowired
	private EmpGroupService empGroupService;
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/empGroup/list")
	@ResponseBody
	public List<EmpGroup> list() {
		return empGroupService.findEmpGroupList();
	}
	
	
	@RequestMapping("/empGroup/listDesc")
	@ResponseBody
	public List<EmpGroupCustom> listDesc() {
		List<EmpGroup> empGroupList = empGroupService.findEmpGroupList();
		List<EmpGroupCustom> gorupCustomList = new ArrayList<EmpGroupCustom>();
		for (EmpGroup empGroup : empGroupList) {
			EmpGroupCustom empGroupCustom = new EmpGroupCustom(empGroup);
			List<Emp> emps = empService.findEmpListByGroupId(empGroup.getGid());
			empGroupCustom.setEmps(emps);
			gorupCustomList.add(empGroupCustom);
		}
		return gorupCustomList;
	}
	
	@RequestMapping("/empGroup/findEmpgroupById")
	@ResponseBody
	public EmpGroup findEmpgroupById(@RequestParam(name="group_id") Long gid) {
		EmpGroup empGroup = empGroupService.findEmpGroupById(gid);
		return empGroup;
	}
	
	@RequestMapping("/empGroup/add")
	public String save(EmpGroup empGroup) {
		empGroupService.insertEmpGroup(empGroup);
		return "emp-group";
	}
	
	@RequestMapping("/empGroup/update")
	public String update(EmpGroup empGroup) {
		empGroupService.updateEmpGroup(empGroup);
		return "emp-group";
	}
	
	@RequestMapping("/empGroup/delete/{ids}")
	public String delete(@PathVariable List<Long> ids) {
		empGroupService.deleteEmpGroups(ids);
		return "emp-group";
	}
	
}
