package com.ucalgary.librarySystem.controller;

import com.ucalgary.librarySystem.dal.StorageDAL;
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

    @RequestMapping("/userlogin")
    public String jumpToUserlogin(){
        return "userlogin";
    }

    @RequestMapping("/UserSearch")
    public String jumpToSearchpage(){
        return "UserSearch";
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
                             @RequestParam(name = "cd", required = false) String Password){
        if(Email =="" && Password ==""){
            return "index";
        }
        else if(dal.registerUser(Email, Password) > 0){
            return "userlogin";
        }
        else{
            return "error";
        }
    }


}

