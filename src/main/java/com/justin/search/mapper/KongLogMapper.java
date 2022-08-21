package com.justin.search.mapper;


import com.justin.search.model.KongLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface KongLogMapper {

    int insertSelective(KongLog record);

    KongLog selectByPrimaryKey(Long id);
}
