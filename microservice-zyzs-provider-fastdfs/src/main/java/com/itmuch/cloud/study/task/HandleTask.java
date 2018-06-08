package com.itmuch.cloud.study.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class HandleTask {
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    @Autowired
    private  ImageManagerService imageManagerService;

    /**保存文件并上传
     *
     * @param base64Str
     * @param token
     * @return
     */
    public String handleRequest(String base64Str,String token){
        executor.execute(
        new Thread(new MyTask(imageManagerService,base64Str,token)));
        return token;
    }

}
class MyTask implements Runnable{
    private final String base64Str;
    private final String token;
    private final ImageManagerService imageManagerService;
    MyTask(ImageManagerService imageManagerService,String base64Str,String token){
        this.imageManagerService=imageManagerService;
        this.base64Str=base64Str;
        this.token=token;
    }
    @Override
    public void run() {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            //上传文件并保存图片
            imageManagerService.saveImages(bytes,token);
            System.out.println(Thread.currentThread().getName()+"---result:"+token);

         }catch (Exception e){
            e.printStackTrace();
         }
    }
}
