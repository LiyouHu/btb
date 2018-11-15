package com.btb.mapper;

import com.btb.pojo.EmpGroup;
import com.btb.pojo.EmpGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpGroupMapper {
    int countByExample(EmpGroupExample example);

    int deleteByExample(EmpGroupExample example);

    int deleteByPrimaryKey(Long gid);

    int insert(EmpGroup record);

    int insertSelective(EmpGroup record);

    List<EmpGroup> selectByExample(EmpGroupExample example);

    EmpGroup selectByPrimaryKey(Long gid);

    int updateByExampleSelective(@Param("record") EmpGroup record, @Param("example") EmpGroupExample example);

    int updateByExample(@Param("record") EmpGroup record, @Param("example") EmpGroupExample example);

    int updateByPrimaryKeySelective(EmpGroup record);

    int updateByPrimaryKey(EmpGroup record);
}