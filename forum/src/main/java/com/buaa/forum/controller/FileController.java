package com.buaa.forum.controller;

import com.buaa.forum.bean.Result;
import com.buaa.forum.uitl.FileUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload")
public class FileController {
    @Value("${web.upload-path}")
    private String path;
    @Value("${web.image-path}")
    private String imagePath;

    @PostMapping(value = "/images")
    public Result uploadImage(@RequestParam(value = "file")MultipartFile file) {
        String fileName = FileUtil.upload(file, path, file.getOriginalFilename());
        return new Result<>(1, "upload", imagePath + fileName);
    }
}
