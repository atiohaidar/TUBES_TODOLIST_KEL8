package com.kuliah.models.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "todo")
public class TodoEntity implements Serializable{
    private static final long serialVersionUID = 1L; // klo impement serializable, disarankan ada ini
    public TodoEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public TodoEntity(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    public String nama;

    @ManyToOne
    @JoinColumn(name="kategori_id", referencedColumnName = "id" )
    public KategoriEntity kategori;
    public KategoriEntity getKategori() {
        return kategori;
    }
    public void setKategori(KategoriEntity kategori) {
        this.kategori = kategori;
    }
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
