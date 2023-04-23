package com.kuliah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.models.entities.KategoriEntity;
import com.kuliah.models.repos.KategoriRepo;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class KategoriService {
    @Autowired
    private KategoriRepo kategoriRepo;

    public Iterable<KategoriEntity> findAll(){
        return kategoriRepo.findAll();
    }
    public KategoriEntity findOne(Integer id){
        Optional<KategoriEntity> temp = kategoriRepo.findById(id);
        if (!temp.isPresent()){
            return null;
        }
        return temp.get();
    }
    public KategoriEntity save(KategoriEntity kategori){
        return kategoriRepo.save(kategori);
    }
    public void removeBy(Integer id){
        kategoriRepo.deleteById(id);
        
    }

}
