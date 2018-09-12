package com.izhuixin.rsps.service.impl;

import com.google.gson.Gson;
import com.izhuixin.rsps.common.constant.ErrorCode;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.vo.api.ApiAckContent;
import com.izhuixin.rsps.common.vo.api.SystemParamReq;
import com.izhuixin.rsps.domain.automatic.SystemParam;
import com.izhuixin.rsps.service.SysParamService;
import org.springframework.stereotype.Service;


@Service
public class SysParamServiceImpl extends AbstractCrudService<SystemParam> implements SysParamService {

    /**
     * 通过名称修改参数信息
     * @param systemParamReq
     * @return
     */
    @Override
    public String updateSysParam(SystemParamReq systemParamReq) {
        SystemParam systemParam = new SystemParam();
        systemParam.setParamName(systemParamReq.getParamName());
        systemParam.setParamValue(systemParamReq.getParamValue());
        systemParam.setParamDescr(systemParamReq.getParamDescr());
        systemParam.setParamStatus(systemParamReq.getParamStatus());
        systemParam.setOperatorId(systemParamReq.getOperatorId());
        systemParam.setParamRemark(systemParamReq.getParamRemark());

        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("param_name", systemParam.getParamName());
        long res = 0;
        try {
             res = update(systemParam, fe);
        } catch (Exception e) {
            logger.error("更新系统参数出现异常", e);
        }

        ApiAckContent apiAckContent = new ApiAckContent();
        if (res > 0) {
            apiAckContent.setStatus(ErrorCode.OK.getIndex().toString());
            apiAckContent.setMessage(ErrorCode.OK.getDescr());
        } else {
            apiAckContent.setStatus(ErrorCode.ERROR.getIndex().toString());
            apiAckContent.setMessage(ErrorCode.ERROR.getDescr());
        }
        return new Gson().toJson(apiAckContent);
    }

}
