package com.kuliah.dto;

public class TodoData {
    private Integer id;
    private String nama;
    private Integer kategori_id;
    public TodoData(){
        
    }
    public TodoData(Integer id,String nama , Integer kategori_id) {
        this.nama = nama;
        this.id = id;
        this.kategori_id = kategori_id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getKategori_id() {
        return kategori_id;
    }
    public void setKategori_id(Integer kategori_id) {
        this.kategori_id = kategori_id;
    }

}
