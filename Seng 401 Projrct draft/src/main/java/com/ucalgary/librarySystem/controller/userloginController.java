package com.ucalgary.librarySystem.controller;

import java.util.ArrayList;
import java.util.List;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.Author;
import com.ucalgary.librarySystem.model.Book;
import com.ucalgary.librarySystem.model.Publisher;
import com.ucalgary.librarySystem.model.Review;
import com.ucalgary.librarySystem.model.User;
import com.ucalgary.librarySystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class userloginController {
    @Autowired
    private StorageDAL dal;
    private String Email;
    private String registeredPassword;
    private int userId;

    @RequestMapping("/userlogin")
    public String jumpToUserlogin(){
        return "userlogin";
    }

    @RequestMapping("/UserSearch")
    public String jumpToSearchpage(){
        return "UserSearch";
    }
    
    @RequestMapping("/Search")
    public String search(@RequestParam(name = "bookName", required = true) String bookName, Model model){
    
        List<Book> books=dal.searchByBookName(bookName);
        List<Author> authors = dal.searchByBookAuthor(bookName);
        List<Publisher> publishers = dal.searchByBookPublisher(bookName);
        int bookID = books.get(0).getBookID();

        List<Review> reviews = dal.searchByBookReview(bookID);
        Author auth = authors.get(0);
        Publisher pub = publishers.get(0);
        if(books.size()!=0){
            model.addAttribute("books",books);
            model.addAttribute("authors", auth);
            model.addAttribute("publishers", pub);
            model.addAttribute("reviews", reviews);
            return "Search";
        }
        else{
            return "BookError";
        }
    }

    @RequestMapping("/UserRent")
    public String jumpToRentpage(){
        return "UserRent";
    }
    @RequestMapping("/UserRate")
    public String jumpToRatepage(){
        return "UserRate";
    }
    @RequestMapping("/UserInfo")
    public String jumpToInfopage(Model model){
        User res = dal.getUserByEmail(Email);
        model.addAttribute("userID", res.getUserID());
        model.addAttribute("userName", res.getUserName());
        model.addAttribute("userAddress", res.getUserAddress());
        model.addAttribute("userPhone", res.getUserPhone());
        model.addAttribute("userBirth", res.getUserBirth());
        model.addAttribute("userEmail", res.getUserEmail());
        model.addAttribute("userBalance", res.getUserBalance());

        System.out.println(res.getUserID());
        System.out.println(Email);
        return "UserInfo";
    }

    @RequestMapping("/UserPage")
    public String jumpToUserpage(){
        return "UserPage";
    }


    @RequestMapping("/usignin")
    public String userSignin(Model model, @RequestParam(name = "ab", required = false) String email,
                             @RequestParam(name = "cd", required = false) String password){
        if (email == "" && password == "") {
            // when either username or password is emtpy, refresh the page.
            return "userlogin";
        }
        else if(dal.isUser(email, password)){
            this.Email = email;
            return "UserPage";
        }
        else{
            return "UserError";
        }
    }

    @RequestMapping("/uregister")
    public String userRegister(Model model, @RequestParam(name = "ab", required = false) String Email,
                             @RequestParam(name = "cd", required = false) String Password, @RequestParam(name = "e", required = false) String confirmedPassword){

        if(Email =="" && Password ==""){
            return "index";
        }
        else if(!Password.equals(confirmedPassword)){
            return "notEqualPassword";
        }
        else{
            this.Email=Email;
            this.registeredPassword=Password;
            return "MoreNewUserInfo";
        }
    }

    @RequestMapping("/signUp")
    public String signUp(@RequestParam(name = "name", required = false) String Name,@RequestParam(name = "address", required = false) String Address,@RequestParam(name = "phone", required = false) String Phone,@RequestParam(name = "birth", required = false) String Birth){
        if(dal.registerUser(this.Email, this.registeredPassword, Name, Address, Phone, Birth) > 0){
            return "userPage";
        }
        else{
            return "unsuccessSignUp";
        }
    }


}

