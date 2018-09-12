package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.vo.web.AddressBookVO;
import com.izhuixin.rsps.domain.automatic.AddressBook;
import com.izhuixin.rsps.service.AddressBookService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressBookServiceImpl extends AbstractCrudService<AddressBook> implements AddressBookService {

    /**
     * 保存地址簿
     * @param addressBookVO
     * @return
     */
    @Override
    public boolean saveAddressBook(AddressBookVO addressBookVO, String customId) {
        boolean res = false;
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.setCustomId(customId);
            addressBook.setName(addressBookVO.getName());
            addressBook.setTel(addressBookVO.getTel());
            addressBook.setAreaProvince(addressBookVO.getAreaProvince());
            addressBook.setAreaCity(addressBookVO.getAreaCity());
            addressBook.setAreaCounty(addressBookVO.getAreaCounty());
            addressBook.setDetailAddress(addressBookVO.getDetailAddress());
            addressBook.setDefaultSelect((byte) 0);
            addressBook.setCreateDate(new Date());
            addressBook.setModifyDate(new Date());
            this.save(addressBook);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存客户(%s)地址簿出现异常", customId), e);
        }
        return res;
    }

    /**
     * 保存地址簿
     * @param addressBookVO
     * @return
     */
    @Override
    public boolean updateAddressBook(AddressBookVO addressBookVO, String customId) {
        boolean res = false;
        try {
            AddressBook addressBook = new AddressBook();
            addressBook.setName(addressBookVO.getName());
            addressBook.setTel(addressBookVO.getTel());
            addressBook.setAreaProvince(addressBookVO.getAreaProvince());
            addressBook.setAreaCity(addressBookVO.getAreaCity());
            addressBook.setAreaCounty(addressBookVO.getAreaCounty());
            addressBook.setDetailAddress(addressBookVO.getDetailAddress());
            addressBook.setModifyDate(new Date());

            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("id", addressBookVO.getId()).andFieldEqualTo("custom_id", customId);

            long updateRes = this.update(addressBook, fe);
            if (updateRes > 0) {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("修改客户(%s)地址簿出现异常", customId), e);
        }
        return res;
    }

    /**
     * 获取用户地址簿列表
     * @param customId
     * @return
     */
    @Override
    public List<AddressBookVO> getAddressBooks(String customId) {
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("custom_id", customId);
        fe.setOrderByClause("default_select desc");
        List<AddressBookVO> addressBooks = Lists.newArrayList();

        try {
            List<AddressBook> abs = getList(fe);
            AddressBookVO addressBookVO = null;
            for (AddressBook addressBook : abs) {
                addressBookVO = new AddressBookVO();
                addressBookVO.setId(addressBook.getId());
                addressBookVO.setName(addressBook.getName());
                addressBookVO.setTel(addressBook.getTel());
                addressBookVO.setAreaProvince(addressBook.getAreaProvince());
                addressBookVO.setAreaCity(addressBook.getAreaCity());
                addressBookVO.setAreaCounty(addressBook.getAreaCounty());
                addressBookVO.setDetailAddress(addressBook.getDetailAddress());
                addressBookVO.setWholeAddress(addressBook.getAreaProvince() + " "
                        + addressBook.getAreaCity() + " "
                        + addressBook.getAreaCounty() + " "
                        + addressBook.getDetailAddress());
                if (addressBook.getDefaultSelect() != null) {
                    addressBookVO.setDefaultSelect(addressBook.getDefaultSelect() == 0 ? false : true);
                } else {
                    addressBookVO.setDefaultSelect(false);
                }
                addressBooks.add(addressBookVO);
            }
        } catch (Exception e) {
            logger.error(String.format("获取用户(%s)地址簿信息出现异常", customId), e);
        }
        return addressBooks;
    }

    /**
     * 获取默认地址簿
     * @param customId
     * @return
     */
    @Override
    public AddressBookVO getDefaultAddressBook(String customId) {
        AddressBookVO addressBookVO = null;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("custom_id", customId).andFieldEqualTo("default_select", true);
            List<AddressBook> addressBookVOS = getList(fe);

            if (addressBookVOS != null && addressBookVOS.size() > 0) {
                AddressBook addressBook = addressBookVOS.get(0);
                addressBookVO = new AddressBookVO();
                addressBookVO.setId(addressBook.getId());
                addressBookVO.setName(addressBook.getName());
                addressBookVO.setTel(addressBook.getTel());
                addressBookVO.setAreaProvince(addressBook.getAreaProvince());
                addressBookVO.setAreaCity(addressBook.getAreaCity());
                addressBookVO.setAreaCounty(addressBook.getAreaCounty());
                addressBookVO.setDetailAddress(addressBook.getDetailAddress());
                addressBookVO.setWholeAddress(addressBook.getAreaProvince() + " "
                        + addressBook.getAreaCity() + " "
                        + addressBook.getAreaCounty() + " "
                        + addressBook.getDetailAddress());
                addressBookVO.setDefaultSelect(addressBook.getDefaultSelect() == 0 ? false : true);
            } else {
                logger.info(String.format("用户(%s)没有默认地址", customId));
            }
        } catch (Exception e) {
            logger.error(String.format("用户(%s)获取默认地址出现异常", customId), e);
        }
        return addressBookVO;
    }

    /**
     * 设置默认地址
     * @param addressBookId
     * @param customId
     * @return
     */
    @Override
    public boolean setDefault(Long addressBookId, String customId) {
        boolean res = false;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("custom_id", customId);
            AddressBook addressBook = new AddressBook();
            addressBook.setDefaultSelect((byte) 0);
            update(addressBook, fe);

            fe.clear();
            fe.createCriteria().andFieldEqualTo("id", addressBookId).andFieldEqualTo("custom_id", customId);
            AddressBook addressBook1 = new AddressBook();
            addressBook1.setDefaultSelect((byte) 1);
            long updateRes = update(addressBook1, fe);

            if (updateRes > 0) {
                res = true;
            } else {
                logger.warn(String.format("用户(%s)设置默认地址簿失败", customId));
            }
        } catch (Exception e) {
            logger.error(String.format("用户(%s)设置默认地址簿出现异常", customId), e);
        }
        return res;
    }

    /**
     * 删除地址簿
     * @param id
     * @return
     */
    @Override
    public boolean deleteAddressBook(Long id) {
        boolean res = false;
        try {
            this.remove(id);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("删除地址簿(%d)出现异常", id), e);
        }
        return res;
    }

    /**
     * 获取地址簿
     * @param addressBookId
     * @return
     */
    @Override
    public AddressBookVO getAddressBook(Long addressBookId) {
        AddressBookVO addressBookVO = new AddressBookVO();
        try {
            Optional<AddressBook> optional = this.get(addressBookId);
            if (optional.isPresent()) {
                AddressBook addressBook = optional.get();
                addressBookVO.setId(addressBook.getId());
                addressBookVO.setName(addressBook.getName());
                addressBookVO.setTel(addressBook.getTel());
                addressBookVO.setAreaProvince(addressBook.getAreaProvince());
                addressBookVO.setAreaCity(addressBook.getAreaCity());
                addressBookVO.setAreaCounty(addressBook.getAreaCounty());
                addressBookVO.setDetailAddress(addressBook.getDetailAddress());
                if (addressBook.getDefaultSelect() != null) {
                    addressBookVO.setDefaultSelect(addressBook.getDefaultSelect() == 0 ? false : true);
                } else {
                    addressBookVO.setDefaultSelect(false);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("获取地址簿(%d)信息出现异常", addressBookId), e);
        }
        return addressBookVO;
    }

}
