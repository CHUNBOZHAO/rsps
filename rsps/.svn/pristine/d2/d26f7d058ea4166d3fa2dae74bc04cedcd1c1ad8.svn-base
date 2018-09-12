package com.izhuixin.authsample.service.impl;


import com.izhuixin.authsample.entity.OperatorInfoEntity;
import com.izhuixin.authsample.service.OperatorInfoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:15:13
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class OperatorInfoServiceImpl implements OperatorInfoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OperatorInfoEntity findByUsername(String operator) {
        String[] aOper = operator.split("_");
        if(aOper.length < 2){
            return null;
        }
        String sql="select * from rsps_"+aOper[0]+"_operator_info u WHERE u.user_name=?";
        //System.out.println(sql+"--------sql语句-------------");
        Query query=entityManager.createNativeQuery(sql, OperatorInfoEntity.class);

        query.setParameter(1,operator);
        OperatorInfoEntity ret = (OperatorInfoEntity)query.getSingleResult();

        //OperatorInfoEntity ret = (OperatorInfoEntity)query.getSingleResult();
        entityManager.close();
        return ret;
    }
}
