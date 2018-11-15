package com.btb.service;

import java.util.List;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.AuthRole;

public interface RoleService {

	void insertRole(AuthRole authRole, String[] functionIds);

	EasyUIDataGridResult findRoleList();
	
	List<AuthRole> findRoleListByEmp(long eid);
	
}
