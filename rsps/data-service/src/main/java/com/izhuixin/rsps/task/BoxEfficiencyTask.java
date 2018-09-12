package com.izhuixin.rsps.task;

import com.izhuixin.rsps.constant.ReportType;
import com.izhuixin.rsps.dao.BoxDao;
import com.izhuixin.rsps.dao.BoxEfficiencyDao;
import com.izhuixin.rsps.dao.EnterpriseDao;
import com.izhuixin.rsps.domain.BoxEfficiencyInfo;
import com.izhuixin.rsps.domain.BoxStatusInfo;
import com.izhuixin.rsps.domain.EnterpriseInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 包装箱效率统计相关任务生成
 */
@Component
@EnableScheduling
public class BoxEfficiencyTask {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private BoxDao boxDao;

    @Autowired
    private BoxEfficiencyDao boxEfficiencyDao;

    /**
     * 每日效能信息生成（每天23点30分执行）
     */
    @Scheduled(cron = "0 30 23 * * ?")
    public void generateDailyBoxEfficiencyReport() {

        List<EnterpriseInfo> enterprises = null;
        try {
            enterprises = enterpriseDao.getAllEnterprises();
        } catch (Exception e) {
            logger.error("Get list of enterprise is error", e);
        }

        if (null != enterprises) {
            enterprises.forEach(enterpriseInfo -> {
                try {
                    BoxEfficiencyInfo boxEfficiencyInfo = new BoxEfficiencyInfo();
                    DateTime nowTime = DateTime.now();
                    String entCode = enterpriseInfo.getEntCode();

                    BoxStatusInfo boxStatusInfo = boxDao.getBoxStatusCountInfo(entCode.concat("_"));
                    // 计算闲置率
                    Float leisureRate = 0f;
                    BigDecimal leisureRateBd = new BigDecimal(boxStatusInfo.getLeisureCount() * 1f / boxStatusInfo.getTotal());
                    leisureRate = leisureRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                    // 计算遗失率，30天未操作
                    Float loseRate = 0f;
                    DateTime loseConfineTime = nowTime.minusDays(30);
                    int loseCount = boxDao.countMissingBox(entCode.concat("_"), loseConfineTime.toString("yyyy-MM-dd HH:mm:ss"));
                    BigDecimal loseRateBd = new BigDecimal(loseCount * 1f / boxStatusInfo.getTotal());
                    loseRate = loseRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                    // 计算过期率， 3年内不算过期
                    Float overdueRate = 0f;
                    DateTime overdueConfineTime = nowTime.minusYears(3);
                    int overdueCount = boxDao.countOverdueBox(entCode.concat("_"), overdueConfineTime.toString("yyyy-MM-dd HH:mm:ss"));
                    BigDecimal overdueRateBd = new BigDecimal(overdueCount * 1f / boxStatusInfo.getTotal());
                    overdueRate = overdueRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                    boxEfficiencyInfo.setEntId(enterpriseInfo.getEntId());
                    boxEfficiencyInfo.setGenerateDate((nowTime.withTime(0,0,0,0).toDate()));
                    boxEfficiencyInfo.setCreateTime(nowTime.toDate());
                    boxEfficiencyInfo.setIdleRate(leisureRate);
                    boxEfficiencyInfo.setLoseRate(loseRate);
                    boxEfficiencyInfo.setOverdueRate(overdueRate);
                    boxEfficiencyInfo.setType(ReportType.DAILY.getIndex().byteValue());

                    boxEfficiencyDao.saveInfo(boxEfficiencyInfo);
                } catch (Exception e) {
                    logger.error(String.format("Generate enterprise(%s) daily data is error.", enterpriseInfo.getEntId()), e);
                }
            });
        } else {
            logger.info("List of enterprise is null");
        }
    }

    /**
     * 每周效能信息生成（每周日23点45分执行）
     */
    @Scheduled(cron = "0 45 23 ? * Sun")
    public void generateWeeklyBoxEfficiencyReport() {

        DateTime nowTime = DateTime.now();
        DateTime beginTime = nowTime.withDayOfWeek(1).withTime(0,0,0,0);
        DateTime endTime = nowTime.withTime(23,59,59,0);

        generateEfficiencyData(beginTime.toDate(), endTime.toDate(), ReportType.WEEKLY.getIndex().byteValue(), endTime);
    }

