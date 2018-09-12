package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.EnterpriseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * 企业信息DAO
 */
@Repository
public interface EnterpriseDao {

    List<EnterpriseInfo> getAllEnterprises();

    // 通过企业id获取企业企业信息
    EnterpriseInfo getEnterpriseInfoById(String entId);

}
