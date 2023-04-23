package com.kuliah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuliah.models.entities.TodoEntity;
import com.kuliah.services.TodoService;

@Controller
@RequestMapping("/{id_kategori}")
public class TodoController {
    @Autowired
    TodoService todoService;
    @GetMapping
    public String showAllTodoByKategori(@PathVariable("id_kategori") Integer id_kategori){
        Iterable<TodoEntity> all_Todo = todoService.findAllByKategori(id_kategori);
        
return "/Kategori/Todo/lihatTodo";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable("id") Integer id){
        TodoEntity Todo = todoService.findOne(id);
        return "/Kategori/Todo/editTodo";
    }
    @PostMapping("/edit/{index}")
    public String editTodoPost( @RequestBody TodoEntity Todo){
        todoService.save(Todo);

        return "/Kategori/Todo/editTodo";
    }
    @GetMapping("/delete/{id}")
    public String deleteTodo( @PathVariable("id") Integer id){
        TodoEntity Todo = todoService.findOne(id);

        return "/Kategori/Todo/hapusTodo";
    }
    @PostMapping("/delete/{id}")
    public String deleteTodoPost(@RequestBody boolean hapus, @PathVariable("id") Integer id){
            if (hapus){
                todoService.removeBy(id);
            }
        return "/Kategori/Todo/hapusTodo";
    }
    @GetMapping("/add")
    public String createTodoGet(@PathVariable("id_kategori") Integer id_kategori){
        return "/Kategori/Todo/tambahTodo";
    }
    @PostMapping("/add")
    public String createTodoPost(@RequestBody TodoEntity new_Todo){
        
        todoService.save(new_Todo);
        return "/Kategori/Todo/tambahTodo";
    }


}
