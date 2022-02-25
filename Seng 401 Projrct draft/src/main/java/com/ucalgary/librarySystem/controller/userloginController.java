package com.ucalgary.librarySystem.controller;

import com.ucalgary.librarySystem.dal.StorageDAL;
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
    public String jumpToInfopage(){
        return "UserInfo";
    }

    @RequestMapping("/UserPage")
    public String jumpToUserpage(){
        return "UserPage";
    }


    @RequestMapping("/usignin")
    public String userSignin(Model model, @RequestParam(name = "ab", required = false) String username,
                             @RequestParam(name = "cd", required = false) String password){
        if (username == "" && password == "") {
            // when either username or password is emtpy, refresh the page.
            return "userlogin";
        }
        else if(dal.isUser(username, password)){
            return "UserPage";
        }
        else{
            return "UserError";
        }

    }
}

