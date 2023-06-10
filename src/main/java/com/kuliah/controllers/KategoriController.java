package com.kuliah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuliah.dto.KategoriData;
import com.kuliah.dto.ResponseData;
import com.kuliah.models.entities.KategoriEntity;
import com.kuliah.services.KategoriService;
@Controller
@RequestMapping("")
public class KategoriController {
    @Autowired
    KategoriService kategoriService;
    @GetMapping
    
    public String showAllKategori(Model model){  
        
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
        responseData.setStatus(true);
        responseData.setPayload(all_kategori); 
        model.addAttribute("all_kategori", all_kategori);

return !AuthController.is_login ? "redirect:/login" :"Kategori/lihatKategori.html";
    }

    @GetMapping("/edit/{id}")
    public String editKategori(@PathVariable("id") Integer id, Model model){
        
        KategoriEntity kategori = kategoriService.findOne(id);
        KategoriData kategoriData = new KategoriData();
        kategoriData.setNama(kategori.getNama());
        kategoriData.setId(kategori.getId());
        model.addAttribute("kategori", kategori);
        return !AuthController.is_login ? "redirect:/login": "Kategori/editKategori.html";

    }
    @PostMapping("/edit/{id}")
    public String editKategoriPost(KategoriEntity kategoriEntity){
        // KategoriEntity kategoriEntity = new KategoriEntity(, kategoriData.getNama());
        System.out.println(kategoriEntity.getId());
        System.out.println("minta perhatian");
        kategoriService.save(kategoriEntity);

        return !AuthController.is_login ? "redirect:/login": "redirect:/";

        
        
    }
    @GetMapping("/delete/{id}")
    public String deleteKategori(@PathVariable("id") Integer id, Model model){
        KategoriEntity kategori = kategoriService.findOne(id);
        model.addAttribute("kategori", kategori);
        return !AuthController.is_login ? "redirect:/login": "Kategori/hapusKategori.html";
    
    }
    @PostMapping("/delete/{id}")
    public String deleteKategoriPost( @PathVariable("id") Integer id){

        kategoriService.removeBy(id);
        

            
        return !AuthController.is_login ? "redirect:/login": "redirect:/";
    }
    @GetMapping("/add")
    public String createKategoriGet(Model model){
        model.addAttribute("new_kategori", new KategoriData());
        return !AuthController.is_login ? "redirect:/login": "Kategori/tambahKategori.html";
    }
    @PostMapping("/add")
    public String createKategoriPost(KategoriData kategoriData){
        System.out.print("Berhasil?");
        
        KategoriEntity kategoriEntity = new KategoriEntity();
        kategoriEntity.setNama(kategoriData.getNama());
        
        kategoriService.save(kategoriEntity);
        
        
        return !AuthController.is_login ? "redirect:/login": "redirect:/";
    }


}
