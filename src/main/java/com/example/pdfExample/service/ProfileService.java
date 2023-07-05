package com.example.pdfExample.service;

import com.example.pdfExample.dto.DownloadRequest;

public interface ProfileService {

    String generatePdfDataProfile(DownloadRequest downloadRequest);
}
