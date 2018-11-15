package com.btb.service;

import java.util.List;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.Emp;

public interface EmpService {

	void insert(Emp emp,String[] roleIds);

	void updateEmp(Emp emp,String[] roleIds);

	void updateEmpStatus(List<Long> ids, int status);

	EasyUIDataGridResult findEmpList(int page, int rows, String key);

	List<Emp> findEmpListByGroupId(long gid);

	Emp findEmpByEname(String ename);

	boolean updateEmpPassword(String password,Long eid);

}
