package com.uib.donation.services;

import com.uib.donation.utils.ImageResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Service
public class FileServiceImplementation implements FileService {

    private final Path root = Paths.get("uploads/");

    @Override
    public ResponseEntity<?> saveFile(MultipartFile file) throws IOException {
        try {

            String fileName = genereateFileName()+
                    "."+
                    file.getContentType().substring(
                            file.getContentType().indexOf("/")+1 ,
                            file.getContentType().length());
            Files.copy(
                    file.getInputStream(),
                    this.root.resolve(
                            fileName));
            ImageResponse response = new ImageResponse(
                    fileName,
                    200
            );
            return  ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
     }

    private static String genereateFileName() {
            StringBuilder sb = new StringBuilder();
            String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
            int charactersLength = characters.length();
            Random random = new Random();
            for (int i = 0; i < 12; i++) {
                char randomChar = characters.charAt(random.nextInt(charactersLength));
                sb.append(randomChar);
            }
            return sb.toString();
    }

    @Override
    public ResponseEntity<?> deleteFile(String name) throws IOException {
        Path filePath = root.toAbsolutePath().normalize().resolve(name) ;
        Files.delete(filePath);
        return  ResponseEntity.ok("deleted");
    }

    @Override
    public ResponseEntity<?> loadFile(String name) throws IOException {
           if(!name.isEmpty()){
            Path filePath = root.toAbsolutePath().normalize().resolve(name) ;
            Resource resource = new UrlResource(filePath.toUri()) ;
            HttpHeaders httpHeaders = new HttpHeaders() ;
            httpHeaders.add("File-Name" , name);
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION , "attachment;File-Name" + resource.getFilename());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                    .headers(httpHeaders).body(resource);
        }
    return ResponseEntity.ok().build();

    }
}
