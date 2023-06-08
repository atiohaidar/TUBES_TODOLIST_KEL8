package com.kuliah.dto;

public class KategoriData {
    private Integer id;
    private String nama;
   
   
    public String getNama() {
        return nama;
    }
    public Integer getId(){
        return id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setId(Integer id){
        this.id = id;
    }

}
