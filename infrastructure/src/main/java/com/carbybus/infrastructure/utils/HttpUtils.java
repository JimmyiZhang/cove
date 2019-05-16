package com.carbybus.infrastructure.utils;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Http工具类
 *
 * @author jimmy.zhang
 * @date 2019-05-06
 */
@Log4j2
public class HttpUtils {
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private HttpUtils() {
    }

    /**
     * 使用GET方法获取结果
     * 获取失败返回null
     *
     * @param url 获取地址
     * @return 返回内容，失败则为null
     * @author jimmy.zhang
     * @date 2019-05-06
     */
    public static String get(String url) {
        BufferedReader in = null;
        StringBuilder result;
        try {
            // url请求中如果有中文，要在接收方用相应字符转码
            URL uri = new URI(url).toURL();
            URLConnection connection = uri.openConnection();
            connection.setRequestProperty("Content-type", "text/html");
            connection.setRequestProperty("Accept-Charset", DEFAULT_CHARSET);
            connection.setRequestProperty("contentType", DEFAULT_CHARSET);
            connection.connect();
            result = new StringBuilder();
            // 读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), DEFAULT_CHARSET));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            String resp = result.toString();
            log.info("获取地址：{}, 获取结果: {}", url, resp);

            return resp;
        } catch (Exception e) {
            log.warn("获取出错，地址: {}", url, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("关闭BufferedReader出错: ", ex);
            }
        }
        return null;
    }

    /**
     * 使用POST方法获取结果
     * 获取失败返回null
     *
     * @param url  获取地址
     * @param data 传输数据
     * @return
     * @author jimmy.zhang
     * @date 2019-05-06
     */
    public static String post(String url, Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        // 构建请求参数
        if (data != null && data.size() > 0) {
            for (Map.Entry<String, String> e : data.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
        }

        return post(url, sb.toString(), DEFAULT_CHARSET);
    }

    /**
     * 使用POST方法获取结果
     * 获取失败返回null
     *
     * @param url     获取地址
     * @param para    获取参数
     * @param charset 字符集
     * @return
     * @author jimmy.zhang
     * @date 2019-05-06
     */
    public static String post(String url, String para, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置超时时间
            conn.setConnectTimeout(60);
            conn.setReadTimeout(60);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(para);
            // flush输出流的缓冲
            out.flush();
            out.close();

            // 获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.warn("连接失败, 地址: {}, 状态码: {}", url, conn.getResponseCode());
                return null;
            }

            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            String resp = sb.toString();
            log.info("获取地址：{}, 获取结果: {}", url, resp);
            return resp;
        } catch (Exception e) {
            log.error("获取出错, 地址: {}", url, e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("关闭BufferedReader出错: ", ex);
            }
        }
        return null;
    }
}
