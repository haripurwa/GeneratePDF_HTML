package com.example.pdfExample.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
@Setter
@Getter
@Entity
@ToString
public class DataProfil {
    @Id
    private String id;
    private String nama;
    private String umur;
    private String alamat;
    private String jenisKelamin;
}
