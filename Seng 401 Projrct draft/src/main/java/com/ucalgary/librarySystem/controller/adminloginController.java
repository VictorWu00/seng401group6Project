package com.ucalgary.librarySystem.controller;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String jumpToAdd(Model model, @RequestParam(name = "a", required = false) String Isbn, @RequestParam(name = "b", required = false) String bname, @RequestParam(name = "c", required = false) String category, @RequestParam(name = "d", required = false) String Year, @RequestParam(name = "e", required = false) String author, @RequestParam(name = "f", required = false) String publisher, @RequestParam(name = "g", required = false) String section, @RequestParam(name = "i", required = false) String description){
        if(Isbn.equals("") || bname.equals("") || category.equals("") || Year.equals("") || author.equals("") || publisher.equals("") || section.equals("")  || description.equals("")){
            return "noInputForAddB";
        }
        
        int isbn=Integer.parseInt(Isbn);
        int year=Integer.parseInt(Year);
        dal.addBook(isbn, bname, description, category, year, author, publisher, section);
        return "AddB";
    }

    @RequestMapping("/DeleteBook")
    public String jumpToDeleteBook(){
        return "DeleteBook";
    }

    @RequestMapping("/DeleteB")
    public String jumpToDeleteB(Model model, @RequestParam(name = "name", required = false) String bName, @RequestParam(name = "author", required = false) String author){
        if(bName.equals("") || author.equals("")){
            return "errorForDeleteB";
        }
        dal.deleteBook(bName, author);
        return "DeleteB";
    }

    @RequestMapping("/ModifyUser")
    public String jumpToModify(){
        return "ModifyUser";
    }

    @RequestMapping("/ModifyU")
    public String jumpToModifyU(Model model, @RequestParam(name = "a", required = false) String address, @RequestParam(name = "b", required = false) String phone, @RequestParam(name = "d", required = false) String Balance, @RequestParam(name = "e", required = false) String name){
        if(!phone.equals("*"))
        {
            try {
                Double.parseDouble(phone);
            } catch (NumberFormatException e) {
                return "WrongPhoneNumberFormat";
            }
        }

        if(address.equals("") || phone.equals("") || Balance.equals("") || name.equals(""))
        {
            return "errorForModifyU";
        }
        double balance=Double.parseDouble(Balance);;
        dal.modifyUser(name, address, phone, balance);
        return "ModifyU";
    }
    
    @RequestMapping("/DeleteBorrow")
    public String jumpToDeleteBorrow(){
        return "DeleteBorrow";
    }

    @RequestMapping("/DeleteBorrw")
    public String jumpToDeleteBorrw(@RequestParam(name = "a", required = false) String UID , @RequestParam(name = "b", required = false) String BID){
        if(UID.equals("") || BID.equals("")){
            return "errorForDeleteBorrow";
        }
        
        int uID=Integer.parseInt(UID);
        int bID=Integer.parseInt(BID);
        boolean res=dal.deleteBorrowInfo(uID, bID);
        if(res==true){
            return "DeleteBorrw";
        }
        else{
            return "errorDBorrow";
        }
    }

}

