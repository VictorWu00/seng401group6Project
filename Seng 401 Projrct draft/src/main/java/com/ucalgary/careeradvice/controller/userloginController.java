package com.ucalgary.careeradvice.controller;

import com.ucalgary.careeradvice.dal.StorageDAL;
import com.ucalgary.careeradvice.repository.AdminRepository;
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

    @RequestMapping("/usignin")
    public String userSignin(Model model, @RequestParam(name = "ab", required = false) String username,
                             @RequestParam(name = "cd", required = false) String password){
        if (username == null || password == null) {
            // when either username or password is emtpy, refresh the page.
            return "userlogin";
        }
        else if(dal.isUser(username, password)){
            return "homepage";
        }
        else{
            return "loginpage";
        }

    }
}

