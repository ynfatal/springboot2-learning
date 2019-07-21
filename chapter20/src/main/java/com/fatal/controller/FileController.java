package com.fatal.controller;

import com.fatal.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传控制器
 * @author: Fatal
 * @date: 2018/10/25 0025 19:47
 */
@Controller
public class FileController {

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {
        if (files.length == 0) {
            redirectAttributes.addFlashAttribute("message", "Select at least one file to upload");
            return "redirect:uploadStatus";
        }

        try {
            fileUtil.uploadFiles(files);
            List<String> fileNames = Arrays.stream(files)
                    .map(MultipartFile::getOriginalFilename)
                    .collect(Collectors.toList());
            redirectAttributes.addFlashAttribute("message", "Successful file uploaded");
            redirectAttributes.addFlashAttribute("files", fileNames);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("uploaded fail");
        }

        return "redirect:/uploadStatus";
    }

}
