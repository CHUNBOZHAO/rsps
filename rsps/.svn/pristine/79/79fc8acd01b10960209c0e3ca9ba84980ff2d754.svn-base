package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.SqlProvider.SqlProvider;
import com.izhuixin.rsps.common.vo.app.AppReqBoxes;
import com.izhuixin.rsps.common.vo.app.AppResBox;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RestDao {

    @SelectProvider(type=SqlProvider.class,method="queryBoxes")
    @Results({
                    @Result(id = true, column = "rfid", property = "boxId"),
                    @Result(column = "status", property = "boxStatus"),
                    @Result(column = "operator_id", property = "operatorId"),
                    @Result(column = "box_name", property = "boxName")
            })
    List<AppResBox> queryBoxes(AppReqBoxes state);


}
