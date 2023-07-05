package com.example.pdfExample.service;

import com.example.pdfExample.dto.DownloadRequest;
import com.example.pdfExample.model.DataProfil;
import com.example.pdfExample.repository.ProfileRepo;
import com.example.pdfExample.util.PDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Value("${download.profile.path}")
    private String downloadPath;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private PDFUtil pdfUtil;


    @Override
    public String generatePdfDataProfile(DownloadRequest downloadRequest) {

        //get data from database by id
        Optional<DataProfil> profile = profileRepo.findById(downloadRequest.getId());
        //validation data empty
        if (profile.isEmpty()) {
            log.info("DATA TIDAK DITEMUKAN");
            return "DATA TIDAK DITEMUKAN BOSS";
        }

        try {
            //membuat nama file yang akan dipakai
            String fileName = String.format("namafile_%s_%s.pdf", profile.get().getNama(), UUID.randomUUID());

            //prosses mapping data
            String html = pdfUtil.dataProfileMappingTemplate(profile.get());

            //location download
            String filePath = downloadPath + File.separator + fileName;

            //generate pdf
            pdfUtil.generatePdfFromHtml(html, filePath);
            return "SUCCESS BOSS";


        } catch (Exception e) {
            return "ERROR GAN  = " + e.getMessage();
        }


    }
}
