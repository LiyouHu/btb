package com.btb.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.mapper.UserMapper;
import com.btb.pojo.Emp;
import com.btb.pojo.EmpExample;
import com.btb.pojo.User;
import com.btb.pojo.UserExample;
import com.btb.pojo.UserExample.Criteria;
import com.btb.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public BtbResult checkData(String property,int type) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(type == 1) {
			criteria.andUsernameEqualTo(property);	//查询用户名					
		}else if(type == 2) {
			criteria.andCellphoneEqualTo(property);	//检查手机号			
		}else if(type == 3) {
			criteria.andEmailEqualTo(property);
		}
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size()>0) {
			return BtbResult.ok(true);	//返回true 表示查询到了
		}
		return BtbResult.ok(false);
	}

	@Override
	public BtbResult insertUser(User user) {
		//非空检查，防止绕过前台页面检查
		if(StringUtils.isBlank(user.getUsername())) {
			return BtbResult.build(400, "用户名不能为空！");
		}
		if(StringUtils.isBlank(user.getCellphone())) {
			return BtbResult.build(400, "手机号不能为空！");
		}
		if(StringUtils.isBlank(user.getEmail())) {
			return BtbResult.build(400, "邮箱不能为空！");
		}
		//检查信息是否已经被使用
		if(StringUtils.isNotBlank(user.getUsername())) {
			if((boolean)this.checkData(user.getUsername(), 1).getData()) {
				return BtbResult.build(400, "用户名已经被使用");
			}
		}
		if(StringUtils.isNotBlank(user.getCellphone())) {
			if((boolean)this.checkData(user.getCellphone(), 2).getData()) {
				return BtbResult.build(400, "手机号已经被使用！");
			}
		}
		if(StringUtils.isNotBlank(user.getEmail())) {
			if((boolean)this.checkData(user.getEmail(),3).getData()) {
				return BtbResult.build(400, "邮箱已经被使用");
			}
		}
		String password = user.getPassword();
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setCreated(new Date());
		user.setUpdated(new Date());
		int i = userMapper.insert(user);
		if(i == 1) {
			return BtbResult.ok(true);
		}
		return BtbResult.ok(false);
	}

	@Override
	public BtbResult findUser(User user) {
		if(StringUtils.isBlank(user.getUsername())) {
			return BtbResult.build(400,"用户名不能为空！");
		}
		if(StringUtils.isBlank(user.getPassword())) {
			return BtbResult.build(400, "用户密码不能为空！");
			
		}	
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		List<User> list = userMapper.selectByExample(example);
		if(list == null||list.size()==0) {
			return BtbResult.build(400,"用户名或密码不正确！");
		}
		String password = list.get(0).getPassword();
		String md5psw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		if(!md5psw.equals(password)) {
			return BtbResult.build(400,"用户名或密码不正确！");
		}
		//登录成功！将user保存
		return BtbResult.ok(list.get(0));
	}

	@Override
	public EasyUIDataGridResult findUserList(int page, int rows, String key) {
		//使用mybatis分页插件设置分页
		PageHelper.startPage(page, rows);	//设page码和每页显示的记录数
		UserExample example = new UserExample();
		if(key != null && !"".equals(key)) {	//添加查询条件
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(key);
			Criteria criteria2 = example.createCriteria();
			criteria2.andCellphoneEqualTo(key);
			example.or(criteria2);
			Criteria criteria3 = example.createCriteria();
			criteria3.andAddressLike(key);
			example.or(criteria3);
			Criteria criteria4 = example.createCriteria();
			criteria3.andEmailEqualTo(key);
			example.or(criteria4);
		}
		List<User> list = userMapper.selectByExample(example);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		for (User user : list) {
			//前台不显示密码
			user.setPassword("");
		}
		easyUIDataGridResult.setTotal(pageInfo.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}

}
