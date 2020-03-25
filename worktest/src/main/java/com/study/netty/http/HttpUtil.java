package com.study.netty.http;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ldb
 * http请求工具类
 */
public class HttpUtil {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    static HttpPost httpPost = new HttpPost("http://wx.u-workshop.com/order");

    public static void main(String[] args) throws Exception {
        String geturl = "http://www.u-workshop.com";
        String posturl1 = "http://wx.u-workshop.com/order";
        String posturl2 = "http://127.0.0.1:8888/ngroute_sc/ws/post2";
        ;
        String postxml = "http://127.0.0.1:8888/ngroute_sc/ws/getPwd";
        ;
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("name", "张三");
        parameters.put("age", "12345");
        System.out.println("get请求返回数据：");
//        String get1=sendGet1(geturl, parameters);
        HashMap<String, String> map = new HashMap<>();
        map.put("customerName","李阳");

        map.put("source","11");
        map.put("message","房屋类型：清水房;面积：90;户型：3室1厅1卫");
        map.put("originSource","360搜索");
        map.put("pageUrl","www.u-workshop.com");
        Gson gson = new Gson();

//        System.out.println(json);

        System.out.println(getNum());

        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    map.put("mobile", getNum());
                    String json = gson.toJson(map);
//                test(geturl, null);
                    try {
                        test(posturl1, json);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("发送完了");
                }
            });
        }






//        String get2=sendGet2(geturl, parameters);
//        System.out.println("get1:"+get1);
//        System.out.println("get2:"+get2);
//        System.out.println();
//        System.out.println("post请求返回数据：");

        //post 发送json数据
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("customerName", "张三");
//        jsonObject.put("age", "12");

//        String post1=sentPostwithJson1(posturl1,jsonObject.toString());
//        String post2=sentPostwithJson2(posturl2,parameters);
//        System.out.println("post1:"+post1);
//        System.out.println("post2:"+post2);

        //post xml
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
//        stringBuffer.append("<USERREQ>");
//        stringBuffer.append("<HEAD><CODE>消息标志</CODE><SID>消息序列号</SID><TIMESTAMP>时间戳</TIMESTAMP><SERVICEID>SCNGMTTSSO
// </SERVICEID></HEAD>");
//        stringBuffer.append("<BODY><LOGINNAME>AIKF0013</LOGINNAME><SIGN>123456证</SIGN></BODY>");
//        stringBuffer.append("</USERREQ>");
//        String reqStr=stringBuffer.toString();
//        String post3 = sentPostwithxml(postxml,reqStr);
//        System.out.println("post3:"+post3);
    }

    public static String getNum() {

        Random r = new Random();
        StringBuffer str = new StringBuffer("176");
        // 随机生成手机号（手机号前三位固定为176）
        for (int i = 0; i < 8; i++) {
            int num = r.nextInt(9);
            str.append(num);
        }
        return str.toString();
    }


    public static void test(String url, String jsonString) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout
                (30000).setSocketTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
        httpClient.execute(httpPost);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            System.out.println("ok");
        }
    }

    /**
     * get请求
     *
     * @param url
     * @param params Map<String, String>
     * @return 字符串
     */
    public static String sendGet1(String url, Map<String, String> params) {
        // 构建请求参数  
        url = url + "?" + getReqParams(params);
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout
                (3000).setSocketTimeout(3000).build();
        httpGet.setConfig(requestConfig);
        httpGet.addHeader("Content-type", "application/json; charset=utf-8");
        //httpGet.setHeader("Accept", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            StringBuffer resultBuffer = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return resultBuffer.toString();
        } else {
            System.out.println("请求失败:" + statusCode);
            return "请求失败:" + statusCode;
        }

    }

    /**
     * get请求
     *
     * @param url
     * @param params Map<String, String>
     * @return 请求结果
     */
    public static String sendGet2(String url, Map<String, String> params) {
        // 构建请求参数  
        url = url + "?" + getReqParams(params);
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post1请求，发送json字符串
     *
     * @param url
     * @param jsonString
     * @return json对象字符串
     */
    public static String sentPostwithJson1(String url, String jsonString) {

        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            //请求时间设置30秒
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout
                    (30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                //String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    //sb.append(line + NL);
                    sb.append(line);
                }
                result = sb.toString();
            } else {
                System.out.println("请求失败:" + statusCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    /**
     * post2请求，发送key=value字符串
     *
     * @param url
     * @param params
     * @param charset
     * @return json对象字符串
     */
    public static String sentPostwithJson2(String url, Map<String, String> params) {
        CloseableHttpResponse response = null;
        HttpPost post = null;
        String reqStr = getReqParams(params);
        String result = "";
        try {
            post = new HttpPost(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout
                    (30000).setSocketTimeout(30000).build();
            post.setConfig(requestConfig);
            post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            post.setHeader("Connection", "Close");
            //String sessionId = getSessionId();
            //post.setHeader("SessionId", sessionId);
            // 构建消息实体
            StringEntity entity = new StringEntity(reqStr, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            //entity.setContentType("application/json");
            post.setEntity(entity);
            response = httpClient.execute(post);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("请求失败:" + statusCode);
            } else {
                StringBuffer resultBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
                result = resultBuffer.toString();
                br.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (post != null) {
                post.releaseConnection();
            }
        }
        return result;
    }

    /**
     * post 请求  发送xml格式数据
     *
     * @param url
     * @param reqStr xml
     * @return 字符串
     */
    public static String sentPostwithxml(String url, String reqStr) {
        CloseableHttpResponse response = null;
        HttpPost post = null;
        String result = "";
        try {
            post = new HttpPost(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout
                    (30000).setSocketTimeout(30000).build();
            post.setConfig(requestConfig);
            post.setHeader("Content-type", "application/xml;charset=utf-8");
            post.setHeader("Connection", "Close");
            // 构建消息实体
            StringEntity entity = new StringEntity(reqStr, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            post.setEntity(entity);
            response = httpClient.execute(post);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("请求失败" + statusCode);
            } else {
                StringBuffer resultBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
                br.close();
                result = resultBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (post != null) {
                post.releaseConnection();
            }
        }
        return result;
    }

    // 构建唯一会话Id
    public static String getSessionId() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str
                .substring(24);
    }

    //封装key=value
    public static String getReqParams(Map<String, String> params) {
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, String> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                try {
                    sbParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                sbParams.append("&");
            }
        }
        String reqparams = "";
        if (sbParams != null && sbParams.length() > 0) {
            reqparams = sbParams.substring(0, sbParams.length() - 1);
        }
        return reqparams;
    }

}