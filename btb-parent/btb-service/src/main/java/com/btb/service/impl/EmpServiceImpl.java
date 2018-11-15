package com.btb.service.impl;

import java.beans.DesignMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.mapper.EmpMapper;
import com.btb.mapper.EmpRoleMapper;
import com.btb.pojo.Emp;
import com.btb.pojo.EmpExample;
import com.btb.pojo.EmpExample.Criteria;
import com.btb.pojo.EmpGroup;
import com.btb.pojo.EmpRoleExample;
import com.btb.pojo.EmpRoleKey;
import com.btb.service.EmpGroupService;
import com.btb.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	@Autowired 
	private EmpRoleMapper empRoleMapper;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private EmpGroupService empGroupService;
	
	@Override
	public void insert(Emp emp,String[] roleIds) {
		//雇员状态1正常，2离职
		emp.setStatus((byte)1);	
		//默认的密码使用用户的手机号
		String password = DigestUtils.md5DigestAsHex(emp.getCellphone().getBytes());
		emp.setPassword(password);
		emp.setPassword(password);
		emp.setHiredate(new Date());
		emp.setUpdated(new Date());
		empMapper.insertSelective(emp);
		User user = new UserEntity(emp.getEid().toString());
		/**
		 *  用户同步到activiti act_id_user 表中
		 *  用户与组信息同步到activiti 的act_id_membership中
		 */
		identityService.saveUser(user);
		Long gid = emp.getGid();
		EmpGroup empGroup = empGroupService.findEmpGroupById(gid);
		identityService.createMembership(emp.getEid().toString(),empGroup.getGname());
		//用户关联角色
		if(roleIds != null) {
			for (String roleId : roleIds) {
				EmpRoleKey empRoleKey = new EmpRoleKey();
				empRoleKey.setEmpId(emp.getEid());
				empRoleKey.setRoleId(roleId);
				empRoleMapper.insert(empRoleKey);
			}
		}
	}

	@Override
	public EasyUIDataGridResult findEmpList(int page,int rows,String key) {
		//使用mybatis分页插件设置分页
		PageHelper.startPage(page, rows);	//设page码和每页显示的记录数
		EmpExample example = new EmpExample();
		if(key != null && !"".equals(key)) {	//添加查询条件
			Criteria criteria = example.createCriteria();
			criteria.andEnameLike(key);
			Criteria criteria2 = example.createCriteria();
			criteria2.andCellphoneEqualTo(key);
			example.or(criteria2);
			Criteria criteria3 = example.createCriteria();
			criteria3.andAddressLike(key);
			example.or(criteria3);
		}
		List<Emp> list = empMapper.selectByExample(example);
		PageInfo<Emp> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}
	@Override
	public List<Emp> findEmpListByGroupId(long gid){
		EmpExample example = new EmpExample();
		Criteria criteria = example.createCriteria();
		criteria.andGidEqualTo(gid);
		List<Emp> emps = empMapper.selectByExample(example);
		return emps;
		
	}
	
	@Override
	public void updateEmp(Emp emp,String[] roleIds) {
		emp.setUpdated(new Date());
		empMapper.updateByPrimaryKeySelective(emp);
		
		//修改雇员的角色需要先查出所有雇员角色
		EmpRoleExample example = new EmpRoleExample();
		com.btb.pojo.EmpRoleExample.Criteria criteria = example.createCriteria();
		criteria.andEmpIdEqualTo(emp.getEid());
		//雇员原有的角色（数据库中的）
		List<EmpRoleKey> empRoleKeyList = empRoleMapper.selectByExample(example);
		//雇员更新的角色（提交的）
		List<EmpRoleKey> updateEmpRoleList = new ArrayList<EmpRoleKey>();
		if(roleIds != null) {
			for (String roleId : roleIds) {
				EmpRoleKey key = new EmpRoleKey();
				key.setEmpId(emp.getEid());
				key.setRoleId(roleId);
				//保存雇员提交的角色
				updateEmpRoleList.add(key);
			}
		}
		//1、雇员原来没有任何角色，直接增加
		if(empRoleKeyList.size()==0) {
			//增加更新的新角色
			for (EmpRoleKey empRoleKey : updateEmpRoleList) {
				empRoleMapper.insert(empRoleKey);
			}
			return ;
		}
		//2、雇员没有提交任何角色 
		if(updateEmpRoleList.size() == 0) {
			for (EmpRoleKey empRoleKey : empRoleKeyList) {
				//删除原有的角色
				empRoleMapper.deleteByPrimaryKey(empRoleKey);
			}
			return ;
		}
		//3、雇员原有角色，又提交了新的雇员角色，
		//3.1移除所有更新的雇员角色，剩下的是已经去掉的雇员角色
		//移除之前先备份
		List<EmpRoleKey> copyEmpRoleKeyList = new ArrayList<EmpRoleKey>();
		for (EmpRoleKey empRoleKey : empRoleKeyList) {
			copyEmpRoleKeyList.add(empRoleKey);
		}
		copyEmpRoleKeyList.removeAll(updateEmpRoleList);
		for (EmpRoleKey empRoleKey : copyEmpRoleKeyList) {
			//删除所有出掉的雇员角色
			empRoleMapper.deleteByPrimaryKey(empRoleKey);
		}
		//3.2得到新增加的雇员角色，并添加到数据库
		updateEmpRoleList.removeAll(empRoleKeyList);
		for (EmpRoleKey empRoleKey : updateEmpRoleList) {
			empRoleMapper.insert(empRoleKey);
		}
	}

	@Override
	public void updateEmpStatus(List<Long> ids, int status) {
		Emp emp = new Emp();
		//更新status 1为正常 ，2为离职
		emp.setStatus((byte)status);
		EmpExample example = new EmpExample();
		Criteria criteria = example.createCriteria();
		criteria.andEidIn(ids);
		empMapper.updateByExampleSelective(emp, example);
	}

	@Override
	public Emp findEmpByEname(String ename) {
		EmpExample example = new EmpExample();
		Criteria criteria = example.createCriteria();
		criteria.andEnameEqualTo(ename);
		List<Emp> list = empMapper.selectByExample(example);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateEmpPassword(String password,Long eid) {
		Emp emp = new Emp();
		emp.setEid(eid);
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		emp.setPassword(password);
		int i = empMapper.updateByPrimaryKeySelective(emp);
		if(i!=1) {
			return false ;
		}
		return true;
	}
}
