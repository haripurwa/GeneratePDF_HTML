package com.example.pdfExample.controller;

import com.example.pdfExample.dto.DownloadRequest;
import com.example.pdfExample.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/generate/pdf")
public class ProfileController {
@Autowired
private ProfileService profileService;

    @PostMapping(value = "/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> profileDownload(@RequestBody DownloadRequest downloadRequest) {

        String response = profileService.generatePdfDataProfile(downloadRequest);

        return new ResponseEntity<>(response, HttpStatus.resolve(HttpStatus.OK.value()));
    }

}
