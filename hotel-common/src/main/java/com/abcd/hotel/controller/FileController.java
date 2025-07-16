package com.abcd.hotel.controller;

import com.abcd.hotel.utils.FileUtils;
import com.abcd.hotel.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class FileController {

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) throws Exception{

        log.info("upload file:"+file);

        String url = FileUtils.uploadFile(file);

        return ResponseResult.success(url);

    }

    @GetMapping("/remove/{fileName}")
    public ResponseResult remove(@PathVariable String fileName) throws Exception{

        boolean result = FileUtils.removeFile(fileName);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("图片删除失败或者不存在该图片!");

    }

}
