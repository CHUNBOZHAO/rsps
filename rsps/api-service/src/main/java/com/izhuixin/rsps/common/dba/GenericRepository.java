package com.izhuixin.rsps.common.dba;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wd
 *
 */
@Repository
public interface GenericRepository<T> {

	long countByExample(FilterExample example);

	long deleteByExample(FilterExample example);

	long deleteByPrimaryKey(Long id);

	long insert(T record);

	long insertSelective(T record);

	List<T> selectByExampleWithBLOBsWithRowbounds(FilterExample example, RowBounds rowBounds);

	List<T> selectByExampleWithBLOBs(FilterExample example);

	List<T> selectByExampleWithRowbounds(FilterExample example, RowBounds rowBounds);

	List<T> selectByExample(FilterExample example);

	T selectByPrimaryKey(Long id);

	long updateByExampleSelective(@Param("record") T record, @Param("example") FilterExample example);

	long updateByExampleWithBLOBs(@Param("record") T record, @Param("example") FilterExample example);

	long updateByExample(@Param("record") T record, @Param("example") FilterExample example);

	long updateByPrimaryKeySelective(T record);

	long updateByPrimaryKeyWithBLOBs(T record);

	long updateByPrimaryKey(T record);

}
