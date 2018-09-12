package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.vo.web.BoxTypeVO;
import com.izhuixin.rsps.dao.manual.BoxTypeDao;
import com.izhuixin.rsps.domain.automatic.BoxType;
import com.izhuixin.rsps.domain.manual.BoxTypeInfo;
import com.izhuixin.rsps.service.BoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxTypeServiceImpl extends AbstractCrudService<BoxType> implements BoxTypeService {

    @Autowired
    private BoxTypeDao boxTypeDao;

    @Override
    public List<BoxTypeVO> getBoxTypes() {
        List<BoxTypeVO> boxTypeVOS = Lists.newArrayList();
        try {
            List<BoxType> boxTypes = getList();
            if (boxTypes != null) {
                BoxTypeVO boxTypeVO = null;
                for (BoxType boxType : boxTypes) {
                    boxTypeVO = new BoxTypeVO();
                    boxTypeVO.setTypeId(boxType.getTypeId());
                    boxTypeVO.setName(boxType.getName());
                    boxTypeVO.setSize(boxType.getSize());
                    boxTypeVO.setColor(boxType.getColor());
                    boxTypeVO.setCount(1);
                    boxTypeVOS.add(boxTypeVO);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("获取包装箱型号出现异常"), e);
        }
        return boxTypeVOS;
    }

    @Override
    public BoxTypeVO getBoxType(String boxId) {
        BoxTypeVO boxTypeVO = null;
        try {
            BoxTypeInfo boxTypeInfo = boxTypeDao.getBoxType(boxId);
            if (boxTypeInfo != null) {
                boxTypeVO = new BoxTypeVO();
                boxTypeVO.setTypeId(boxTypeInfo.getTypeId());
                boxTypeVO.setName(boxTypeInfo.getName());
                boxTypeVO.setColor(boxTypeInfo.getColor());
                boxTypeVO.setSize(boxTypeInfo.getSize());
            }
        } catch (Exception e) {
            logger.error(String.format("通过包装ID(%s)获取包装箱型号信息出现异常", boxId), e);
        }
        return boxTypeVO;
    }

}
