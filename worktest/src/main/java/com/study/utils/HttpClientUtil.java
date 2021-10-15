package com.study.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求客户端
 *
 * @author ldb
 */
@Slf4j
public class HttpClientUtil {

    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static RequestConfig requestConfig = null;
    private static SSLConnectionSocketFactory sslConnectionSocketFactory = null;
    private static PoolingHttpClientConnectionManager pool = null;

    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(5000).build();
        try {
            // 信任所有站点 直接返回true
            // 管理Https连接的上下文类
            SSLContextBuilder sslContextBuilder = SSLContextBuilder.create().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
                    // 信任所有站点 直接返回true
                    return true;
                }
            });
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build(),
                    new String[]{"TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslConnectionSocketFactory)
                    .build();
            pool = new PoolingHttpClientConnectionManager(registryBuilder);
            //将最大连接数增加到500
            pool.setMaxTotal(500);
            //将每个路由的默认最大连接数增加到100
            pool.setDefaultMaxPerRoute(100);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(pool)
                .setDefaultRequestConfig(requestConfig)
                .disableAutomaticRetries()
                .setConnectionManagerShared(true)
                .build();
    }

    private static List<NameValuePair> buildPostParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        List<NameValuePair> results = new ArrayList<NameValuePair>();

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = URLEncoder.encode(entry.getValue(), params.get("_input_charset") == null ? "utf-8" : params.get("_input_charset"));
                results.add(new BasicNameValuePair(key, value));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("异常", e);
        }
        return results;
    }

    /**
     * post请求传输json参数
     *
     * @param url       url地址
     * @param jsonParam 参数
     * @return
     */
    public static String httpPost(String url, JSONObject jsonParam) {
        // post请求返回结果
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        if (null != jsonParam) {
            // 解决中文乱码问题
            StringEntity entity = new StringEntity(jsonParam.toString(), StandardCharsets.UTF_8);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 读取服务器返回过来的json字符串数据
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toString(entity, StandardCharsets.UTF_8);
                } else {
                    log.error("post请求响应失败:{},响应码:{}", url, response.getStatusLine().getStatusCode());
                }
            }
        } catch (IOException e) {
            log.error("post请求发送失败:{},异常:{}", url, e);
        }
        return null;
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     *
     * @param url    url地址
     * @param params 参数
     * @return
     */
    public static String httpPost(String url, Map<String, String> params) {
        // post请求返回结果 todo
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        //创建一个post对象
        List<NameValuePair> paramList = buildPostParams(params);
        httpPost.setConfig(requestConfig);
        if (null != params) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("异常", e);
            }
        }
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 读取服务器返回过来的json字符串数据
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toString(entity, StandardCharsets.UTF_8);
                } else {
                    log.error("post请求响应失败:{},响应码:{}", url, response.getStatusLine().getStatusCode());
                }
            }
        } catch (IOException e) {
            log.error("post请求发送失败:{},异常:{}", url, e);
        }
        return null;
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     *
     * @param url      url地址
     * @param strParam 参数
     * @return
     */
    public static String httpPost(String url, String strParam) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (null != strParam) {
            // 解决中文乱码问题
            StringEntity entity = new StringEntity(strParam, StandardCharsets.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 读取服务器返回过来的json字符串数据
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toString(entity, StandardCharsets.UTF_8);
                } else {
                    log.error("post请求响应失败:{},响应码:{}", url, response.getStatusLine().getStatusCode());
                }
            }
        } catch (IOException e) {
            log.error("post请求发送失败:{},异常:{}", url, e);
        }
        return null;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static String httpGet(String url) {
        // get请求返回结果
        CloseableHttpClient httpClient = getHttpClient();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            } else {
                log.error("get请求响应失败:{},响应码:{}", url, response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("get请求发送失败:{},异常:{}", url, e);
        }
        return null;
    }

//   public <T> T parseResult(String str,Class clazz){
//       Object o = JSON.parseObject(str, clazz);
//       JSONObject response = JSON.parseObject(str);
//       String isSuccess = response.getString("is_success");
//       if("T".equals(isSuccess)){
//           // 调用成功
//           String memberId = response.getString("member_id");
//           // 打印memberId
//           System.out.println(memberId);
//       }else{
//           log.info("调用失败:{}",str);
//       }
//   }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "124");
        String result = HttpClientUtil.httpPost("http://localhost:8080/settle/test", map);
        System.out.println(result);


    }
}