package com.itmuch.cloud.study.controller;


import com.esotericsoftware.minlog.Log;
import com.itmuch.cloud.study.fastdfs.FastDFSClient;
import com.itmuch.cloud.study.fastdfs.FileUtil;
import com.itmuch.cloud.study.myenum.Code;
import com.itmuch.cloud.study.task.HandleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/file/*")
public class FileController
{
    @Autowired
    private HandleTask handleTask;

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * ajax上传文件
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @return
     */
    @RequestMapping(value = "ajaxFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxFileUpload(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        BASE64Decoder decoder = new BASE64Decoder();
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            String imgData = request.getParameter("image");
            String[] base64 = imgData.split(",");
            String type = base64[0].replaceAll("data:image/", "");
            type = type.replaceAll(";base64", "");
            byte[] b = decoder.decodeBuffer(base64[1]);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String path = fastDFSClient.uploadFile(b, UUID.randomUUID()+"."+type);
            m.put("code", Code.SUCCESS);
            m.put("url", path);
            m.put("msg", "上传成功");

        }catch (Exception e){
            e.printStackTrace();
            m.put("code", Code.FAIL);
            m.put("msg", "上传fail");

        }
        return m;

    }
    /**
     * http上传文件 -------案例在httputils中
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @return
     */
    @RequestMapping(value = "httpuploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> httpuploadFile(HttpServletRequest request, HttpServletResponse response)
    {
        String fileName = request.getParameter("fileName");
        String str = request.getParameter("file");
        BASE64Decoder decoder = new BASE64Decoder();
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            String filename=UUID.randomUUID()+".jpg";
            Log.info("--------------"+filename);
            String path = fastDFSClient.uploadFile(bytes, filename);
            Log.info("--------------"+path);
            m.put("code", Code.SUCCESS);
            m.put("url", path);
            m.put("msg", "上传成功");

        }catch (Exception e){
            e.printStackTrace();
            m.put("code", Code.FAIL);
            m.put("msg", "上传fail");

        }
        return m;

    }

    /**
     * 多线程文件上传
     *
     * @author zhaokui
     * @version 1.0
     * @date 2018-05-31
     * @return
     */
    @RequestMapping(value = "threaduploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String threaduploadFile(HttpServletRequest request, HttpServletResponse response)
    {
        String token=UUID.randomUUID().toString();
        //采用契约模式--盒生产消费者模式
        String str = request.getParameter("file");
        String fileName = request.getParameter("fileName");
        String filetype= "jpg";
        Log.info("文件类型："+filetype);
        return handleTask.handleRequest(str,token+"."+filetype);


    }
    /**
     * 下载文件
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @return
     */
    @RequestMapping(value = "getFileByPath", method = RequestMethod.GET)
    public void getFileByPath(HttpServletResponse response, String path)
    {

        try
        {
            // 判断文件是否存在
            if (fastDFSClient.getFileInfo(path) != null)
            {
                byte[] buffer = fastDFSClient.downloadFile(path);
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + FileUtil.getOriginalFilename(path));
                response.addHeader("Content-Length", "" + buffer.length);
                // 通过文件流的形式写到客户端
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                // 写完以后关闭文件流
                toClient.flush();
                toClient.close();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 下载文件
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @return
     */
    @RequestMapping(value = "downloadFile", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response)
    {

        try
        {

            FileInputStream fileForInput = new FileInputStream("D:\\test\\1.jpg");
            byte[] bytes = new byte[fileForInput.available()];
            if(bytes.length<102400){
                System.out.print(bytes.length);
            }
            fileForInput.read(bytes);
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition",
                    "attachment;filename=app.txt");
            response.addHeader("Content-Length", "" + bytes.length);
            // 通过文件流的形式写到客户端
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(bytes);
            // 写完以后关闭文件流
            toClient.flush();
            toClient.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 删除文件
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @return
     */
    @RequestMapping(value = "deleteFileByPath", method = RequestMethod.DELETE)
    public int deleteFileByPath(String path)
    {
        try
        {
            // 判断文件是否存在
            if (fastDFSClient.getFileInfo(path) != null)
            {
               return fastDFSClient.deleteFile(path);
            }

        }catch (Exception e)
        {
            e.printStackTrace();


        }
        return 0;
    }
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response)
    {

        BASE64Decoder decoder = new BASE64Decoder();
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            String imgData = request.getParameter("image");
            System.out.println("-----------------------------------------");
            System.out.print(imgData);
            System.out.println("-----------------------------------------");

            System.out.println(getImageStr("D:\\3.jpg"));

        }catch (Exception e){
            e.printStackTrace();
            m.put("code", Code.FAIL);
            m.put("msg", "上传fail");

        }
        return m;

    }

}