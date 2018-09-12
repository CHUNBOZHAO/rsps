package com.izhuixin.rsps.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.object.JsApiTicketInfo;
import com.izhuixin.rsps.common.object.MyX509TrustManager;
import com.izhuixin.rsps.common.util.UUID;
import com.izhuixin.rsps.common.vo.web.WxConfigModel;
import com.izhuixin.rsps.domain.automatic.JsApiTicket;
import com.izhuixin.rsps.service.WeChatService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class WeChatServiceImpl extends AbstractCrudService<JsApiTicket> implements WeChatService {

//    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

//    private static final String appId = "wxb7876218de37c718";
//
//    private static final String appSecret = "f6656c8c75c6199f83de6bea5c0ecafe";

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;


    /**
     * 检测票据
     * @return
     */
    @Override
    public boolean checkTicket() {
        boolean res = false;
        try {
            List<JsApiTicket> jsApiTicketList = getList();
            if (jsApiTicketList != null) {
                if (!jsApiTicketList.isEmpty()) {
                    JsApiTicket curJsApiTicket = jsApiTicketList.get(0);
                    if ((new Date().getTime() - curJsApiTicket.getUpdateTime().getTime()) > 1800 * 1000) {
                        res = true;
                    }
                } else {
                    res = true;
                }
            } else {
                res = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return res;
    }

    /***
     * 更新票据
     * @param jsApiTicket
     * @return
     */
    @Override
    public boolean updateTicket(JsApiTicket jsApiTicket) {
        boolean res = false;
        try {
            List<JsApiTicket> jsApiTicketList = getList();
            if (jsApiTicketList != null) {
                if (jsApiTicketList.isEmpty()) {
                    save(jsApiTicket);
                } else {
                    JsApiTicket curJsApiTicket = jsApiTicketList.get(0);
                    jsApiTicket.setId(curJsApiTicket.getId());
                    update(jsApiTicket);
                }
                res = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return res;
    }


    /**
     * 获取access_token
     * @return
     */
    @Override
    public String generateAccessToken() {
        String accessToken = null;

        String requestUrl = String.format(ACCESS_TOKEN_URL, appId, appSecret);
        JsonObject jsonObject = httpRequest(requestUrl, "GET", null);

        if (jsonObject != null && jsonObject.has("access_token")) {
            accessToken = jsonObject.get("access_token").getAsString();
        }
        return accessToken;
    }

    /**
     * 获取票据
     * @param accessToken
     * @return
     */
    @Override
    public JsApiTicketInfo generateJsApiTicket(String accessToken) {
        JsApiTicketInfo jsApiTicketInfo = null;
        String requestUrl = String.format(JSAPI_TICKET, accessToken);
        JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                jsApiTicketInfo = new JsApiTicketInfo();
                jsApiTicketInfo.setTicket(jsonObject.get("ticket").getAsString());
                jsApiTicketInfo.setExpiresIn(jsonObject.get("expires_in").getAsInt());
            } catch (Exception e) {
                logger.error("生成jsApiTicket出现异常", e);
            }
        }
        return jsApiTicketInfo;
    }

    /**
     * 获取签名所需数据
     * @param url
     * @return
     */
    @Override
    public WxConfigModel getSignature(String url) {
        WxConfigModel wxConfigModel = new WxConfigModel();
        String ticket = getJsApiTicket();
        if (StringUtils.isNotBlank(ticket)) {
            String noncestr = UUID.geneate().substring(0,16);
            String timestamp = Long.toString(System.currentTimeMillis() / 1000);
            String str = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
            // sha1加密
            String signature = SHA1(str);

            wxConfigModel.setAppId(appId);
            wxConfigModel.setNoncestr(noncestr);
            wxConfigModel.setTimestamp(timestamp);
            wxConfigModel.setSignature(signature);
        }
        return wxConfigModel;
    }

    /**
     * 获取JsApiTicket
     * @return
     */
    private String getJsApiTicket() {
        String ticket = "";
        try {
            List<JsApiTicket> jsApiTicketList = getList();
            if (jsApiTicketList != null && !jsApiTicketList.isEmpty()) {
                JsApiTicket jsApiTicket = jsApiTicketList.get(0);
                ticket = jsApiTicket.getJsapiTicket();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return ticket;
    }

    /**
     * 发起https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr  提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    private JsonObject httpRequest(String requestUrl,
                                   String requestMethod, String outputStr) {
        JsonObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

            Gson gson = new Gson();
            jsonObject = gson.fromJson(buffer.toString(), JsonObject.class);
        } catch (ConnectException ce) {
            logger.error("", ce);
        } catch (Exception e) {
            logger.error("", e);
        }
        return jsonObject;
    }

    /**
     * 加密
     * @param str
     * @return
     */
    private String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); // 如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
