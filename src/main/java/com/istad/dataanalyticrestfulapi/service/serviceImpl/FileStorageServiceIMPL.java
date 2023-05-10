package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceIMPL implements FileService {

    private final String serverLocation = "src/main/resources/images/";
    Path fileLocationStorage;
    FileStorageServiceIMPL(){
        this.fileLocationStorage = Paths.get("src/main/resources/images/");
        try {
            if (!Files.exists(fileLocationStorage)){
                Files.createDirectories(fileLocationStorage);
            }else{
                System.out.println("Directory is already existed !!!");
            }
        }catch (Exception e){
            System.out.println("Error create directory : " + e.getMessage());
        }
    }
    @Override
    public String uploadFile(MultipartFile file) {
        // format file name;
        String filename = file.getOriginalFilename();
        // String array
        // check to see if file is empty:
        if (file.isEmpty()){
            return "file is empty!";
        }
        String[] fileCompartment = filename.split("\\.");
        filename = UUID.randomUUID() + "." + fileCompartment[1];
        Path resolvePath = fileLocationStorage.resolve(filename);
        try {
            Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String deleteFileByName(String filename) {
        return null;
    }

    @Override
    public String deleteAllFiles() {
        return null;
    }
}
