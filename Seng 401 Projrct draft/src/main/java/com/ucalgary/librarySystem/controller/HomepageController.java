package com.ucalgary.librarySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.Book;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Autowired
    private StorageDAL dal;

    // stumbled upon this (though not relevant, might be useful in the future):
    // https://github.com/donnemartin/system-design-primer
    @GetMapping
    public String homepage(Model model) {
        return "index";
    }

    @GetMapping("/homepage")
    public String homePage2(Model model) {
        return homepage(model);
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model){

        return "GuestSearch";
    }

    @RequestMapping("/GuestSearch")
    public String search2(@RequestParam(name = "bookName", required = true) String bookName, Model model){
        List<Book> books=dal.searchByBookName(bookName);
        if(books.size() == 0)
        {
            return "BookError2";
        }
        if(books.size() != 0){
            model.addAttribute("books",books);
            return "GuestSearchRes";
        }
        else{
            
            return "BookError2";
        }
    }

}

