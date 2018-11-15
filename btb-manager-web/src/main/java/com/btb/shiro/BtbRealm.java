package com.btb.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.btb.pojo.AuthFunction;
import com.btb.pojo.Emp;
import com.btb.service.EmpService;
import com.btb.service.FunctionService;

public class BtbRealm extends AuthorizingRealm{
	
	@Autowired
	private EmpService empService; 
	@Autowired
	private FunctionService functionService ;
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String ename = upToken.getUsername();
		Emp emp = empService.findEmpByEname(ename);
		if(emp != null) {
			//有用户名 校验密码是否正确
			//取得用户数据库中的密码
			String password = emp.getPassword();
			//简单鉴定用户信息对象
			/**
			 * 参数一：签名，程序可以在任意位置获取当前用户放入的对象
			 * 参数二：从数据中查询的密码
			 * 参数三：realm的名称
			 */
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(emp, password, this.getClass().getSimpleName());
			return info ;//返回给安全管理器，由安全管理器负责比对数据库中的密码和用户输入的密码
		}else {
			return null;
		}
		
		
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.addStringPermission("xxx");	//授予权限
		//info.addRole("yewuyuan");	//授予角色
		//取得当前登录的用户
		Emp emp = (Emp)principals.getPrimaryPrincipal();
		//根据用户查询权限
		List<AuthFunction> functionList = null ;
		if("admin".equals(emp.getEname())) {
			//是系统管理员 ，授予所有的权限
			functionList = functionService.findFunctionList();
		}else {
			//取得当前用户的权限
			functionList = functionService.findFunctionListByEmp(emp.getEid());
		}
		//给用户授权
		for (AuthFunction authFunction : functionList) {
			info.addStringPermission(authFunction.getCode());
		}
		return info;
	}

	
	
}
