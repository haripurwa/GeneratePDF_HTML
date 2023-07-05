package com.example.pdfExample.util;

import com.example.pdfExample.model.DataProfil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Slf4j
@Service
public class PDFUtil {

    public String dataProfileMappingTemplate(DataProfil dataProfil) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        //mapping id field html vs data from db
        Context context = new Context();
        context.setVariable("nik", dataProfil.getId());
        context.setVariable("full_name", dataProfil.getNama());
        context.setVariable("gender", dataProfil.getJenisKelamin());
        context.setVariable("birth_place", dataProfil.getUmur());

        //------------Silahkan Lanjutkan Sendiri--------------------///


        return templateEngine.process("EKYC", context);
    }


    public void generatePdfFromHtml(String html, String filePath) {
        try {
            OutputStream outputStream = new FileOutputStream(filePath);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();
        } catch (Exception e) {
            log.error("ERROR generatePdfFromHtml: {}", filePath, e);
        }
    }

}
