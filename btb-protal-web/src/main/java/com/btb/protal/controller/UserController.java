package com.btb.protal.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.BtbResult;
import com.btb.common.pojo.EasyUIDataGridResult;
import com.btb.pojo.User;
import com.btb.service.UserService;
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/checkData")
	@ResponseBody
	public BtbResult checkData(String property,int type) {
		BtbResult btbResult = userService.checkData(property,type);
		return btbResult ;
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public BtbResult register(User user) {
		BtbResult btbResult = userService.insertUser(user);
		return btbResult ;
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public String login(User user,HttpServletRequest request, HttpServletResponse response
			,@RequestParam(name="callback",defaultValue="")String url) {
		BtbResult btbResult = userService.findUser(user);
		if(btbResult.getStatus()==400) {
			String msg = "";
			try {
				msg = URLEncoder.encode(btbResult.getMsg(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:/login.html?msg="+msg ;	//重新登录
		}
		//登录成功!
		if(btbResult.getStatus()==200) {
			//设置session属性范围
			HttpSession session = request.getSession();
			User loginuser = (User)btbResult.getData();
			loginuser.setPassword(null);
			session.setAttribute("loginUser", loginuser);
			//跳转回访问页
			if(StringUtils.isNotBlank(url)) {	//不是登录状态,经过interceptor
				return "redirect:"+url ;
			}else {//直接登录的
				return "redirect:/index.html";	
			}
			
		}
		
		return "login" ;
	}
	
	@RequestMapping("/user/logout")
	@ResponseBody
	public BtbResult logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return BtbResult.ok();
	}
	
	
}
