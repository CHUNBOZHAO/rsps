package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxBaseInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoxBaseDao {

    /**
     * 获取boxbase下的所有的包装箱
     */
    List<BoxBaseInfo> getAllBoxBaseInfo();

    /**
     * 添加包装箱
     */
    boolean saveBox(BoxBaseInfo boxBaseInfo);

    /**
     * 更新包装箱
     */
    boolean updateBox(BoxBaseInfo baseInfo);

    /**
     * 保存rfid重名校验
     */
    Integer checkRfidForSave(String rfid);

    /**
     * 编辑重名校验
     */
    Integer checkRfidForUpdate(String rfid, Integer id);

    /**
     * 删除包装箱
     */
    boolean deleteBox(String id);

    /**
     * 通过id获取包装箱
     */
    BoxBaseInfo getBoxBaseInfoById(Integer id);

    /**
     * 获取boxbase总数
     */
    Integer getboxCount();

    /**
     * 分页查询
     */
    List<BoxBaseInfo> getBoxBaseInfoByPage(@Param("start") Integer start,@Param("length") Integer length, @Param("search") String search);

    /**
     * count过滤后的数量
     */
    Integer countBoxBaseInfoByPage(@Param("start") Integer start,@Param("length") Integer length, @Param("search") String search);

    /**
     * 通过BoxId获取epcId
     *
     */
    String getEpcId(String rfid);
}
