package com.ucalgary.librarySystem.controller;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adminloginController {
    @Autowired
    private StorageDAL dal;


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
            return "admin";
        }
        else{
            return "error";
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

    @RequestMapping("/AddB")
    public String jumpToAdd(Model model, @RequestParam(name = "a", required = false) int isbn, @RequestParam(name = "b", required = false) String bname, @RequestParam(name = "c", required = false) String category, @RequestParam(name = "d", required = false) int year, @RequestParam(name = "e", required = false) String author, @RequestParam(name = "f", required = false) String publisher, @RequestParam(name = "g", required = false) String section, @RequestParam(name = "h", required = false) int location, @RequestParam(name = "i", required = false) String description){
        /*System.out.print(isbn);
        System.out.print(bname);
        System.out.print(category);
        System.out.print(year);
        System.out.print(author);
        System.out.print(publisher);
        System.out.print(location);
        System.out.print(section);
        System.out.print(description);*/

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

}

