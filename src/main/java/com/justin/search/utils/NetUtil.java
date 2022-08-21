package com.justin.search.utils;

import org.springframework.stereotype.Component;
import sun.awt.OSInfo;

import javax.swing.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 网络工具类 用于抓取http://serve.netsh.org上的hosts数据
 *
 * @author tone
 */
@Component
public class NetUtil {

    private final static String ENCODING = "UTF-8";
    private final static String GZIPCODING = "gzip";
    private final static String HOST = "http://serve.netsh.org/pub/hosts.php";
    private final static String COOKIE = "hostspasscode=%s; Hm_lvt_e26a7cd6079c926259ded8f19369bf0b=1421846509,1421846927,1421847015,1421849633; Hm_lpvt_e26a7cd6079c926259ded8f19369bf0b=1421849633";
    private final static String OFF = "off";
    private final static String ON = "on";
    private final static int RANDOM = 100000;
    private static String hostspasscode = null;
    private static NetUtil instance;

    public static NetUtil getInstance() {
        if (instance == null) {
            instance = new NetUtil();
        }
        return instance;
    }

    private NetUtil() {
        hostspasscode = createRandomCookies();
    }

    /**
     * 获取html内容
     *
     * @param gs
     * @param wk
     * @param twttr
     * @param fb
     * @param flkr
     * @param dpbx
     * @param odrvB
     * @param yt
     * @param nohl
     * @return
     */
    public String getHtmlInfo(boolean gs, boolean wk, boolean twttr, boolean fb,
                              boolean flkr, boolean dpbx, boolean odrv,
                              boolean yt, boolean nohl) throws Exception {
        HttpURLConnection conn = null;

        String result = "";

        //String cookie = "hostspasscode="+hostspasscode+"; Hm_lvt_e26a7cd6079c926259ded8f19369bf0b=1421846509,1421846927,1421847015,1421849633; Hm_lpvt_e26a7cd6079c926259ded8f19369bf0b=1421849633";
        String cookie = String.format(COOKIE, hostspasscode);

        //URL url = new URL("http://serve.netsh.org/pub/hosts.php?passcode=13008&gs=on&wk=on&twttr=on&fb=on&flkr=on&dpbx=on&odrv=on&yt=on&nolh=on");
        URL url = new URL(createUrl(hostspasscode, gs, wk, twttr, fb, flkr, dpbx, odrv, yt, nohl));
        //System.out.println(cookie);
        // System.out.println(url.toString());

        conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(5 * 1000);
        conn.setDoOutput(true);
        //get方式提交
        conn.setRequestMethod("GET");
        //凭借请求头文件
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Cookie", cookie);
        conn.setRequestProperty("Host", "serve.netsh.org");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:35.0) Gecko/20100101 Firefox/35.0");

        // conn.setRequestProperty("Referer", "http://serve.netsh.org/pub/gethosts.php");
        // conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

        conn.connect();

        String encoding = conn.getContentEncoding();

        result = readStream(conn.getInputStream(), encoding);
        //测试进度条显示
        // result = readStream(new FileInputStream(new File("/home/tone/Resident.Evil.Damnation.2012.1080p.BluRay.x264.DTS-WiKi.mkv")), "11");

        conn.disconnect();
        if (nohl) {
            result = getLocalHost() + result;
        }

        return result;
    }

    /**
     * 读取将InputStream中的字节读以字符的形式取到字符串中，如果encoding是gzip，那么需要先有GZIPInputStream进行封装
     *
     * @param inputStream InputStream字节流
     * @param encoding    编码格式
     * @return String类型的形式
     * @throws IOException IO异常
     */
    public String readStream(InputStream inputStream, String encoding) throws Exception {
        StringBuffer buffer = new StringBuffer();
        ProgressMonitorInputStream pmis = null;

        InputStreamReader inputStreamReader = null;
        GZIPInputStream gZIPInputStream = null;
        if (GZIPCODING.equals(encoding)) {
            gZIPInputStream = new GZIPInputStream(inputStream);
            inputStreamReader = new InputStreamReader(gZIPInputStream, ENCODING);
        } else {
            inputStreamReader = new InputStreamReader(inputStream, ENCODING);
        }
        char[] c = new char[1024];
        int lenI;
        while ((lenI = inputStreamReader.read(c)) != -1) {
            buffer.append(new String(c, 0, lenI));
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (gZIPInputStream != null) {
            gZIPInputStream.close();
        }
        if (pmis != null) {
            gZIPInputStream.close();
        }


        return buffer.toString();


    }

    /**
     * 生成随机Cookies数组
     *
     * @return 五位随机数字
     */
    private String createRandomCookies() {

        return String.valueOf(Math.random() * RANDOM).substring(0, 5);

    }

    /**
     * 生成链接字符串
     *
     * @param hostspasscode
     * @param gs
     * @param wk
     * @param twttr
     * @param fb
     * @param flkr
     * @param dpbx
     * @param odrvB
     * @param yt
     * @param nohl
     * @return
     */
    private String createUrl(String hostspasscode, boolean gs, boolean wk, boolean twttr, boolean fb,
                             boolean flkr, boolean dpbx, boolean odrv,
                             boolean yt, boolean nohl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(HOST);
        buffer.append("?passcode=" + hostspasscode);
        if (gs) {
            buffer.append("&gs=" + ON);
        } else {
            buffer.append("&gs=" + OFF);
        }
        if (wk) {
            buffer.append("&wk=" + ON);
        } else {
            buffer.append("&wk=" + OFF);
        }
        if (twttr) {
            buffer.append("&twttr=" + ON);
        } else {
            buffer.append("&twttr=" + OFF);
        }
        if (fb) {
            buffer.append("&fb=" + ON);
        } else {
            buffer.append("&fb=" + OFF);
        }
        if (flkr) {
            buffer.append("&flkr=" + ON);
        } else {
            buffer.append("&flkr=" + OFF);
        }
        if (dpbx) {
            buffer.append("&dpbx=" + ON);
        } else {
            buffer.append("&dpbx=" + OFF);
        }
        if (odrv) {
            buffer.append("&odrv=" + ON);
        } else {
            buffer.append("&odrv=" + OFF);
        }
        if (yt) {
            buffer.append("&yt=" + ON);
        } else {
            buffer.append("&yt=" + OFF);
        }
        if (nohl) {
            buffer.append("&nohl=" + ON);
        } else {
            buffer.append("&nohl=" + OFF);
        }
        return buffer.toString();
    }

    private String getLocalHost() throws Exception {

        StringBuffer buffer = new StringBuffer();
        //String hostName = System.getInstance().getLocalhostName();
        buffer.append("#LOCALHOST begin" + "\n");
        buffer.append("127.0.0.1\tlocalhost" + "\n");
//        if (hostName != null && !"".equals(hostName)) {
//            buffer.append("127.0.1.1\t" + hostName + "\n");
//        }

        buffer.append("#LOCALHOST end" + "\n");
        return buffer.toString();


    }

}