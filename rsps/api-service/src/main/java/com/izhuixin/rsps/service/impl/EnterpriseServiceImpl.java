package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.constant.Constants;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.util.IdBuilder;
import com.izhuixin.rsps.common.util.PasswordUtils;
import com.izhuixin.rsps.dao.manual.EnterpriseDao;
import com.izhuixin.rsps.dao.manual.SysPermissionDao;
import com.izhuixin.rsps.dao.manual.SysUserDao;
import com.izhuixin.rsps.domain.automatic.Enterprise;
import com.izhuixin.rsps.domain.manual.EnterpriseAndUserInfo;
import com.izhuixin.rsps.domain.manual.EnterpriseInfo;
import com.izhuixin.rsps.domain.manual.SysUserInfo;
import com.izhuixin.rsps.service.EnterpriseService;
import com.izhuixin.rsps.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class EnterpriseServiceImpl extends AbstractCrudService<Enterprise> implements EnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysPermissionDao permissionDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 检测用户名属于所属企业
     * @param useName
     * @return
     */
    @Override
    public String checkUserName(String useName) {
        String entCode = "";
        if (StringUtils.isNotBlank(useName)) {
            String[] userNameArray = useName.split("_");
            if (userNameArray.length > 0) {
                entCode = userNameArray[0];
                FilterExample fe = new FilterExample();
                fe.createCriteria().andFieldEqualTo("ent_code", entCode.toLowerCase()).andFieldEqualTo("status", 1);
                long count = count(fe);
                if (count <= 0) {
                    entCode = "";
                }
            }
        }
        return entCode;
    }

    /**
     * 通过用户名获取企业信息
     * @param userName
     * @return
     */
    @Override
    public Enterprise getEntByUserName(String userName) {
        Enterprise enterprise = null;
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("user_name", userName).andFieldEqualTo("status", 1);
        try {
            Optional<Enterprise> optional = get(fe);
            if (optional.isPresent()) {
                enterprise = optional.get();
            }
        } catch (Exception e) {
            logger.error(String.format("通过企业用户名(%s)获取企业信息出现异常", userName), e);
        }
        return enterprise;
    }

