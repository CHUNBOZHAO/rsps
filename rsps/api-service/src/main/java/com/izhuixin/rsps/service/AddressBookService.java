package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.vo.web.AddressBookVO;
import com.izhuixin.rsps.domain.automatic.AddressBook;

import java.util.List;

public interface AddressBookService extends CrudService<AddressBook> {
    boolean saveAddressBook(AddressBookVO addressBookVO, String customId);

    boolean updateAddressBook(AddressBookVO addressBookVO, String customId);

    List<AddressBookVO> getAddressBooks(String customId);

    AddressBookVO getDefaultAddressBook(String customId);

    boolean setDefault(Long addressBookId, String customId);

    // 删除地址簿
    boolean deleteAddressBook(Long id);

    AddressBookVO getAddressBook(Long addressBookId);
}
