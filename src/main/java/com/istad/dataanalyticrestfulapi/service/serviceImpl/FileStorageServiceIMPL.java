package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
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
        // Find location that your file located
        Path imagesLocation = Paths.get(serverLocation);
        List<File> allFile = List.of(Objects.requireNonNull(imagesLocation.toFile().listFiles()));

        // filter file that we are going to delete.
        File deleteFile = allFile.stream().filter(file -> file.getName().equals(filename)).findFirst().orElse(null);

        // delete file by name
        if (deleteFile != null){
            try {
                Files.delete(deleteFile.toPath());
                return "Delete file successfully";
            } catch (IOException e) {
                System.out.println("Error delete file by name : " + e.getMessage());
                return "File " + filename + " does not exist!";
            }
        }else{
            // cannot delete because  there is no images.
            return "Filename does not exist!";
        }
    }

    @Override
    public String deleteAllFiles() {
        Path imagesLocation = Paths.get(serverLocation);
        List<File> allFile = List.of(Objects.requireNonNull(imagesLocation.toFile().listFiles()));
        if (allFile.isEmpty()){
            return "There are no file to delete!";
        }else{
            FileSystemUtils.deleteRecursively(imagesLocation.toFile());
        }
        return null;
    }

    @Override
    public Resource loadFileAsResource(String filename) {
        Path imageLocation = Paths.get(serverLocation);
        try {
            Path file = imageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()){
                return resource;
            }else{
                throw new RuntimeException("Cannot read the file!");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException("Cannot resolve image!");
        }
    }

}
