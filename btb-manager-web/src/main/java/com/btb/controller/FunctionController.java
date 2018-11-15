package com.btb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.AuthFunction;
import com.btb.pojo.Emp;
import com.btb.service.FunctionService;
import com.btb.web.pojo.FunctionCustom;

/**
 * @ClassName: FunctionController 
 * @Description:功能权限管理
 * @author: huliyou
 * @date: 2018年10月28日 下午2:44:19
 */
@Controller
public class FunctionController {
	@Autowired 
	private FunctionService functionService;
	
	
	@RequestMapping("/function/list")
	@ResponseBody	
	public EasyUIDataGridResult list(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="10")int rows) {
		//page：查询页码 rows:每页显示的记录条数
		EasyUIDataGridResult easyUIDataGridResult = functionService.findFunctionList(page,rows);
		return easyUIDataGridResult;
	}
	
	@RequestMapping("/function/listAll")
	@ResponseBody
	public List<FunctionCustom> listAll(){
		List<AuthFunction> list = functionService.findFunctionList();
		List<FunctionCustom> funList = new ArrayList<FunctionCustom>();
		for (AuthFunction authFunction : list) {
			funList.add(new FunctionCustom(authFunction));
		}
		return funList;
	}
	
	@RequestMapping("/function/add")
	public String add(AuthFunction authFunction) {
		functionService.insertFunction(authFunction);
		return "function-list" ;
	}
	/*
	 * 根据权限显示菜单
	 */
	@RequestMapping("/function/showMenu")
	@ResponseBody
	public List<FunctionCustom> showMenu(HttpServletRequest request){
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		List<AuthFunction> menuList = null ;
		if("admin".equals(loginUser.getEname())) {
			//是系统管理员显示所有的菜单
			menuList = functionService.findFunctionListByMenuAll();
		}else {
			menuList = functionService.findFunctionListByMenuByEmp(loginUser.getEid());
		}
		 
		List<FunctionCustom> list = new ArrayList<FunctionCustom>();
		for (AuthFunction authFunction : menuList) {
			list.add(new FunctionCustom(authFunction));
		}
		return list;
	}

}
