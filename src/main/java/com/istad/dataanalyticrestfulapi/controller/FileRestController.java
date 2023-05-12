package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.model.response.FileResponse;
import com.istad.dataanalyticrestfulapi.service.FileService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("file-service")
@Slf4j
public class FileRestController {
    private final FileService fileService;
    // allow extension
    private final List<String> ALLOWED_EXTENSION = List.of("jpb", "png", "jpeg");
    private final long MAX_FILE_SIZE = 1024 * 2024 * 5;
    @PostMapping("/file-upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file){
        try {
            FileResponse response = uploadFile(file);
            return Response.<FileResponse>ok()
                    .setPayload(response)
                    .setMessage("Successfully upload file");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to upload images!" + e.getMessage());
            return Response.<FileResponse>exception()
                    .setMessage("Failed to upload an image! exception occurred!");
        }
    }

    // upload multipart file :
    // helper method
    @PostMapping("/multiple-file-upload")
    public Response<?> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        try {
            List<FileResponse> responses = Arrays.stream(files).map(this::uploadFile).collect(Collectors.toList());
            return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("Upload successfully!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed by : " + e.getMessage());
            return Response.<FileResponse>exception().setMessage("Failed to upload files");
        }
    }
    @DeleteMapping("/delete-file/{filename}")
    public Response<String> deleteSingleFile(@PathVariable("filename") String filename){
        String result = fileService.deleteFileByName(filename);
        return Response.<String>deleteSuccess().setPayload(result);
    }

    @DeleteMapping("/delete-all-files")
    public Response<String> deleteAllFiles(){
        String result = fileService.deleteAllFiles();
        return Response.<String>deleteSuccess().setPayload(result);
    }
    private FileResponse uploadFile(MultipartFile file){
        if (file.isEmpty()) throw new IllegalArgumentException("File cannot be empty!");
        // extension
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename()); ;
        assert fileExtension != null;
        if(!ALLOWED_EXTENSION.contains(fileExtension.toLowerCase())){
            throw new IllegalArgumentException("Your file type is not allow here!!!");
        }
        // file size
        if (file.getSize() > MAX_FILE_SIZE) throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);

        String filename = fileService.uploadFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/file-service/download-file/")
                .path(filename).toUriString();

        return new FileResponse().setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFiletype(file.getContentType())
                .setSize(file.getSize());
    }
    @GetMapping("/download-file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            log.info("Could not determine the the file type!!");
        }
        assert contentType != null;
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }
}
