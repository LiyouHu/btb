package com.btb.web.pojo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.btb.pojo.AuthRole;
import com.btb.pojo.Emp;
/**
 *
 * @ClassName: EmpCustom 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年9月30日 下午9:29:48
 */
public class EmpCustom extends Emp {
	private List<AuthRole> roles ;
	
	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}



	private String groupName ;
	
	public EmpCustom() {
		super();
	}

	public EmpCustom(Emp emp) {
		this.setEid(emp.getEid());
		this.setEname(emp.getEname());
		this.setGender(emp.getGender());
		this.setBirthday(emp.getBirthday());
		this.setAddress(emp.getAddress());
		this.setCellphone(emp.getCellphone());
		this.setHiredate(emp.getHiredate());
		this.setBonus(emp.getBonus());
		this.setGid(emp.getGid());
		this.setSalary(emp.getSalary());
		this.setPassword(emp.getPassword());
		this.setStatus(emp.getStatus());
		this.setUpdated(emp.getUpdated());
		
	}
	
	
	
	public String getFormatBirthday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formatBirthday = "";
		if(this.getBirthday() != null) {
			formatBirthday = format.format(this.getBirthday());
		}
		if(formatBirthday != null && !"".equals(formatBirthday)) {
			return formatBirthday;
		}
		return "";
	}
	
	
	

	//扩展emp的属性

	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
