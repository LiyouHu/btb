package com.btb.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.AuthRole;
import com.btb.pojo.Emp;
import com.btb.pojo.EmpGroup;
import com.btb.service.EmpGroupService;
import com.btb.service.EmpService;
import com.btb.service.RoleService;
import com.btb.web.pojo.EmpCustom;

/**
 * 雇员管理
 * @ClassName: EmpController 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年9月29日 下午9:07:53
 */
@Controller
public class EmpController {
	@Autowired
	private EmpService empService;
	@Autowired 
	private EmpGroupService empGroupService;
	@Autowired 
	private RoleService roleService;
	@RequestMapping("/emp/add")
	public String save(Emp emp,String[] roleIds) {
		empService.insert(emp,roleIds);
		return "emp-list";
		
	}
	
	@RequestMapping("/emp/list")
	@ResponseBody
	public EasyUIDataGridResult list(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="10")int rows,@RequestParam(defaultValue="")String key) {
		if(!"".equals(key)) {
			try {
				key = new String(key.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		EasyUIDataGridResult easyUIDataGridResult = empService.findEmpList(page,rows,key);
		
		List<Emp> list = (List<Emp>)easyUIDataGridResult.getRows();
		List<EmpCustom> empCustomList = new ArrayList<EmpCustom>();
		
		for (Emp emp : list) {
			EmpCustom empCustom = new EmpCustom(emp);
			if(emp.getGid() != null && !"".equals(emp.getGid().toString())) {
				EmpGroup empGroup = empGroupService.findEmpGroupById(emp.getGid());
				empCustom.setGroupName(empGroup.getGname());
			}
			//一个雇员对应多个角色
			List<AuthRole> roles = roleService.findRoleListByEmp(emp.getEid());
			empCustom.setRoles(roles);
			empCustomList.add(empCustom);
		}
		easyUIDataGridResult.setRows(empCustomList);
		return easyUIDataGridResult;
	}
	
	@RequestMapping("/emp/update")
	public String update(Emp emp,@RequestParam(defaultValue="")String[] roleIds) {
		empService.updateEmp(emp,roleIds);
		return "emp-list" ;
	}
	
	@RequestMapping("/emp/delete/{ids}")
	public String delete(@PathVariable List<Long> ids) {
		//emp.status 1 为 正常 ，2为删除
		empService.updateEmpStatus(ids,2);
		return "emp-list" ;
	}
	@RequestMapping("/emp/restore/{ids}")
	public String restore(@PathVariable List<Long> ids) {
		empService.updateEmpStatus(ids,1);
		return "emp-list" ;
	}
	
}
