package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.OrderSource;
import com.izhuixin.rsps.service.OrderSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;

@RestController
@RequestMapping("v1/")
public class OrderSourceController {

    @Autowired
    private OrderSourceService orderSourceService;

    @RequestMapping("orderData/commonOrderSource/save")
    public boolean saveOrderSource(@RequestBody OrderSource orderSource){

        return  orderSourceService.saveOrderSource(orderSource);
    }

    @RequestMapping("orderData/commonOrderSource/get")
    public void getOrderSourceByOrderId(String orderId) throws Exception{

        OrderSource orderSource =  orderSourceService.getOrderSourceByOrderId(orderId);

        InputStream inputStream = new ByteArrayInputStream(orderSource.getSource());

        String fileName = new Random().nextInt(100)+".jpg";
        File file = new File("D:\\Images");
        if(!file.exists()){
            file.mkdirs();
        }

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Images"+File.separator+fileName);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes,0,len);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
    }
}
