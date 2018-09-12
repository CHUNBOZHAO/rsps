package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.domain.automatic.CustomLinkerDO;
import com.izhuixin.rsps.service.CustomLinkerService;
import org.springframework.stereotype.Service;

@Service
public class CustomLinkerServiceImpl extends AbstractCrudService<CustomLinkerDO> implements CustomLinkerService {

    /**
     * 通过用户名称获取用户信息
     * @param userName
     * @return
     */
    @Override
    public CustomLinkerDO getCustomByName(String userName) {
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("user_name",userName);
        CustomLinkerDO customLinkerDO = null;
        try {
            Optional<CustomLinkerDO> customLinkerDOOptional =  get(fe);
            if (customLinkerDOOptional.isPresent()) {
                customLinkerDO = customLinkerDOOptional.get();
            }
        } catch (Exception e) {
            logger.error(String.format("通过客户用户名(%s)获取客户信息出现异常", userName), e);
        }
        return customLinkerDO;
    }

}
