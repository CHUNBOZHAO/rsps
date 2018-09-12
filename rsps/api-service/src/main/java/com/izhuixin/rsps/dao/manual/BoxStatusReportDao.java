package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxStatusReportInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoxStatusReportDao {

    /***
     * 获取指定时间段的包装箱操作记录每小时报表信息
     * @param entCode
     * @param beginDate
     * @param endDate
     * @return
     */
    List<BoxStatusReportInfo> getHourlyInfos(@Param("entCode") String entCode,
                                             @Param("beginDate") String beginDate,
                                             @Param("endDate") String endDate);

    /***
     * 获取指定时间段的包装箱操作记录每天报表信息
     * @param entCode
     * @param beginDate
     * @param endDate
     * @return
     */
    List<BoxStatusReportInfo> getDailyInfos(@Param("entCode") String entCode,
                                            @Param("beginDate") String beginDate,
                                            @Param("endDate") String endDate);


    /**
     * 获取指定时间段内每小时包装箱的瞬时状态数据
     * @param entCode
     * @param beginDate
     * @param endDate
     * @return
     */
    List<BoxStatusReportInfo> getTransientHourlyInfos(@Param("entCode") String entCode,
                                                      @Param("beginDate") String beginDate,
                                                      @Param("endDate") String endDate);


    /**
     * 获取指定时间段内的数据
     * @param entCode
     * @param beginDate
     * @param endDate
     * @return
     */
    BoxStatusReportInfo getTotalReport(@Param("entCode") String entCode,
                                       @Param("beginDate") String beginDate,
                                       @Param("endDate") String endDate);

    /***
     * 保存报表
     * @param boxStatusReportInfo
     * @return
     */
    boolean saveReport(@Param("boxStatusReportInfo") BoxStatusReportInfo boxStatusReportInfo,
                       @Param("entCode") String entCode);


    /**
     * 检测重复报表数据
     * @param type
     * @param generateTime
     * @param entCode
     * @return
     */
    long checkRepeat(@Param("type") Byte type,
                     @Param("generateTime") String generateTime,
                     @Param("entCode") String entCode);

}
