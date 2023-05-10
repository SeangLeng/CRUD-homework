package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.service.FileService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("file-service")
public class FileRestController {
    private final FileService fileService;
    @PostMapping("/file-upload")
    public Response<?> fileUpload(@RequestParam MultipartFile file){
        String filename = fileService.uploadFile(file);
        return Response.<Object>ok().setPayload(filename);
    }
}
