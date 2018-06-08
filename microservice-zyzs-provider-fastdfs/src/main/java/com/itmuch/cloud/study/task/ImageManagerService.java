package com.itmuch.cloud.study.task;

import com.itmuch.cloud.study.entity.ImageManager;
import com.itmuch.cloud.study.fastdfs.FastDFSClient;
import com.itmuch.cloud.study.repository.ImageManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ImageManagerService {
    @Autowired
    private ImageManagerRepository imageManagerRepository;
    @Autowired
    private FastDFSClient fastDFSClient;

    public synchronized void  saveImages(byte[] base64Str,String token){
        String fastDFSpath=fastDFSClient.uploadFile(base64Str,token);
        System.out.println(fastDFSpath);
        ImageManager imageManager =new ImageManager();
        imageManager.setImageName(token);
        imageManager.setUrl(fastDFSpath);
        imageManager.setCreateDate(new Date());
        imageManagerRepository.save(imageManager);
    }
}
