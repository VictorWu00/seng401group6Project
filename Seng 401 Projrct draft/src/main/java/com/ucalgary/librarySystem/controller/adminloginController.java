package com.ucalgary.librarySystem.controller;

import java.util.List;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ucalgary.librarySystem.model.Admin;
import com.ucalgary.librarySystem.model.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adminloginController {
    @Autowired
    private StorageDAL dal;
    private String Email;

    @RequestMapping("/adminlogin")
    public String jumpToAdminlogin(){
        return "adminlogin";
    }

    @RequestMapping("/signin")
    public String adminSignin(Model model, @RequestParam(name = "un", required = false) String username,
                             @RequestParam(name = "pw", required = false) String password){
        if (username == "" || password == "") {
            // when either username or password is emtpy, refresh the page.
            return "adminlogin";
        }
        else if(dal.isAdmin(username, password)){
            this.Email=username;
            return "admin";
        }
        else{
            return "AdminError";
        }
    }

    @RequestMapping("/admin")
    public String jumpToAdmin(){
        return "admin";
    }

    @RequestMapping("/AddBook")
    public String jumpToAddBook(){
        return "AddBook";
    }

    @RequestMapping("/AdminInfo")
    public String jumpToInfopage(Model model){
        Admin res = dal.getAdminByEmail(Email);
        model.addAttribute("adminID", res.getAdminID());
        model.addAttribute("adminName", res.getAdminName());
        model.addAttribute("adminAddress", res.getAdminAddress());
        model.addAttribute("adminPhone", res.getAdminPhone());
        model.addAttribute("adminBirth", res.getAdminBirth());
        model.addAttribute("adminEmail", res.getEmail());
        model.addAttribute("adminLibraryId", res.getLibraryID());
        return "AdminInfo";
    }

    @RequestMapping("/AddB")
    public String jumpToAdd(Model model, @RequestParam(name = "a", required = false) int isbn, @RequestParam(name = "b", required = false) String bname, @RequestParam(name = "c", required = false) String category, @RequestParam(name = "d", required = false) int year, @RequestParam(name = "e", required = false) String author, @RequestParam(name = "f", required = false) String publisher, @RequestParam(name = "g", required = false) String section, @RequestParam(name = "h", required = false) int location, @RequestParam(name = "i", required = false) String description){

        dal.addBook(isbn, bname, description, category, year, author, publisher, section, location);
        return "AddB";
    }

    @RequestMapping("/DeleteBook")
    public String jumpToDeleteBook(){
        return "DeleteBook";
    }

    @RequestMapping("/DeleteB")
    public String jumpToDeleteB(Model model, @RequestParam(name = "name", required = false) String bName, @RequestParam(name = "author", required = false) String author){
        dal.deleteBook(bName, author);
        return "DeleteB";
    }

    @RequestMapping("/ModifyUser")
    public String jumpToModify(){
        return "ModifyUser";
    }

    @RequestMapping("/ModifyU")
    public String jumpToModifyU(Model model, @RequestParam(name = "a", required = false) String address, @RequestParam(name = "b", required = false) String phone, @RequestParam(name = "c", required = false) String birth, @RequestParam(name = "d", required = false) double balance, @RequestParam(name = "e", required = false) String name){
        
        System.out.println(address);
        System.out.println(birth);
        System.out.println(phone);
        System.out.println(balance);
        System.out.println(name);
    
        dal.modifyUser(name, address, phone, birth, balance);
        return "ModifyU";
    }


}

