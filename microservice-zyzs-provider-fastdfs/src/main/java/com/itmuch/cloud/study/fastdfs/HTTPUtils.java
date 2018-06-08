package com.itmuch.cloud.study.fastdfs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


public class HTTPUtils {
    public static String sendP(String picPath){
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;
        try {
            URL url = new URL("http://127.0.0.1:5000/file/uploadFile2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            Long sendTime = System.currentTimeMillis();
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            // 要传的参数
            String content ="fileName=bg13.jpg";
            // 得到图片的base64编码
            content = content + "&" + URLEncoder.encode("file", "UTF-8") + "="+ URLEncoder.encode(getPicBASE64(picPath), "UTF-8");
            out.writeBytes(content);
            out.flush();
            out.close();
            Map<String, List<String>> map = connection.getHeaderFields();

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (Exception e) {

        }
        return stringBuffer.toString();

    }

    public static String getPicBASE64(String picPath) {
        String content = "";
        try {
            FileInputStream fileForInput = new FileInputStream(picPath);
            byte[] bytes = new byte[fileForInput.available()];
            if(bytes.length<102400){
                System.out.print(bytes.length);
            }
            fileForInput.read(bytes);
            content = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法
            fileForInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    public static void main(String[] args) {
        System.out.println(sendP("D:\\bg13.jpg"));


    }
}
