
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传失败，文件为空");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;

        File dest = new File(UPLOAD_DIR + filename);
        dest.getParentFile().mkdirs(); // 创建目录

        try {
            file.transferTo(dest);
            return Result.success("/api/upload/files/" + filename); // 返回访问路径
        } catch (IOException e) {
            return Result.error("上传失败：" + e.getMessage());
        }
    }

    @GetMapping("/files/{filename:.+}")
    public byte[] download(@PathVariable String filename) throws IOException {
        File file = new File(UPLOAD_DIR + filename);
        return java.nio.file.Files.readAllBytes(file.toPath());
    }
}
