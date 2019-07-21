package com.fatal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 文件工具类
 * @author: Fatal
 * @date: 2019/7/21 0021 12:46
 */
@Slf4j
@Component
public class FileUtil {

    @Value("${upload.path}")
    private String uploadPath;

    /**
     * 上传一个或多个文件
     */
    public void uploadFiles(MultipartFile[] files) {
        Arrays.stream(files)
                .forEach(this::uploadByFiles);
//                .forEach(this::uploadByFileStream);
//                .forEach(this::uploadByMultipartFile);
    }

    /**
     * @param file 文件
     * @param filePath 文件保存路径
     * @param filename  文件名（包括后缀）
     * private void uploadByxxx(MultipartFile file)
     * 注意：这三个方法都一样，如果图片名字格式相同，则会覆盖
     */

    /**
     * FileStreams
     */
    private void uploadByFileStream(MultipartFile file) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            long start = System.currentTimeMillis();
            String filename = file.getOriginalFilename();
            in = (FileInputStream)file.getInputStream();
            File directory = new File(uploadPath);
            initDirectory(directory);
            out = new FileOutputStream(uploadPath + filename);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            long end = System.currentTimeMillis();
            log.info("【文件 {} 上传耗时】 [{}]", filename, end - start);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (in!= null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * nio
     */
    private void uploadByFiles(MultipartFile file) {
        try {
            long start = System.currentTimeMillis();
            String filename = file.getOriginalFilename();
            byte[] bytes = new byte[1024];
            Path path = Paths.get(uploadPath + filename);
            File directory = new File(uploadPath);
            initDirectory(directory);
            Files.write(path, bytes);
            long end = System.currentTimeMillis();
            log.info("【文件 {} 上传耗时】 [{}]", filename, end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SpringMVC 封装的上传文件的方法 MultipartFile.transferTo(File dest)
     */
    private void uploadByMultipartFile(MultipartFile file) {
        try {
            long start = System.currentTimeMillis();
            String filename = file.getOriginalFilename();
            File directory = new File(uploadPath);
            initDirectory(directory);
            file.transferTo(new File(uploadPath + filename));
            long end = System.currentTimeMillis();
            log.info("【文件 {} 上传耗时】 [{}]", filename, end - start);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化文件夹
     * @param directory
     */
    private void initDirectory(File directory) {
        // 判断文件是否存在，不存在则新建一个
        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();
            if (!mkdirs) {
                log.error("【文件上传】 文件夹创建失败 -[{}]", uploadPath);
            }
        }
    }

}
