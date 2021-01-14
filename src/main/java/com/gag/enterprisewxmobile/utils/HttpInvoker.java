package com.gag.enterprisewxmobile.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http请求工具类
 * @author ouyangjun
 *
 */
@Slf4j
public class HttpInvoker {

    /**
     * 微信官方允许一天刷2000次
     * @param sUrl
     * @param sMethod
     * @param sOutput
     * @return
     */
    public static JSONObject exec(String sUrl, String sMethod, String sOutput) {
        JSONObject json = null;
        StringBuffer buffer = new StringBuffer();

        HttpURLConnection con = null;
        try {
            URL url = new URL(sUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestMethod(sMethod);
            // connection.setConnectTimeout(60000);

            con.setReadTimeout(60000);
            con.setConnectTimeout(60000);

            if (sOutput != null) {
                OutputStream os = con.getOutputStream();
                try {
                    os.write(sOutput.getBytes("UTF-8"));
                } catch (Exception e) {
                    log.info("HttpInvoker exec error: {}"+ e);
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            log.info("HttpInvoker exec error: {}"+ e);
                        }
                    }
                }
            }

            InputStream is = null;
            InputStreamReader inputReader = null;
            BufferedReader reader = null;
            try {
                is = con.getInputStream();
                inputReader = new InputStreamReader(is, "UTF-8");
                reader = new BufferedReader(inputReader);
                String temp;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                }
            } catch (Exception e) {
                log.info("HttpInvoker exec error: {}"+ e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        log.info("HttpInvoker exec error: {}"+e);
                    }
                }
                if (inputReader != null) {
                    try {
                        inputReader.close();
                    } catch (IOException e) {
                        log.info("HttpInvoker exec error: {}"+ e);
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        log.info("HttpInvoker exec error: {}"+ e);
                    }
                }
            }

            // con.disconnect();
            json = JSONObject.parseObject(buffer.toString());

            if (json != null) {
                //log.info("OK, http连接Url: {}, 返回数据,json: {}"+ sUrl+","+ json);
            } else {
                log.info("return json is null, http连接Url: {}, 返回数据,json: {}"+ sUrl+","+json);
            }
        } catch (IOException e) {
            log.info("HttpInvoker exec error: {}"+ e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return json;
    }

    /**
     * post封装
     * @param path
     * @param Info
     * @return
     * @throws IOException
     */
    public static JSONObject postResponse(String path, JSONObject Info){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(path);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {
            StringEntity s = new StringEntity(Info.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //System.out.println("请求服务器成功，做相应处理");
            } else {
                //System.out.println("请求服务端失败");
            }

        } catch (Exception e) {
            log.error("post封装请求错误，请检查相关参数:"+e);
            throw new RuntimeException(e);
        }
        return JSONObject.parseObject(result);
    }

}
