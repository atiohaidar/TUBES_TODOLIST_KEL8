package com.kuliah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuliah.models.entities.KategoriEntity;
import com.kuliah.services.KategoriService;
@RestController
@RequestMapping("")
public class KategoriController {
    @Autowired
    KategoriService kategoriService;
    @GetMapping
    public Iterable<KategoriEntity> showAllKategori(){
        Iterable<KategoriEntity> all_kategori = kategoriService.findAll();
        
return all_kategori;
    }

    @GetMapping("/edit/{id}")
    public KategoriEntity editKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);
        return kategori;
    }
    @PostMapping("/edit/{index}")
    public String editKategoriPost(@RequestBody KategoriEntity kategori){
        kategoriService.save(kategori);

        return "berhasil";
    }
    @GetMapping("/delete/{id}")
    public KategoriEntity deleteKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);

        return kategori;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteKategoriPost(/*@RequestBody boolean hapus,*/ @PathVariable("id") Integer id){
            //if (hapus){
                kategoriService.removeBy(id);
            //}
        return "berhasil";
    }
    @GetMapping("/add")
    public String createKategoriGet(){
        return "silahkan";
    }
    @PostMapping("/add")
    public String createKategoriPost(@RequestBody KategoriEntity new_kategori){
        kategoriService.save(new_kategori);
        return "berhasil";
    }


}
