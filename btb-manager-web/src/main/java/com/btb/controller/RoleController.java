package com.btb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.AuthRole;
import com.btb.service.RoleService;

/**
 * @ClassName: RoleController 
 * @Description: 角色管理
 * @author: huliyou
 * @date: 2018年10月28日 下午9:18:55
 */
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/role/add")
	public String add(AuthRole authRole,String[] functionIds) {
		roleService.insertRole(authRole,functionIds);
		return "role-list" ;
	}
	
	@RequestMapping("/role/list")
	@ResponseBody
	public EasyUIDataGridResult list() {
		return roleService.findRoleList();
	}

}
