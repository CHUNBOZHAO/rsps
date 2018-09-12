package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestUserDao {

    /**
     * 获取所有包装箱信息
     * @return
     */
    List<BoxInfo> queryAllBoxes(@Param("boxBaseTableName") String boxBaseTableName);

}
