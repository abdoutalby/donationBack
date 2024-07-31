package com.uib.donation.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

    ResponseEntity<?> saveFile(MultipartFile file) throws IOException;
    ResponseEntity<?> deleteFile(String name) throws IOException;
    ResponseEntity<?> loadFile(String name) throws IOException;
}
