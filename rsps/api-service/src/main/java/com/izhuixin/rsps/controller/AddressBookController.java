package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.AddressBookVO;
import com.izhuixin.rsps.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地址簿服务接口
 */
@RestController
@RequestMapping("v1/addressBookData")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 保存地址簿
     * @param addressBookVO
     * @param customId
     * @return
     */
    @RequestMapping("/addressBook/save")
    public boolean saveAddressBook(@RequestBody AddressBookVO addressBookVO, String customId) {
        return addressBookService.saveAddressBook(addressBookVO, customId);
    }

    /**
     * 更新地址簿信息
     * @param addressBookVO
     * @param customId
     * @return
     */
    @RequestMapping("/addressBook/update")
    public boolean updateAddressBook(@RequestBody AddressBookVO addressBookVO, String customId) {
        return addressBookService.updateAddressBook(addressBookVO, customId);
    }

    /**
     * 获取用户地址簿
     * @param customId
     * @return
     */
    @RequestMapping("/addressBooks/get/{customId}")
    public List<AddressBookVO> getAddressBooks(@PathVariable("customId") String customId) {
        return addressBookService.getAddressBooks(customId);
    }

    /**
     * 获取用户默认地址簿
     * @param customId
     * @return
     */
    @RequestMapping("/addressBook/default/get/{customId}")
    public AddressBookVO getDefaultAddressBook(@PathVariable("customId") String customId) {
        return addressBookService.getDefaultAddressBook(customId);
    }

    /**
     * 设置用户默认地址簿
     * @param addressBookId
     * @param customId
     * @return
     */
    @RequestMapping("/addressBook/default/set/{addressBookId}/{customId}")
    public boolean setDefault(@PathVariable("addressBookId") Long addressBookId, @PathVariable("customId") String customId) {
        return addressBookService.setDefault(addressBookId, customId);
    }

    /**
     * 删除地址簿
     * @param id
     * @return
     */
    @RequestMapping("/addressBook/delete/{id}")
    public boolean deleteAddressBook(@PathVariable("id") Long id) {
        return addressBookService.deleteAddressBook(id);
    }

    /**
     * 获取地址簿信息
     * @param addressBookId
     * @return
     */
    @RequestMapping("/addressBook/get/{addressBookId}")
    public AddressBookVO getAddressBook(@PathVariable("addressBookId") Long addressBookId) {
        return addressBookService.getAddressBook(addressBookId);
    }

}
