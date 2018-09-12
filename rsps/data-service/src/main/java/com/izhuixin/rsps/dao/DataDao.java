package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.DataEfficiency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDao {


    //获取总体包装箱当月30天数据
    List<DataEfficiency> getEfficiency(@Param("entId") String entId, @Param("type") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
