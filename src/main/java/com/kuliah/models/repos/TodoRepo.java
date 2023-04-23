package com.kuliah.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.models.entities.TodoEntity;

public interface TodoRepo extends CrudRepository<TodoEntity, Integer>{
    Iterable<TodoEntity>  findByKategoriId(Integer id);
}
