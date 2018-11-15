package com.btb.service.impl;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btb.mapper.EmpGroupMapper;
import com.btb.pojo.EmpGroup;
import com.btb.pojo.EmpGroupExample;
import com.btb.pojo.EmpGroupExample.Criteria;
import com.btb.service.EmpGroupService;
@Service
public class EmpGroupServiceImpl implements EmpGroupService {
	@Autowired 
	private EmpGroupMapper empGroupMapper;
	@Autowired
	private IdentityService identityService;
	
	@Override
	public List<EmpGroup> findEmpGroupList() {
		EmpGroupExample example = new EmpGroupExample();
		List<EmpGroup> list = empGroupMapper.selectByExample(example);
		return list;
	}


	@Override
	public EmpGroup findEmpGroupById(Long gid) {
		EmpGroup empGroup = empGroupMapper.selectByPrimaryKey(gid);
		return empGroup;
	}


	@Override
	public void insertEmpGroup(EmpGroup empGroup) {
		empGroupMapper.insert(empGroup);
		//将雇员组同步到activiti的act_id_group表中
		//使用组名称作为id
		Group group = new GroupEntity(empGroup.getGname());
		identityService.saveGroup(group);
	}


	@Override
	public void updateEmpGroup(EmpGroup empGroup) {
		empGroupMapper.updateByPrimaryKey(empGroup);
	}


	@Override
	public void deleteEmpGroups(List<Long> ids) {
		EmpGroupExample example = new EmpGroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andGidIn(ids);
		empGroupMapper.deleteByExample(example);
	}

}
