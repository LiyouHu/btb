package com.btb.service;

import java.util.List;

import com.btb.pojo.EmpGroup;

public interface EmpGroupService {

	List<EmpGroup> findEmpGroupList();

	EmpGroup findEmpGroupById(Long gid);

	void insertEmpGroup(EmpGroup empGroup);

	void updateEmpGroup(EmpGroup empGroup);

	void deleteEmpGroups(List<Long> ids);

}