//    @Override
//    public String getEntCodeByPadId(String padId) {
//        String entCode = "";
//        try {
//            if (StringUtils.isNotBlank(padId)) {
//                EnterpriseInfo entInfo = enterpriseDao.getEnterpriseByPadId(padId);
//                if (entInfo != null) {
//                    entCode = entInfo.getEntCode();
//                }
//            } else {
//                entCode = "jhyt";  // 如果为空则默认为金华英特
//            }
//        } catch (Exception e) {
//            logger.error(String.format("通过PAD ID(%s)获取企业编码出现异常", padId),  e);
//        }
//        return entCode;
//    }

    @Override
    public String getEntIdByEntCode(String entCode) {
        String entId = "";
        try {
            if (StringUtils.isNotBlank(entCode)) {
                EnterpriseInfo entInfo = enterpriseDao.getEnterpriseByEntCode(entCode);
                if (entInfo != null) {
                    entId = entInfo.getEntId();
                }
            } else {
                entId = "10001"; // 如果为空则默认为金华英特
            }
        } catch (Exception e) {
            logger.error(String.format("通过企业编码(%s)获取企业ID出现异常", entCode),  e);
        }
        return entId;
    }

    @Override
    public List<String> getEntCodes() {
        List<String> entCodes = Lists.newArrayList();
        try {
            List<Enterprise> enterprises = getList();
            if (enterprises != null) {
                for (Enterprise enterprise : enterprises) {
                    entCodes.add(enterprise.getEntCode().concat("_"));
                }
            }
        } catch (Exception e) {
            logger.error(String.format("通过条件获取企业编码出现异常"));
        }
        return entCodes;
    }

    @Override
    public String getEntCodeByBoxId(String boxId) {
        String entCode  = "";
        try {
            entCode = enterpriseDao.getEntCodeByBoxId(boxId);
        } catch (Exception e) {
            logger.error(String.format("通过包装箱ID(%s)获取所属企业编码出现异常", boxId), e);
        }
        return entCode;
    }

    /***
     * 检测用户密码
     * @param id
     * @param oldPwd
     * @return
     */
    @Override
    public boolean checkPwd(Integer id, String oldPwd) {
        boolean res = false;
        try {
            Integer count = enterpriseDao.checkPwd(id, PasswordUtils.md5(oldPwd));
            if (count > 0) {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("检测用户(%d)密码出现异常", id), e);
        }
        return res;
    }

    /**
     * 通过用户ID修改用户密码
     * @param id
     * @param newPwd
     * @return
     */
    @Override
    public boolean updatePwd(Integer id, String newPwd) {
        boolean res = false;
        try {
            enterpriseDao.updatePwd(id, PasswordUtils.md5(newPwd));
            res = true;
        } catch (Exception e) {
            logger.error(String.format("通过用户ID(%d)修改密码出现异常", id), e);
        }
        return res;
    }

    @Override
    public Byte getAccessWayByEntCode(String entCode) {
        Byte accessWay = -1;
        try {
            EnterpriseInfo entInfo = enterpriseDao.getEnterpriseByEntCode(entCode);
            if (entInfo != null && entInfo.getAccessWay() != null) {
                accessWay = entInfo.getAccessWay();
            }
        } catch (Exception e) {
            logger.error(String.format("通过企业编码(%s)获取企业接入方式出现异常", entCode),  e);
        }
        return accessWay;
    }

    /**
     * 检测企业编码
     * @param entCode
     * @return
     */
    @Override
    public boolean checkEntCode(String entCode) {
        boolean res = false;
        try {
            Integer count = enterpriseDao.countByEntCode(entCode);
            if (count.intValue() == 1) {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("查询企业编码(%s)是否存在出现异常", entCode), e);
        }
        return res;
    }

    /**
     * 查询企业
     * @return
     */
    @Override
    public List<EnterpriseAndUserInfo> getAllEnterpriseAndUserInfo() {
        return enterpriseDao.getAllEnterpriseAndUserInfo();
    }

    @Override
    public boolean addEnterpriseAndUser(EnterpriseAndUserInfo enterpriseAndUserInfo) {

        //获取下一个企业id(ent_id)
        String ent_id = String.valueOf(Integer.parseInt(enterpriseDao.getLastEnt_id())+1);

        //获取企业
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEntId(ent_id);
        enterpriseInfo.setEntName(enterpriseAndUserInfo.getEntName());
        enterpriseInfo.setEntAddress(enterpriseAndUserInfo.getEntAddress());
        enterpriseInfo.setEntCode(enterpriseAndUserInfo.getEntCode());

        //获取用户
        /*user_id,user_name,user_pwd,nickname,tel,email,level,ent_id,pid*/
        SysUserInfo sysUserInfo = new SysUserInfo();

        sysUserInfo.setUserId(IdBuilder.geneate());
        sysUserInfo.setUserName(enterpriseAndUserInfo.getUserName());
        sysUserInfo.setUserPwd(PasswordUtils.md5(Constants.OPERATOR_DEFAULT_PASSWORD));
        sysUserInfo.setNickname(enterpriseAndUserInfo.getNickName());
        sysUserInfo.setTel(enterpriseAndUserInfo.getTel());
        sysUserInfo.setEmail(enterpriseAndUserInfo.getEmail());
        sysUserInfo.setLevel((byte)1);
        sysUserInfo.setEntId(ent_id);
        sysUserInfo.setPid(enterpriseAndUserInfo.getPid());


        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                boolean res = false;
                try {
                    if (enterpriseAndUserInfo != null) {

                        //添加企业
                        enterpriseDao.addEnterprise(enterpriseInfo);
                        //添加企业用户
                        enterpriseDao.addEnterpriseUser(sysUserInfo);

                        //创建企业业务表
                        enterpriseDao.createEnterpriseTable(enterpriseInfo.getEntCode());

                        res = true;
                    }
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    logger.error(String.format("新增企业出错(%s)", enterpriseAndUserInfo.getUserId()), e);
                }
                return res;
            }


        });
    }


    /**
     * 检测企业名是否重复
     * @param entName
     * @return
     */
    @Override
    public boolean checkEntName(String entName,String entId) {

        boolean flag = false;

        if(StringUtils.isNotBlank(entId)){

            try{
                Integer count  = enterpriseDao.checkEntNameForEdit(entName,entId);
                if (count != null && count == 1) {

                    flag = true;
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }else{
            try{
                Integer count = enterpriseDao.checkEntName(entName);
                if (count != null && count == 1) {

                    flag = true;
                }
            }catch (Exception e){

                e.printStackTrace();
            }
        }

        return flag;
    }

    /**
     * 更新企业
     * @param enterpriseAndUserInfo
     * @return
     */
    @Override
    public boolean updateEnterpriseAndUserInfo(EnterpriseAndUserInfo enterpriseAndUserInfo) {

        //获取企业
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEntId(enterpriseAndUserInfo.getEntId());
        enterpriseInfo.setEntName(enterpriseAndUserInfo.getEntName());
        enterpriseInfo.setEntAddress(enterpriseAndUserInfo.getEntAddress());
        enterpriseInfo.setEntCode(enterpriseAndUserInfo.getEntCode());


        //获取用户
        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setUserName(enterpriseAndUserInfo.getUserName());
        sysUserInfo.setEntId(enterpriseAndUserInfo.getEntId());
        sysUserInfo.setEmail(enterpriseAndUserInfo.getEmail());
        sysUserInfo.setTel(enterpriseAndUserInfo.getTel());
        sysUserInfo.setNickname(enterpriseAndUserInfo.getNickName());

        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                boolean res = false;
                try {
                    if (enterpriseAndUserInfo != null) {

                        //修改企业
                        enterpriseDao.updateEnterprise(enterpriseInfo);
                        //修改企业用户
                        enterpriseDao.updateEnterpriseUser(sysUserInfo);
                        res = true;
                    }
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    logger.error(String.format("修改企业出错(%s)", enterpriseAndUserInfo.getUserId()), e);
                }
                return res;
            }


        });
    }

    /**
     * 删除企业
     * @param entId
     * @return
     */
    @Override
    public boolean deleteEnterprise(String entId,String userId) {

        String entCode = enterpriseDao.getEntCodeByEntId(entId);

        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                boolean res = false;
                try {
                    if (entId != null) {
                        sysUserService.deleteUser(userId);
                        //删除企业
                        enterpriseDao.deleteEnterprise(entId);
                        //删除用户
                        sysUserDao.deleteSysUser(userId);
                        //删除企业表
                        enterpriseDao.dropEnterpriseTable(entCode);
                        res = true;
                    }
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    logger.error(String.format("删除企业出错(%s)", entId,userId), e);
                }
                return res;
            }


        });

    }

    /**
     * 根据entId获取企业
     * @param entId
     * @return
     */
    @Override
    public EnterpriseAndUserInfo getEnterpriseAndUserInfoByEntId(String entId) {

        return enterpriseDao.getEnterpriseAndUserInfoByEntId(entId);
    }

    /**
     * 递归获取子企业
     * @param userId
     * @return
     */
    @Override
    public List<EnterpriseAndUserInfo> getDeepEnterpriseAndUserInfo(String userId) {
        List<EnterpriseAndUserInfo> userInfos = Lists.newArrayList();
        getChildEnterpriseAndUserInfo(userId, userInfos);
        return userInfos;
    }

    /**
     * 递归获取该用户下所有子用户信息
     * @param userId
     * @param
     */
    private void getChildEnterpriseAndUserInfo(String userId, List<EnterpriseAndUserInfo> enterpriseAndUserInfos) {

        List<EnterpriseAndUserInfo> tempEnterpriseUserInfos = enterpriseDao.getAllEnterpriseAndUserInfoByUserId(userId);
        enterpriseAndUserInfos.addAll(tempEnterpriseUserInfos);
        for (EnterpriseAndUserInfo item : tempEnterpriseUserInfos) {
            getChildEnterpriseAndUserInfo(item.getUserId(), enterpriseAndUserInfos);
        }
    }

    @Override
    public EnterpriseInfo getEnterpriseInfoByEntName(String entName) {
        return enterpriseDao.getEnterpriseInfoByEntName(entName);
    }
}
