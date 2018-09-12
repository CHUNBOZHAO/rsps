package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.common.vo.app.AppReqBoxes;
import com.izhuixin.rsps.common.vo.app.AppResBox;
import com.izhuixin.rsps.common.vo.app.AppResOrders;
import com.izhuixin.rsps.dao.manual.RestDao;
import com.izhuixin.rsps.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private RestDao restDao;

    /**
     * 获取包装箱信息
     * @param req
     * @return
     */
    public AppResOrders requestBoxes(AppReqBoxes req){
        List<AppResBox> boxes = restDao.queryBoxes(req);
        AppResOrders retOrders = new AppResOrders();
        retOrders.setOrders(boxes);
        return retOrders;
    }
}
