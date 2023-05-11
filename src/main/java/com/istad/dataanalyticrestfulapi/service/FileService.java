package com.istad.dataanalyticrestfulapi.service;

import com.istad.dataanalyticrestfulapi.utils.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile (MultipartFile file);
    String deleteFileByName(String filename);
    String deleteAllFiles();
    Resource loadFileAsResource(String filename);
}
