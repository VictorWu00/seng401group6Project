package com.ucalgary.careeradvice.controller;

import com.ucalgary.careeradvice.dal.StorageDAL;
import com.ucalgary.careeradvice.repository.AdminRepository;
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
        if (username == null || password == null) {
            // when either username or password is emtpy, refresh the page.
            return "adminlogin";
        }
        else if(dal.isAdmin(username, password)){
            return "admin";
        }
        else{
            return "adminlogin";
        }

    }
}

