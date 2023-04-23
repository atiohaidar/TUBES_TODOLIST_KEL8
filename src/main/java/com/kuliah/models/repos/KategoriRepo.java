package com.kuliah.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.models.entities.KategoriEntity;

public interface KategoriRepo extends CrudRepository<KategoriEntity, Integer>{
    
}