    /**
     * 每月效能信息生成（每月1日0点10分执行）
     */
    @Scheduled(cron = "0 10 0 1 * ?")
    public void generateMonthlyBoxEfficiencyReport() {
        DateTime nowTime = DateTime.now();
        DateTime beginTime = nowTime.minusMonths(1).withTime(0,0,0,0);
        DateTime endTime = nowTime.minusDays(1).withTime(23,59,59,0);

        generateEfficiencyData(beginTime.toDate(), endTime.toDate(), ReportType.MONTHLY.getIndex().byteValue(), endTime);
    }


    /**
     * 每年效能信息生成（每年1月1日0点20分执行）
     */
    @Scheduled(cron = "0 20 0 1 1 *")
    public void generateYearlyBoxEfficiencyReport() {
        DateTime nowTime = DateTime.now();
        DateTime beginTime = nowTime.minusYears(1).withTime(0,0,0,0);
        DateTime endTime = nowTime.minusDays(1).withTime(23,59,59,0);

        generateEfficiencyData(beginTime.toDate(), endTime.toDate(), ReportType.YEARLY.getIndex().byteValue(), endTime);
    }

    /**
     * 生成指定条件的数据
     * @param beginTime
     * @param endTime
     * @param reportType
     */
    private void generateEfficiencyData(Date beginTime, Date endTime, byte reportType, DateTime generateTime) {

        List<EnterpriseInfo> enterprises = null;
        try {
            enterprises = enterpriseDao.getAllEnterprises();
        } catch (Exception e) {
            logger.error("Get list of enterprise is error", e);
        }
        if (null != enterprises) {
            enterprises.forEach(enterpriseInfo -> {
                try {
                    String entId = enterpriseInfo.getEntId();
                    List<BoxEfficiencyInfo> efficiencyInfos = boxEfficiencyDao.getEfficiencyInfo(ReportType.DAILY.getIndex().byteValue(),
                            entId, beginTime, endTime);
                    int size = efficiencyInfos.size();

                    // 计算闲置率
                    Float leisureRate = 0f;
                    Float loseRate = 0f;
                    Float overdueRate = 0f;

                    Float gatherLeisureRate = 0f;
                    Float gatherLoseRate = 0f;
                    Float gatherOverDueRate = 0f;
                    if (null != efficiencyInfos && efficiencyInfos.size() > 0) {
                        for (BoxEfficiencyInfo efficiencyInfo : efficiencyInfos) {
                            gatherLeisureRate += efficiencyInfo.getIdleRate();
                            gatherLoseRate += efficiencyInfo.getLoseRate();
                            gatherOverDueRate += efficiencyInfo.getOverdueRate();
                        }

                        BigDecimal leisureRateBd = new BigDecimal(gatherLeisureRate / size);
                        leisureRate = leisureRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                        BigDecimal loseRateBd = new BigDecimal(gatherLoseRate / size);
                        loseRate = loseRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                        BigDecimal overdueRateBd = new BigDecimal(gatherOverDueRate / size);
                        overdueRate = overdueRateBd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

                        BoxEfficiencyInfo boxEfficiencyInfo = new BoxEfficiencyInfo();

                        boxEfficiencyInfo.setEntId(entId);
                        boxEfficiencyInfo.setGenerateDate((generateTime.now().withTime(0,0,0,0).toDate()));
                        boxEfficiencyInfo.setCreateTime(DateTime.now().toDate());
                        boxEfficiencyInfo.setIdleRate(leisureRate);
                        boxEfficiencyInfo.setLoseRate(loseRate);
                        boxEfficiencyInfo.setOverdueRate(overdueRate);
                        boxEfficiencyInfo.setType(reportType);

                        boxEfficiencyDao.saveInfo(boxEfficiencyInfo);
                    } else {
                        logger.warn(String.format("Generate efficiency data is null."));
                    }

                } catch (Exception e) {
                    logger.error(String.format("Generate efficiency data is error."), e);
                }
            });
        } else {
            logger.info("List of enterprise is null");
        }

    }

}
