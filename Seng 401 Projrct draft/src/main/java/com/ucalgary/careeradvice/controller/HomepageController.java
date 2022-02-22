package com.ucalgary.careeradvice.controller;

import com.ucalgary.careeradvice.dal.StorageDAL;
import com.ucalgary.careeradvice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLCollection;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Autowired
    private StorageDAL dal;

    // stumbled upon this (though not relevant, might be useful in the future):
    // https://github.com/donnemartin/system-design-primer
    @GetMapping
    public String homepage(Model model) {
        return "homepage";
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

}

