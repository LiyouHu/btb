package com.btb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: ProcessDefinitionController 
 * @Description: 流程定义管理
 * @author: huliyou
 * @date: 2018年11月2日 下午7:45:39
 */
@Controller
public class ProcessDefinitionController {
	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * 流程定义部署
	 */
	@RequestMapping("/processDefinition/deploy")
	public String deploy(MultipartFile zipFile) {
		InputStream inputStream = null;
		try {
			inputStream = zipFile.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		deploymentBuilder.addZipInputStream(zipInputStream);
		deploymentBuilder.deploy();
		return "redirect:/processDefinition/list" ;
	}
	
	/**
	 *	流程定义列表
	 **/
	@RequestMapping("/processDefinition/list")
	public String list(Model model) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.latestVersion();	//查询最新版本
		query.orderByProcessDefinitionName().desc();	//按照流程定义名称降序		
		List<ProcessDefinition> list = query.list();	//执行查询
		model.addAttribute("list", list);
		return "processdefinition_list";
	}
	
	/**
	 * 显示流程定义图片
	 * @throws IOException 
	 */
	@RequestMapping("/processDefinition/showPng")
	public void showPng(@RequestParam(defaultValue="")String id,HttpServletResponse response) throws Exception {
		//根据流程定义id取得流程定义图片输入流
		InputStream pngStream = repositoryService.getProcessDiagram(id);
		byte[] img = new byte[1024] ;
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		try {
			while(pngStream.read(img) != -1) {
				out.write(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 删除流程定义
	 */
	@RequestMapping("/processDefinition/delete")
	public String delete(@RequestParam(defaultValue="")String id,Model model) {
		//创建一个流程定义查询
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		definitionQuery.processDefinitionId(id);	//设置条件
		//查询到流程定义
		ProcessDefinition processDefinition = definitionQuery.singleResult();
		//得到流程部署id
		String deploymentId = processDefinition.getDeploymentId();
		//根据流程部署id删除流程定义
		try {
			repositoryService.deleteDeployment(deploymentId);
			//删除成功 重新执行查询
			return "redirect:/processDefinition/list" ;
		}catch(Exception e) {
			//删除失败,存在级联关系（流程正在被使用）
			e.printStackTrace();
			model.addAttribute("deltag", "1");
			ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
			query.latestVersion();	//查询最新版本
			query.orderByProcessDefinitionName().desc();	//按照流程定义名称降序		
			List<ProcessDefinition> list = query.list();	//执行查询
			model.addAttribute("list", list);
			return "processdefinition_list";
		}
	}
}
