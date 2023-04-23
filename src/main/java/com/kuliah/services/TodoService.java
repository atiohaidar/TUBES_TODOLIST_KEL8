package com.kuliah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.models.entities.TodoEntity;
import com.kuliah.models.repos.TodoRepo;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    public Iterable<TodoEntity> findAllByKategori(Integer kategori_id){
        return todoRepo.findByKategoriId(kategori_id);
    }
    public TodoEntity findOne(Integer id){
        Optional<TodoEntity> temp = todoRepo.findById(id);
        if (!temp.isPresent()){
            return null;
        }
        return temp.get();
    }
    public TodoEntity save(TodoEntity Todo){
        return todoRepo.save(Todo);
    }
    public void removeBy(Integer id){
        todoRepo.deleteById(id);
        
    }

}
