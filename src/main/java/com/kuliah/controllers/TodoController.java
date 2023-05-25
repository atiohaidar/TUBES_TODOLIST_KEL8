package com.kuliah.controllers;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuliah.dto.ResponseData;
import com.kuliah.dto.TodoData;
import com.kuliah.models.entities.TodoEntity;
import com.kuliah.services.KategoriService;
import com.kuliah.services.TodoService;

@RestController
@RequestMapping("/{id_kategori}")
public class TodoController {
        @Autowired

    TodoService todoService;
    @Autowired

    KategoriService kategoriService;
    @GetMapping
    public ResponseEntity<ResponseData<ArrayList<TodoData>>>  showAllTodoByKategori(@PathVariable("id_kategori") Integer id_kategori){
        ResponseData<ArrayList<TodoData>> responseData = new ResponseData<>();
        Iterable<TodoEntity> all_Todo = todoService.findAllByKategoriId(id_kategori);
        ArrayList<TodoData> all_todoData = new ArrayList<>();
        for (TodoEntity todoEntity : all_Todo) {
            all_todoData.add(new TodoData(todoEntity.getId(), todoEntity.getNama(), todoEntity.getKategori().getId()));
        }
        responseData.setStatus(true);
        responseData.setPayload(all_todoData);
        
    return ResponseEntity.ok(responseData);
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable("id") Integer id){
        TodoEntity todo = todoService.findOne(id);
        todo.getKategori().getId();

        return "/Kategori/Todo/editTodo";
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseData<TodoData>> editTodoPost(@PathVariable("id_kategori") Integer id_kategori, @PathVariable("id") Integer id , @RequestBody TodoData edited_todo, Errors errors){
        ResponseData<TodoData> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        TodoEntity todoEntity = new TodoEntity();
        
        todoEntity.setNama(edited_todo.getNama());
        todoEntity.setKategori(kategoriService.findOne(id_kategori));
        todoEntity.setId(id);
        todoEntity = todoService.save(todoEntity);

        responseData.setStatus(true);
        responseData.setPayload(new TodoData(todoEntity.getId(), todoEntity.getNama(), id_kategori));

        
        
        return ResponseEntity.ok(responseData);

        
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTodoPost(@PathVariable("id") Integer id){
        
        ResponseData<String> responseData = new ResponseData<>();
        // if (errors.hasErrors()){
        //     for (ObjectError error: errors.getAllErrors()){
        //         responseData.getMessage().add(error.getDefaultMessage());
        //     }
        //     responseData.setStatus(false);
        //     responseData.setPayload(null);
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        // }
        TodoData todoData = new TodoData();
        
        responseData.setStatus(true);
        todoService.removeBy(id);
        responseData.setPayload("berhasil");
        return ResponseEntity.ok(responseData);


    }
    @GetMapping("/add")
    public String createTodoGet(@PathVariable("id_kategori") Integer id_kategori){
        return "/Kategori/Todo/tambahTodo";
    }
    @PostMapping("/add")
    public  ResponseEntity<ResponseData<TodoData>> createTodoPost(@PathVariable("id_kategori") Integer id_kategori, @RequestBody TodoData new_Todo, Errors errors){
        ResponseData<TodoData> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        TodoEntity todoEntity = new TodoEntity();
        
        todoEntity.setNama(new_Todo.getNama());
        todoEntity.setKategori(kategoriService.findOne(id_kategori));
        todoEntity = todoService.save(todoEntity);

        responseData.setStatus(true);
        responseData.setPayload(new TodoData(todoEntity.getId(), todoEntity.getNama(), id_kategori));

        
        
        return ResponseEntity.ok(responseData);
        
       
    }


}
