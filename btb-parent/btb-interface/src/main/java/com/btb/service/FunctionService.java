package com.btb.service;

import java.util.List;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.AuthFunction;

public interface FunctionService {

	EasyUIDataGridResult findFunctionList(int page, int rows);

	List<AuthFunction> findFunctionList();

	void insertFunction(AuthFunction authFunction);
	
	List<AuthFunction> findFunctionListByEmp(long eid);
	//显示所有的菜单 ，不显示generatemenu 为1的项
	List<AuthFunction> findFunctionListByMenuAll();
	//显示所有的菜单 ，不显示generatemenu 为1的项，根据用户的权限查
	List<AuthFunction> findFunctionListByMenuByEmp(Long eid);

}
