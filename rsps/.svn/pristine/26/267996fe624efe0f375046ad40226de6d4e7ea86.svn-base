package com.izhuixin.rsps.task;

import com.izhuixin.rsps.common.constant.BoxStatus;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.dao.manual.BoxRecordDao;
import com.izhuixin.rsps.dao.manual.BoxStatusReportDao;
import com.izhuixin.rsps.domain.automatic.Enterprise;
import com.izhuixin.rsps.domain.manual.BoxStatusReportInfo;
import com.izhuixin.rsps.domain.manual.StatusReportInfo;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.BoxStatusReportService;
import com.izhuixin.rsps.service.EnterpriseService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class BoxStatusReport {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private BoxStatusReportService boxStatusDailyService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private BoxRecordDao boxRecordDao;

    @Autowired
    private BoxStatusReportDao boxStatusReportDao;

    /**
     * 统计一天的操作信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void dailyReport() {
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("status", 1);
        List<Enterprise> enterpriseList = enterpriseService.getList(fe);

        String entCode = "";
        BoxStatusReportInfo boxReportInfo = null;
        DateTime beginTime = null;
        DateTime endTime = null;
        for (Enterprise enterprise : enterpriseList) {
            entCode = enterprise.getEntCode().concat("_");

            endTime = DateTime.now();
            beginTime = endTime.minusDays(1);
//            endTime = endTime.minusSeconds(1);
            BoxStatusReportInfo boxStatusReportInfo = boxStatusReportDao.getTotalReport(entCode, beginTime.toString("yyyy-MM-dd HH:mm:ss"), endTime.toString("yyyy-MM-dd HH:mm:ss"));

            boxReportInfo = new BoxStatusReportInfo();
            Long leisureCount = boxStatusReportInfo.getLeisureNum().longValue();
            Long bindingCount = boxStatusReportInfo.getBindingNum().longValue();
            Long recyleCount = boxStatusReportInfo.getRecycleNum().longValue();
            Long retentionCount = boxStatusReportInfo.getRetentionNum().longValue();
            Long transportingCount = boxStatusReportInfo.getTransportingNum().longValue();

            boxReportInfo.setBindingNum(bindingCount.intValue());
            boxReportInfo.setGenerateDate(beginTime.toDate());
            boxReportInfo.setGenerateDateStr(beginTime.toString("yyyy-MM-dd HH:mm:ss"));
            boxReportInfo.setLeisureNum(leisureCount.intValue());
            boxReportInfo.setRecycleNum(recyleCount.intValue());
            boxReportInfo.setRetentionNum(retentionCount.intValue());
            boxReportInfo.setTransportingNum(transportingCount.intValue());
            boxReportInfo.setType((byte) 3);

            try {
                boxStatusDailyService.saveReport(boxReportInfo, entCode);
            } catch (Exception e) {
                logger.error("生成包装箱每天时操作记录报表数据出现异常", e);
            }
        }
    }

    /**
     * 统计一小时的操作信息
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void hourlyReport() {
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("status", 1);
        List<Enterprise> enterpriseList = enterpriseService.getList(fe);

        String entCode = "";
        BoxStatusReportInfo boxReportInfo = null;
        DateTime beginTime = null;
        DateTime endTime = null;
        for (Enterprise enterprise : enterpriseList) {
            entCode = enterprise.getEntCode().concat("_");

            endTime = DateTime.now();
            beginTime = endTime.minusHours(1);
            endTime = endTime.minusSeconds(1);
            BoxStatusReportInfo boxStatusReportInfo = boxStatusReportDao.getTotalReport(entCode, beginTime.toString("yyyy-MM-dd HH:mm:ss"), endTime.toString("yyyy-MM-dd HH:mm:ss"));

            boxReportInfo = new BoxStatusReportInfo();
            Long leisureCount = boxStatusReportInfo.getLeisureNum().longValue();
            Long bindingCount = boxStatusReportInfo.getBindingNum().longValue();
            Long recyleCount = boxStatusReportInfo.getRecycleNum().longValue();
            Long retentionCount = boxStatusReportInfo.getRetentionNum().longValue();
            Long transportingCount = boxStatusReportInfo.getTransportingNum().longValue();

            boxReportInfo.setBindingNum(bindingCount.intValue());
            boxReportInfo.setGenerateDate(new Date());
            boxReportInfo.setGenerateDateStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            boxReportInfo.setLeisureNum(leisureCount.intValue());
            boxReportInfo.setRecycleNum(recyleCount.intValue());
            boxReportInfo.setRetentionNum(retentionCount.intValue());
            boxReportInfo.setTransportingNum(transportingCount.intValue());
            boxReportInfo.setType((byte) 2);

            try {
                boxStatusDailyService.saveReport(boxReportInfo, entCode);
            } catch (Exception e) {
                logger.error("生成包装箱每小时操作记录报表数据出现异常", e);
            }
        }
    }


    /**
     * 分钟级操作记录统计
     */
    @Scheduled(cron = "0 * * * * ?")
    public void minutelyReport() {
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("status", 1);
        List<Enterprise> enterpriseList = enterpriseService.getList(fe);

        String entCode = "";
        BoxStatusReportInfo boxReportInfo = null;
        DateTime beginTime = null;
        DateTime endTime = null;
        for (Enterprise enterprise : enterpriseList) {
            entCode = enterprise.getEntCode().concat("_");

            endTime = DateTime.now();
            beginTime = endTime.minusMinutes(1);
            endTime = endTime.minusSeconds(1);
            List<StatusReportInfo> listStatus = boxRecordDao.getSectionRecord(entCode, beginTime.toString("yyyy-MM-dd HH:mm:ss"), endTime.toString("yyyy-MM-dd HH:mm:ss"));

            Integer leisureCount = 0;
            Integer bindingCount = 0;
            Integer recyleCount = 0;
            Integer retentionCount = 0;
            Integer transportingCount = 0;
            for (StatusReportInfo reportInfo : listStatus) {
                if (reportInfo.getStatus().intValue() == BoxStatus.LEISURE.getIndex().intValue()) {
                    leisureCount += reportInfo.getCount();
                } else if (reportInfo.getStatus().intValue() == BoxStatus.BINDING.getIndex().intValue()) {
                    bindingCount += reportInfo.getCount();
                } else if (reportInfo.getStatus().intValue() == BoxStatus.TRANSPORTING.getIndex().intValue()) {
                    transportingCount += reportInfo.getCount();
                } else if (reportInfo.getStatus().intValue() == BoxStatus.RETENTION.getIndex().intValue()) {
                    retentionCount += reportInfo.getCount();
                } else if (reportInfo.getStatus().intValue() == BoxStatus.RECYCLE.getIndex().intValue()) {
                    recyleCount += reportInfo.getCount();
                } else if (reportInfo.getStatus().intValue() == BoxStatus.TRANSIT.getIndex().intValue()) {
                    transportingCount += reportInfo.getCount();
                }

            }
            boxReportInfo = new BoxStatusReportInfo();
            boxReportInfo.setBindingNum(bindingCount.intValue());
            boxReportInfo.setGenerateDate(new Date());
            boxReportInfo.setGenerateDateStr(DateTime.now().toString("yyyy-MM-dd HH:mm:00"));
            boxReportInfo.setLeisureNum(leisureCount.intValue());
            boxReportInfo.setRecycleNum(recyleCount.intValue());
            boxReportInfo.setRetentionNum(retentionCount.intValue());
            boxReportInfo.setTransportingNum(transportingCount.intValue());
            boxReportInfo.setType((byte)1);

            try {
                boolean res = boxStatusDailyService.checkRepeat((byte)1, DateTime.now().toString("yyyy-MM-dd HH:mm:00"), entCode);
                if (!res) {
                    boxStatusDailyService.saveReport(boxReportInfo, entCode);
                }
            } catch (Exception e) {
                logger.error("生成每分钟中包装箱操作记录报表数据出现异常", e);
            }
        }
    }

    /**
     * 统计整点包装箱状态数量
     */
    @Scheduled(cron = "0 5 */1 * * ?")
    public void hourlyBoxStatusReport() {

        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("status", 1);
        List<Enterprise> enterpriseList = enterpriseService.getList(fe);

        String entCode = "";
        BoxStatusReportInfo boxStatusHourlyInfo = null;
        for (Enterprise enterprise : enterpriseList) {
            entCode = enterprise.getEntCode().concat("_");

            boxStatusHourlyInfo = new BoxStatusReportInfo();
            Long leisureCount = boxInfoService.countLeisureBoxes(entCode);
            Long bindingCount = boxInfoService.countBindingBoxes(entCode);
            Long recyleCount = boxInfoService.countRecycleBoxes(entCode);
            Long retentionCount = boxInfoService.countRetentionBoxes(entCode);
            Long transportingCount = boxInfoService.countTransportingBoxes(entCode);

            boxStatusHourlyInfo.setBindingNum(bindingCount.intValue());
            boxStatusHourlyInfo.setGenerateDate(new Date());
            boxStatusHourlyInfo.setGenerateDateStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            boxStatusHourlyInfo.setLeisureNum(leisureCount.intValue());
            boxStatusHourlyInfo.setRecycleNum(recyleCount.intValue());
            boxStatusHourlyInfo.setRetentionNum(retentionCount.intValue());
            boxStatusHourlyInfo.setTransportingNum(transportingCount.intValue());
            boxStatusHourlyInfo.setType((byte)22);

            try {
                boxStatusDailyService.saveReport(boxStatusHourlyInfo, entCode);
            } catch (Exception e) {
                logger.error("生成包装箱状态时报表数据出现异常", e);
            }
        }

    }

}
