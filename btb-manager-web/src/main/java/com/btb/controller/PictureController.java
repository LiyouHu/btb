package com.btb.controller;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.btb.common.utils.IPTimeStamp;

/**
 * 图片管理
 * @ClassName: PictureController 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年9月22日 下午9:57:44
 */
@Controller
public class PictureController {
	@Value("${ITEM_PIC_LOCATION}")
	private String picLocation;
	@Value("${ITEM_PIC_LINK}")
	private String ITEM_PIC_LINK;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String updateFile(MultipartFile uploadFile,HttpServletRequest request) {
		//图片的访问地址
		Map<String,Object> map = new HashMap<String,Object>(); ;
		try {
			String url ="";	//图片的访问路径
			String originalFilename = uploadFile.getOriginalFilename();
			//取得扩展名包含“.”
			String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			IPTimeStamp its = new IPTimeStamp(request.getRemoteAddr());
			String randName = its.getIPTimeStampRand()+extName;	//新的图片名称
			InputStream inputStream = uploadFile.getInputStream();
			byte[] data = FileUtil.readAsByteArray(inputStream);
			File file = new File(picLocation+File.separator+randName);
			FileUtils.writeByteArrayToFile(file , data);
			url = ITEM_PIC_LINK+randName;
			map.put("error", 0);
			map.put("url", url);
			return JSONUtils.toJSONString(map) ;

		}catch(Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "图片上传失败！");
			return JSONUtils.toJSONString(map);
		}
	}
	
	
}
