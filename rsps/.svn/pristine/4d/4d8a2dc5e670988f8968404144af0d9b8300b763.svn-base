package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.common.AddressBookVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/addressBookData")
public interface AddressBookService {
    @RequestMapping("/addressBook/save")
    boolean saveAddressBook(@RequestBody AddressBookVO addressBookVO, @RequestParam("customId") String customId);

    @RequestMapping("/addressBook/update")
    boolean updateAddressBook(@RequestBody AddressBookVO addressBookVO, @RequestParam("customId") String customId);

    @RequestMapping("/addressBooks/get/{customId}")
    List<AddressBookVO> getAddressBooks(@PathVariable("customId") String customId);

    //AddressBookVO getDefaultAddressBook(String customId);

    @RequestMapping("/addressBook/default/set/{addressBookId}/{customId}")
    boolean setDefault(@PathVariable("addressBookId") Long addressBookId, @PathVariable("customId") String customId);

    // 删除地址簿
    @RequestMapping("/addressBook/delete/{id}")
    boolean deleteAddressBook(@PathVariable("id") Long id);

    @RequestMapping("/addressBook/get/{addressBookId}")
    AddressBookVO getAddressBook(@PathVariable("addressBookId") Long addressBookId);
}
