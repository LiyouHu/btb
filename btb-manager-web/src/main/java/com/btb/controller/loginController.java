package com.btb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btb.common.pojo.BtbResult;
import com.btb.pojo.Emp;
import com.btb.service.EmpService;

/**
 * @ClassName: loginController 
 * @Description: 用户登录
 * @author: huliyou
 * @date: 2018年10月27日 下午5:20:46
 */

@Controller
public class loginController {
	@Autowired
	private EmpService empService;
	
	@RequestMapping(value="/emp/login",method=RequestMethod.POST)
	public String login(String name,String password,String captcha,HttpServletRequest request) {
		if(StringUtils.isBlank(name)||StringUtils.isBlank(password)) {
			request.setAttribute("errormsg", "用户名或密码不能为空！");
			return "login" ;
		}
		//取得session域中的验证码
		String rand = (String) request.getSession().getAttribute("rand");
		if(StringUtils.isBlank(captcha)) {
			request.setAttribute("errormsg", "验证码输入错误！");
			return "login";
		}else {
			//验证码不通过
			if(!captcha.equalsIgnoreCase(rand)) {
				request.setAttribute("errormsg", "验证码输入错误！");
				return "login";
			}
		}
		//获得当前用户对象，状态为为认证
		Subject subject = SecurityUtils.getSubject();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		//构造一个用户令牌
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		try {
			//会调用安全管理器，安全管理器调用ream
			subject.login(token);
		}catch(UnknownAccountException e) {
			request.setAttribute("errormsg", "用户名不存在!");
			e.printStackTrace();
			return "login";
		}catch(Exception e) {
			request.setAttribute("errormsg", "用户名或密码不存在！");
			e.printStackTrace();
			return "login";
		}
		Emp loginUser = (Emp)subject.getPrincipal();
		request.getSession().setAttribute("loginUser", loginUser);
		return "redirect:/index.html";
	}
	
	@RequestMapping("/emp/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login" ;
	}
	
	@RequestMapping("/emp/updatePassword")
	@ResponseBody
	public BtbResult updatePassword(String password,String rePassword,HttpServletRequest request) {
		if(StringUtils.isBlank(password)||StringUtils.isBlank(rePassword)) {
			return BtbResult.build(400, "密码不能为空！");
		}
		if(!password.equals(rePassword)) {
			return BtbResult.build(400, "两次密码输入不一致！");
		}
		Emp loginUser = (Emp)request.getSession().getAttribute("loginUser");
		boolean flag = empService.updateEmpPassword(password,loginUser.getEid());
		if(!flag) {
			return BtbResult.build(400,"密码修改失败！");
		}
		return BtbResult.ok();
	}
}
