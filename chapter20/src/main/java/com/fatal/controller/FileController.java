package com.fatal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传控制器
 * @author: Fatal
 * @date: 2018/10/25 0025 19:47
 */
@Controller
public class FileController {

    private static String UPLOADED_FOLDER = "E:/upload/";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            uploadByMultipartFile(file, UPLOADED_FOLDER, file.getOriginalFilename());
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("uploaded fail");
        }

        return "redirect:/uploadStatus";
    }

    /**
     * @param file 文件
     * @param filePath 文件保存路径
     * @param filename  文件名（包括后缀）
     * private void uploadByxxx(MultipartFile file, String filePath, String filename)
     * 注意：这三个方法都一样，如果图片名字格式相同，则会覆盖
     */

    /**
     * FileStreams
     */
    private void uploadByFileStream(MultipartFile file, String filePath, String filename) throws Exception {
        byte[] bytes = file.getBytes();
        InputStream in = file.getInputStream();
        File desc = new File(filePath);
        // 判断文件是否存在，不存在则新建一个
        if (!desc.exists()) {
            desc.mkdir();
        }
        FileOutputStream out = new FileOutputStream(filePath + filename);
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
        in.close();
    }


    /**
     * nio
     */
    private void uploadByFiles(MultipartFile file, String filePath, String filename) throws Exception {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath + filename);
        File desc = new File(filePath);
        // 判断文件是否存在，不存在则新建一个
        if (!desc.exists()) {
            desc.mkdir();
        }
        Files.write(path, bytes);
    }

    /**
     * MultipartFile
     */
    private void uploadByMultipartFile(MultipartFile file, String filePath, String filename) throws Exception {
        File desc = new File(filePath);
        // 判断文件是否存在，不存在则新建一个
        if (!desc.exists()) {
            desc.mkdir();
        }
        file.transferTo(new File(filePath + filename));
    }

}
