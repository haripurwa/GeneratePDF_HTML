package com.example.pdfExample.repository;

import com.example.pdfExample.model.DataProfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<DataProfil, String> {
}
