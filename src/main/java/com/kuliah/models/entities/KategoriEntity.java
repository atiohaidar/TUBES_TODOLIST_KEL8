package com.kuliah.models.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kategori")
public class KategoriEntity implements Serializable{
    private static final long serialVersionUID = 1L; // klo impement serializable, disarankan ada ini
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public KategoriEntity() {
    }
    public KategoriEntity(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    private String nama;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    
}
