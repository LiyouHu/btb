package com.btb.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.service.UserService;
/**
 * @ClassName: UserControlleer 
 * @Description: 客户管理
 * @author: huliyou
 * @date: 2018年10月31日 下午9:57:12
 */
@Controller
public class UserControlleer {
	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUIDataGridResult findUserList(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="10")int rows,@RequestParam(defaultValue="")String key){
		if(!"".equals(key)) {
			try {
				key = new String(key.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		EasyUIDataGridResult easyUIDataGridResult = userService.findUserList(page,rows,key);
		return easyUIDataGridResult;
	}
}
