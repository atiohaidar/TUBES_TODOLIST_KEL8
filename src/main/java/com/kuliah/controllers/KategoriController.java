package com.kuliah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuliah.dto.KategoriData;
import com.kuliah.dto.ResponseData;
import com.kuliah.models.entities.KategoriEntity;
import com.kuliah.services.KategoriService;
@RestController
@RequestMapping("")
public class KategoriController {
    @Autowired
    KategoriService kategoriService;
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<KategoriEntity>>> showAllKategori(){
        ResponseData<Iterable<KategoriEntity>> responseData = new ResponseData<>();
        Iterable<KategoriEntity> all_kategori = kategoriService.findAll();
        // if (errors.hasErrors()){
        //     for (ObjectError error: errors.getAllErrors()){
        //         responseData.getMessage().add(error.getDefaultMessage());
        //     }
        //     responseData.setStatus(false);
        //     responseData.setPayload(null);
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        // }
        responseData.setStatus(true);
        responseData.setPayload(all_kategori);
return ResponseEntity.ok(responseData);
    }

    @GetMapping("/edit/{id}")
    public KategoriEntity editKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);
        return kategori;
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseData<KategoriEntity>> editKategoriPost(@PathVariable("id") Integer id,@RequestBody KategoriData kategoriData, Errors errors){
        ResponseData<KategoriEntity> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        KategoriEntity kategoriEntity = new KategoriEntity();
        kategoriEntity.setNama(kategoriData.getNama());
        kategoriEntity.setId(id);
        responseData.setStatus(true);
        responseData.setPayload(kategoriService.save(kategoriEntity));

        ;
        
        return ResponseEntity.ok(responseData);
        
    }
    @GetMapping("/delete/{id}")
    public KategoriEntity deleteKategori(@PathVariable("id") Integer id){
        KategoriEntity kategori = kategoriService.findOne(id);

        return kategori;
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteKategoriPost(/*@RequestBody boolean hapus,*/ @PathVariable("id") Integer id){
        ResponseData<String> responseData = new ResponseData<>();

        kategoriService.removeBy(id);
        responseData.setStatus(true);
        responseData.setPayload("berhasil");

            
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/add")
    public String createKategoriGet(){
        return "silahkan";
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseData<KategoriEntity>> createKategoriPost(@RequestBody KategoriData kategoriData, Errors errors){
        System.out.print("Berhasil?");
        ResponseData<KategoriEntity> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        KategoriEntity kategoriEntity = new KategoriEntity();
        kategoriEntity.setNama(kategoriData.getNama());
        responseData.setStatus(true);
        responseData.setPayload(kategoriService.save(kategoriEntity));

        
        
        return ResponseEntity.ok(responseData);
    }


}
