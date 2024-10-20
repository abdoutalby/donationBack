package com.uib.donation.controllers;

import com.uib.donation.services.FileServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/files/")
@RequiredArgsConstructor
@CrossOrigin(origins = {
    "http://localhost:4200"
})
public class FileController {
    private  final FileServiceImplementation fileService ;

    @PostMapping("upload")
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

    @GetMapping("load/{name}")
    ResponseEntity<?> loadFile(@PathVariable("name") String name) throws IOException {
        return fileService.loadFile(name);
    }

    @DeleteMapping("delete/{name}")
    ResponseEntity<?> deleteFile(@PathVariable("name") String name) throws IOException {
        return fileService.deleteFile(name);
    }
}
