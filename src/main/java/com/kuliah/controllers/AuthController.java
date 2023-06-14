package com.kuliah.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuliah.dto.AuthData;

@Controller
@RequestMapping("")
public class AuthController {
    public static boolean is_login;
    @PostMapping("/login")
    public String loginPost(AuthData authData){
        if (authData.getPassword().equals("password")){
            this.is_login = true;

        }
        System.out.println(authData.getPassword());
        return "redirect:/";
    }
    @GetMapping("/login")
    public String loginGet(Model model){
        if (this.is_login){
            return "redirect:/";
        }
        model.addAttribute("authData", new AuthData()); 
        
        return "login.html";
    }

    
    @GetMapping("/logout")

    public String logout(){
        this.is_login = false;
        return "redirect:/login";
    }
    


}
