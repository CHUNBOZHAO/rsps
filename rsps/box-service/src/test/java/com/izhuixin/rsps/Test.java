package com.izhuixin.rsps;

import com.izhuixin.rsps.domain.BoxDetailInfo;
import com.izhuixin.rsps.service.BoxDetailService;
import com.izhuixin.rsps.util.SpringContext;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoxService.class)
@WebAppConfiguration
public class Test {

        @org.junit.Test
        public void test(){

            BoxDetailInfo boxDetailInfo = new BoxDetailInfo();
            BoxDetailService detailService = SpringContext.getBean(BoxDetailService.class);

            System.out.println(detailService.saveBoxDetail(boxDetailInfo));
        }


}
