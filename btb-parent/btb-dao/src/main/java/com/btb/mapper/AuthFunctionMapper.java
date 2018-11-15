package com.btb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.btb.pojo.AuthFunction;
import com.btb.pojo.AuthFunctionExample;

public interface AuthFunctionMapper {
    int countByExample(AuthFunctionExample example);

    int deleteByExample(AuthFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(AuthFunction record);

    int insertSelective(AuthFunction record);

    List<AuthFunction> selectByExample(AuthFunctionExample example);

    AuthFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AuthFunction record, @Param("example") AuthFunctionExample example);

    int updateByExample(@Param("record") AuthFunction record, @Param("example") AuthFunctionExample example);

    int updateByPrimaryKeySelective(AuthFunction record);

    int updateByPrimaryKey(AuthFunction record);
    /**
    * @Title: selectByEmp 
    * @Description:根据用户取得所有的权限
    * @param eid
    * @return List<AuthFunction>
    * @author huliyou
    * @date 2018年10月31日下午6:43:16
     */
    List<AuthFunction> selectByEmp(Long eid);
   
    /**
    * @Title: selectFunctionListMenuByEmp 
    * @Description:查询生成菜单项的AuthFunction 根据雇员的权限来生成
    * @return List<AuthFunction>
    * @author huliyou
    * @date 2018年10月31日下午10:19:37
     */
	List<AuthFunction> selectFunctionListMenuByEmp(Long eid);
}