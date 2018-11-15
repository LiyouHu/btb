package com.btb.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.btb.pojo.Orders;

@Controller
public class ProcessInstanceController {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	/**
	 * 	查询正在运行的流程实例
	 */
	@RequestMapping("/processInstance/list")
	public String list(Model model) {
		ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery();
		instanceQuery.orderByProcessInstanceId().desc(); //根据流程实例降序
		List<ProcessInstance> list = instanceQuery.list();
		model.addAttribute("list", list);
		return "processinstance";
		
	}
	
	/**
	 * 根据正在运行的流程实例id查询流程实例图片
	 */
	@RequestMapping("/processInstance/showPng")
	public String showPng(@RequestParam(defaultValue="")String id,Model model) {
		//创建一个流程实例查询
		ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery();
		instanceQuery.processInstanceId(id);
		//根据流程实例id得到一个流程实例
		ProcessInstance processInstance = instanceQuery.singleResult();
		String definitionId = processInstance.getProcessDefinitionId();
		//创建一个流程定义查询
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		definitionQuery.processDefinitionId(definitionId);
		//得到一个流程定义
		ProcessDefinition processDefinition = definitionQuery.singleResult();
		//根根流程定义对象得到流程部署id
		String deploymentId = processDefinition.getDeploymentId();
		//得到流程设计图名称
		String pngName = processDefinition.getDiagramResourceName();
		//取得正在运行到那个节点的名称
		String activityId = processInstance.getActivityId();
		//查询坐标 （加载btmn文件）
		//需要转换为ProcessDefinitionEntity 此ProcessDefinition实现类包含获得节点坐标的信息（查询act_ge_bytearray表）
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(definitionId);
		//获得activiti对象
		ActivityImpl activity = processDefinitionEntity.findActivity(activityId);	
		int x = activity.getX();
		int y = activity.getY();
		int width = activity.getWidth();
		int height = activity.getHeight();
		model.addAttribute("x",x);
		model.addAttribute("y",y);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("pngName", pngName);
		model.addAttribute("deploymentId",deploymentId);
		return "image";
	}
	
	//将图片写入客户端
	@RequestMapping("/processInstance/viewImage")
	public void viewImage(String deploymentId,String pngName,HttpServletResponse response) throws Exception {
		InputStream pngStream = repositoryService.getResourceAsStream(deploymentId, pngName);
		ServletOutputStream outputStream = response.getOutputStream();
		FileUtil.copyStream(pngStream, outputStream);
		outputStream.flush();
		outputStream.close();
	}
	
	
	@RequestMapping("/processInstance/findData")
	//查看业务数据
	public String findData(String id,Model model) {
		String executionId = id;
		Map<String, Object> variables = runtimeService.getVariables(executionId);
		Object obj = variables.get("业务数据");
		if(obj == null) {
			return "processinstance_data" ;
		}
		//如果这是一个订单业务
		if(obj instanceof Orders) {
			Orders orders = (Orders)obj;
			return "redirect:/order/orderdetails?oid="+orders.getOid();
		}
		//返回一个通用的流程数据展示页
		model.addAttribute("processInstanceData", obj.toString());
		return "processinstance_data" ;
		
	}
}
