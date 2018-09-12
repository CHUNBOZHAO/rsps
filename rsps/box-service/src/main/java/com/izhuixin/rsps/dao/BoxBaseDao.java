package com.izhuixin.rsps.dao;


import org.apache.ibatis.annotations.Update;

public interface BoxBaseDao {
//    @Update("update rspsdb.rsps_box_base set comm_num=comm_num+1 where uuid=#{uuid}")
    @Update("update rspsdb.rsps_box_detail set communicate_count=communicate_count+1 where uuid=#{uuid}")
    boolean updateCommunicateNum(String uuid);
}
