package com.izhuixin.rsps.common.util;//package com.izhuixin.rsps.common.util;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.izhuixin.rsps.common.object.MyX509TrustManager;
//import com.oracle.javafx.jmx.json.JSONException;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.ConnectException;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class WechatUtils {
//
//    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
//
//    private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
//
//    /**
//     * 获取access_token
//     *
//     * @param appid
//     *            凭证
//     * @param appsecret
//     *            密钥
//     * @return
//     */
//    public static AccessToken getAccessToken(String appid, String appsecret) {
//        AccessToken accessToken = null;
//
//        String requestUrl = String.format(ACCESS_TOKEN_URL, appid, appsecret);
//        JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
//
//        System.out.println(jsonObject);
//
////        // 如果请求成功
////        if (null != jsonObject) {
////            try {
////                accessToken = new AccessToken();
////                accessToken.setToken(jsonObject.getString("access_token"));
////                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
////            } catch (JSONException e) {
////                accessToken = null;
////                // 获取token失败
////                log.error("获取token失败 errcode:{} errmsg:{}",
////                        jsonObject.getInt("errcode"),
////                        jsonObject.getString("errmsg"));
////
////            }
////        }
//        return accessToken;
//    }
//
//    /**
//     * 发起https请求并获取结果
//     *
//     * @param requestUrl
//     *            请求地址
//     * @param requestMethod
//     *            请求方式（GET、POST）
//     * @param outputStr
//     *            提交的数据
//     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
//     */
//    public static JsonObject httpRequest(String requestUrl,
//                                         String requestMethod, String outputStr) {
//        JsonObject jsonObject = null;
//        StringBuffer buffer = new StringBuffer();
//        try {
//            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            TrustManager[] tm = { new MyX509TrustManager() };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//
//            URL url = new URL(requestUrl);
//            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
//                    .openConnection();
//            httpUrlConn.setSSLSocketFactory(ssf);
//
//            httpUrlConn.setDoOutput(true);
//            httpUrlConn.setDoInput(true);
//            httpUrlConn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            httpUrlConn.setRequestMethod(requestMethod);
//
//            if ("GET".equalsIgnoreCase(requestMethod))
//                httpUrlConn.connect();
//
//            // 当有数据需要提交时
//            if (null != outputStr) {
//                OutputStream outputStream = httpUrlConn.getOutputStream();
//                // 注意编码格式，防止中文乱码
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//
//            // 将返回的输入流转换成字符串
//            InputStream inputStream = httpUrlConn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(
//                    inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(
//                    inputStreamReader);
//
//            String str = null;
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            bufferedReader.close();
//            inputStreamReader.close();
//            // 释放资源
//            inputStream.close();
//            inputStream = null;
//            httpUrlConn.disconnect();
//
//            Gson gson = new Gson();
//            jsonObject = gson.fromJson(buffer.toString(), JsonObject.class);
////            jsonObject = JSONObject.fromObject(buffer.toString());
//        } catch (ConnectException ce) {
//            ce.printStackTrace();
////            log.error("Weixin server connection timed out.");
//        } catch (Exception e) {
//            e.printStackTrace();
////            log.error("https request error:{}", e);
//        }
//        return jsonObject;
//    }
//
//    public static JsApiTicket getJsApiTicket(String accessToken) {
//        JsApiTicket jsApiTicket = null;
//
//        String requestUrl = String.format(JSAPI_TICKET, accessToken);
//        JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
//        // 如果请求成功
//        if (null != jsonObject) {
//            try {
//                jsApiTicket = new JsApiTicket();
//                jsApiTicket.setTicket(jsonObject.get("ticket").getAsString());
//                jsApiTicket.setExpiresIn(jsonObject.get("expires_in").getAsInt());
//            } catch (JSONException e) {
//                e.printStackTrace();
//                accessToken = null;
//                // 获取jsApiTicket失败
////                log.error("获取jsApiTicket失败 errcode:{} errmsg:{}",
////                        jsonObject.getInt("errcode"),
////                        jsonObject.getString("errmsg"));
//            }
//        }
//        return jsApiTicket;
//    }
//
//    public static String getSignature(String jsApiTicket) {
//        String noncestr = UUID.geneate().substring(0,16);
//        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//        String url = "https://rsps.izhuixin.net/custom/order/showOrder";
//        String str = "jsapi_ticket=" + jsApiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
//        // sha1加密
//        String signature = SHA1(str);
//
//        System.out.println("signature:" + signature);
//        System.out.println("noncestr:" + noncestr);
//        System.out.println("timestamp:" + timestamp);
//        return signature;
//    }
//
//
//    private static String SHA1(String str) {
//        try {
//            MessageDigest digest = java.security.MessageDigest
//                    .getInstance("SHA-1"); // 如果是SHA加密只需要将"SHA-1"改成"SHA"即可
//            digest.update(str.getBytes());
//            byte messageDigest[] = digest.digest();
//            // Create Hex String
//            StringBuffer hexStr = new StringBuffer();
//            // 字节数组转换为 十六进制 数
//            for (int i = 0; i < messageDigest.length; i++) {
//                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
//                if (shaHex.length() < 2) {
//                    hexStr.append(0);
//                }
//                hexStr.append(shaHex);
//            }
//            return hexStr.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//}
