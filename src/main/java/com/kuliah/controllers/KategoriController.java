package com.kuliah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuliah.models.entities.KategoriEntity;
import com.kuliah.services.KategoriService;

@Controller
@RequestMapping("")
public class KategoriController {
    @Autowired
    KategoriService kategoriService;
    @GetMapping
    public String showAllKategori(){
        Iterable<KategoriEntity> all_kategori = kategoriService.findAll();
        
return "/Kategori/lihatKategori";
    }

    @GetMapping("/edit/{id}")
    public String editKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);
        return "/Kategori/editKategori";
    }
    @PostMapping("/edit/{index}")
    public String editKategoriPost(@RequestBody KategoriEntity kategori){
        kategoriService.save(kategori);

        return "/Kategori/editKategori";
    }
    @GetMapping("/delete/{id}")
    public String deleteKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);

        return "/Kategori/hapusKategori";
    }
    @PostMapping("/delete/{id}")
    public String deleteKategoriPost(@RequestBody boolean hapus, @PathVariable("id") Integer id){
            if (hapus){
                kategoriService.removeBy(null);
            }
        return "/Kategori/hapusKategori";
    }
    @GetMapping("/add")
    public String createKategoriGet(){
        return "/Kategori/tambahKategori";
    }
    @PostMapping("/add")
    public String createKategoriPost(@RequestBody KategoriEntity new_kategori){
        kategoriService.save(new_kategori);
        return "/Kategori/tambahKategori";
    }


}
