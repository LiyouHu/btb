package com.btb.web.pojo;

import java.util.List;

import com.btb.pojo.Emp;
import com.btb.pojo.EmpGroup;

public class EmpGroupCustom extends EmpGroup {
	private List<Emp> emps;
	
	public String getGroupEmps() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		int flag = 0;
		for (Emp emp : emps) {
			sb.append(emp.getEname());
			if(emps.size()-1 != flag) {
				sb.append("， ");
			}
			flag++;
		}
		sb.append(" ]");
		return sb.toString();
	}
	
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

	public EmpGroupCustom(EmpGroup empGroup) {
		this.setGid(empGroup.getGid());
		this.setGname(empGroup.getGname());
		this.setCode(empGroup.getCode());
		this.setDescription(empGroup.getDescription());
	}

}
