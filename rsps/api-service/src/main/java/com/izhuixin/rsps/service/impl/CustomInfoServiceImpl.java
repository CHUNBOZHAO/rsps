package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.constant.Constants;
import com.izhuixin.rsps.common.constant.ValidStatus;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.util.CustomIdBuilder;
import com.izhuixin.rsps.common.util.PasswordUtils;
import com.izhuixin.rsps.dao.manual.CustomDao;
import com.izhuixin.rsps.domain.automatic.CustomInfoDO;
import com.izhuixin.rsps.domain.automatic.Enterprise;
import com.izhuixin.rsps.service.CustomInfoService;
import com.izhuixin.rsps.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomInfoServiceImpl extends AbstractCrudService<CustomInfoDO> implements CustomInfoService {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private CustomDao customDao;

    /***
     * 检测手机号是否被使用
     * @param tel
     * @return
     */
    @Override
    public boolean checkTel(String tel) {
        boolean res = false;
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("tel", tel).andFieldEqualTo("status", ValidStatus.VALID.getIndex().byteValue());
        long count = count(fe);
        if (count > 0) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean saveCustomInfo(String tel, String password) {
        boolean res = false;
        try {
            CustomInfoDO customInfoDO = new CustomInfoDO();
            customInfoDO.setTel(tel);
            customInfoDO.setCreateTime(new Date());
            customInfoDO.setCustomAddress("");
            customInfoDO.setCustomId(CustomIdBuilder.geneate());
            customInfoDO.setCustomName(tel); // 用户昵称即显示名称
            customInfoDO.setEmail("");
            customInfoDO.setCustomPwd(PasswordUtils.md5(password));
            customInfoDO.setModifyTime(new Date());
            customInfoDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
            customInfoDO.setUserName(""); // 登录用户名
            save(customInfoDO);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存用户(%s)信息出现异常", tel), e);
        }
        return res;
    }

    @Override
    public CustomInfoDO getCustomInfo(String userName) {

        CustomInfoDO customInfoDO = null;
        try {
            // 用户名称和电话都可以登录
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("custom_id", userName).
                    andFieldEqualTo("status", ValidStatus.VALID.getIndex());
            fe.or().andFieldEqualTo("tel", userName).andFieldEqualTo("status", ValidStatus.VALID.getIndex());

            Optional<CustomInfoDO> customInfoDOOptional = get(fe);
            if (customInfoDOOptional.isPresent()) {
                customInfoDO = customInfoDOOptional.get();
            }
        } catch (Exception e) {
            logger.error(String.format("通过客户ID（%s）获取客户信息出现异常", userName), e);
        }
        return customInfoDO;
    }

    /**
     * 修改客户密码
     * @param id
     * @param newPassword
     * @return
     */
    @Override
    public boolean modifyPwd(Long id, String newPassword) {
        boolean res = false;
        try {
            CustomInfoDO infoDO = new CustomInfoDO();
            infoDO.setId(id);
            infoDO.setCustomPwd(PasswordUtils.md5(newPassword));
            update(infoDO);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("客户(%d)修改密码出现异常", id), e);
        }
        return res;
    }

    /**
     * 检测老密码是否正确
     * @param id
     * @param oldPassword
     * @return
     */
    @Override
    public boolean checkPwd(Long id, String oldPassword) {
        boolean res = false;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("id", id).andFieldEqualTo("custom_pwd", PasswordUtils.md5(oldPassword));
            long count = count(fe);
            if (count > 0) {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("检测客户(%d)密码出现异常", id), e);
        }
        return res;
    }

    /**
     * 通过扫码获取客户登录ID
     * @param qCode
     * @return
     */
    @Override
    public String getCustomInfoByQcode(String qCode) {
        JsonObject jsonObject = new JsonObject();

        String barCode = qCode;
        List<String> customIds = Lists.newArrayList();
        List<String> customId1s = null;
        List<String> customId2s = null;

        List<Enterprise> enterprises = enterpriseService.getList();
        String entId = "";
        for (Enterprise enterprise: enterprises) {
            entId = enterprise.getEntId();
            try {
                customId1s = customDao.getCustomIdsByBarcode1(barCode, enterprise.getEntCode().concat("_"));
            } catch (Exception e) {
                logger.error("获取客户ID出现异常", e);
            }

            if (customId1s == null || customId1s.isEmpty()) {
                try {
                    customId2s = customDao.getCustomIdsByBarcode2(barCode, enterprise.getEntCode().concat("_"));
                } catch (Exception e) {
                    logger.error("获取客户ID出现异常", e);
                }
            } else {
                customIds.addAll(customId1s);
                break;
            }

            if (customId2s != null && !customId2s.isEmpty()) {
                customIds.addAll(customId2s);
                break;
            }
        }

        if (!customIds.isEmpty()) {
            String customId = customIds.get(0);
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("custom_id", customId).andFieldEqualTo("ent_id", entId);
            Optional<CustomInfoDO> optional = get(fe);
            if (optional.isPresent()) {
                CustomInfoDO customInfoDO = optional.get();
                jsonObject.addProperty("returnCode", "0");
                jsonObject.addProperty("customId", customInfoDO.getCustomId());

                if (customInfoDO.getCustomPwd().equals(PasswordUtils.md5(Constants.CUSTOMER_DEFAULT_PASSWORD))) {
                    jsonObject.addProperty("customPwd", Constants.CUSTOMER_DEFAULT_PASSWORD);
                } else {
                    jsonObject.addProperty("customPwd", "");
                }

                return jsonObject.toString();
            }
        }

        jsonObject.addProperty("returnCode", "1001");
        return jsonObject.toString();
    }

}
