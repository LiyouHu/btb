package com.btb.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.mapper.AuthFunctionMapper;
import com.btb.pojo.AuthFunction;
import com.btb.pojo.AuthFunctionExample;
import com.btb.pojo.AuthFunctionExample.Criteria;
import com.btb.service.FunctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private AuthFunctionMapper authFunctionMapper;
	
	@Override
	public EasyUIDataGridResult findFunctionList(int page, int rows) {
		//使用pageHelper分页
		PageHelper.startPage(page, rows);
		List<AuthFunction> list = findFunctionList();
		//取得总的记录条数
		long total = new PageInfo<AuthFunction>(list).getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal(total);
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}

	@Override
	public List<AuthFunction> findFunctionList() {
		AuthFunctionExample example = new AuthFunctionExample();;
		//example.setOrderByClause("zindex");	//按照zindex升序排序 
		List<AuthFunction> list = authFunctionMapper.selectByExample(example);
		return list ;
	}
	@Override
	public void insertFunction(AuthFunction authFunction) {
		authFunction.setId(UUID.randomUUID().toString());
		authFunctionMapper.insertSelective(authFunction);
	}
	
	/**
	 * 根据雇员取得权限
	 */
	@Override
	public List<AuthFunction> findFunctionListByEmp(long eid) {
		//根据用户查询所有的角色
		List<AuthFunction> functionList = authFunctionMapper.selectByEmp(eid);
		return functionList;
	}

	@Override
	public List<AuthFunction> findFunctionListByMenuAll() {
		AuthFunctionExample example = new AuthFunctionExample();
		//菜单排序
		example.setOrderByClause("zindex");
		Criteria criteria = example.createCriteria();
		//generatemenu 为 “1” 表示生成菜单
		criteria.andGeneratemenuEqualTo("1");
		List<AuthFunction> list = authFunctionMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<AuthFunction> findFunctionListByMenuByEmp(Long eid) {
		List<AuthFunction> menuList = authFunctionMapper.selectFunctionListMenuByEmp(eid);
		return menuList;
	}

}
