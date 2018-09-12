package com.izhuixin.rsps.receiver;

import com.google.gson.Gson;
import com.izhuixin.rsps.EnterpriseService;
import com.izhuixin.rsps.domain.*;
import com.izhuixin.rsps.message.InputMsg;
import com.izhuixin.rsps.model.AppReqPushOpLocation;
import com.izhuixin.rsps.service.feign.OperatorFeignService;
import com.izhuixin.rsps.util.Md5Util;
import okhttp3.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.bouncycastle.util.encoders.UrlBase64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 绑定一个接收消息的通道
 */
@EnableBinding(InputMsg.class)
/**
 * @author zcb
 */
public class OrderReceiver {

    @Value("${url.test}")
    private String URL;

    @Autowired
    OperatorFeignService operatorFeignService;

    private static Logger logger = LoggerFactory.getLogger(EnterpriseService.class);

    @StreamListener(InputMsg.INPUT_ORDER)
    public void OrderMessage(Object message) throws Exception{

        SignInfo signInfo = (SignInfo)message;
        //对象转json
        Gson gson = new Gson();
        //使用Gson将对象转换为json字符串
        String jsonStr = gson.toJson(signInfo);


        Params params = new Params();
        params.setService("zmrj_status_qs");
        params.setContent(jsonStr);
        params.setCustomerCode("zm");
        params.setFormat("json");
        params.setSign(Md5Util.MD5Encode(jsonStr+"4686c51afd8f30d93d209c8e3fd2331d","UTF-8"));

        //发送
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("service", params.getService())
                .add("content", params.getContent())
                .add("customerCode", params.getCustomerCode())
                .add("format", params.getFormat())
                .add("sign", params.getSign())
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();

        //创建/Call
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                logger.info("连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = response.body().string();
                logger.info(a);
            }
        });
    }

    @StreamListener(InputMsg.INPUT_OPERLOCATION)
    public void operlocationMessage(Object message){
        AppReqPushOpLocation appReqPushOpLocation = null;
        appReqPushOpLocation = (AppReqPushOpLocation)message;
        //String ack = appLoctionService.pushOperatorLocation(location);

        String name = appReqPushOpLocation.getOperatorName();
        String entCode = appReqPushOpLocation.getEntCode()+"_";
        OperatorInfo operatorInfo = operatorFeignService.getOperatorInfo(name, entCode);
        if(null == operatorInfo || null == operatorInfo.getOperatorNo()){
            return;
        }

        DateTime d1 = new DateTime();
        String s1 = d1.toString("yyyy/MM/dd HH:mm:ss.SSS");
        Gson gson = new Gson();
        logger.info("Receive data:"+gson.toJson(appReqPushOpLocation) +"-->"+s1);

        DriverLocation driverLocation = new DriverLocation();
        driverLocation.setVehicle(appReqPushOpLocation.getOperatorName());
        driverLocation.setVehicleid(operatorInfo.getOperatorNo());
        String gpstime = d1.toString("yyyy-MM-dd HH:mm:ss");
        driverLocation.setGpstime(gpstime);
        driverLocation.setLat(appReqPushOpLocation.getLatitude().toString());
        driverLocation.setLon(appReqPushOpLocation.getLongitude().toString());
        driverLocation.setBdlat(appReqPushOpLocation.getLatitude().toString());
        driverLocation.setBdlon(appReqPushOpLocation.getLongitude().toString());
        driverLocation.setTel("00000000");

        ArrayList<DriverLocation> data = new ArrayList<>();
        data.add(driverLocation);

        OutputLocation outputData = new OutputLocation();
        outputData.setData(data);

        String strData = gson.toJson(outputData);

        // 创建动态客户端
        DateTime d2 = new DateTime();
        String s2 = d2.toString("yyyy/MM/dd HH:mm:ss.SSS");
        logger.info("Send data:"+strData +"-->"+s2);
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://60.191.39.27:8088/zxdmzservice/services/webService?wsdl");

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("ZXGPSSERVICE", "ZXKJGPS", "ZXKJGPS", strData);

            DateTime d3 = new DateTime();
            String s3 = d3.toString("yyyy/MM/dd HH:mm:ss.SSS");
            logger.info("返回数据:" + objects[0] +"-->"+s3);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

}