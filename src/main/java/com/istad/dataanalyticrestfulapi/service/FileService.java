package com.istad.dataanalyticrestfulapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile (MultipartFile file);
    String deleteFileByName(String filename);
    String deleteAllFiles();
}
