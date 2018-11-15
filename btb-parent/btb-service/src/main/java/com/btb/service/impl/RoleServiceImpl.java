package com.btb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.mapper.AuthRoleMapper;
import com.btb.mapper.EmpRoleMapper;
import com.btb.mapper.RoleFunctionMapper;
import com.btb.pojo.AuthRole;
import com.btb.pojo.AuthRoleExample;
import com.btb.pojo.EmpRoleExample;
import com.btb.pojo.EmpRoleExample.Criteria;
import com.btb.pojo.EmpRoleKey;
import com.btb.pojo.RoleFunctionKey;
import com.btb.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private AuthRoleMapper authRoleMapper;
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;
	@Autowired
	private EmpRoleMapper empRoleMapper; 
	
	@Override
	public void insertRole(AuthRole authRole, String[] functionIds) {
		authRole.setId(UUID.randomUUID().toString());
		authRoleMapper.insert(authRole);
		//一个角色对应多个权限
		for (String functionId : functionIds) {
			//构造一个角色权限
			RoleFunctionKey roleFunctionKey = new RoleFunctionKey();
			roleFunctionKey.setFunctionId(functionId);
			roleFunctionKey.setRoleId(authRole.getId());
			//添加一个角色权限
			roleFunctionMapper.insert(roleFunctionKey);
		}
	}

	@Override
	public EasyUIDataGridResult findRoleList() {
		AuthRoleExample example = new AuthRoleExample();
		List<AuthRole> list = authRoleMapper.selectByExample(example);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setRows(list);
		easyUIDataGridResult.setTotal((long)list.size());
		return easyUIDataGridResult;
	}

	@Override
	public List<AuthRole> findRoleListByEmp(long eid) {
		EmpRoleExample example = new EmpRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdEqualTo(eid);
		//一个用户可能对应多个角色
		List<EmpRoleKey> list = empRoleMapper.selectByExample(example);
		List<AuthRole> roles = new ArrayList<AuthRole>();
		for (EmpRoleKey empRoleKey : list) {
			AuthRole role = authRoleMapper.selectByPrimaryKey(empRoleKey.getRoleId());
			roles.add(role);
		}
		return roles;
	}

}
