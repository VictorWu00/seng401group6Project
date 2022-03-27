package com.ucalgary.librarySystem.controller;

import com.ucalgary.librarySystem.dal.StorageDAL;
import com.ucalgary.librarySystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


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
    public String jumpToSearchpage(Model model){
        User res = dal.getUserByEmail(Email);
        String name = res.getUserName();
     model.addAttribute("name", name);
        return "UserSearch";
    }
    
    @RequestMapping("/Search")
    public String search(@RequestParam(name = "bookName", required = true) String bookName, Model model){
        User res = dal.getUserByEmail(Email);
        List<Book> books=dal.searchByBookName(bookName);
        if(books.size() == 0)
        {
            return "BookError";
        }
        List<Author> authors = dal.searchByBookAuthor(bookName);
        List<Publisher> publishers = dal.searchByBookPublisher(bookName);
        int bookID = books.get(0).getBookID();

        List<Review> reviews = dal.searchByBookReview(bookID);
        Author auth = authors.get(0);
        Publisher pub = publishers.get(0);
        if(books.size() != 0){
            model.addAttribute("books",books);
            model.addAttribute("authors", auth);
            model.addAttribute("publishers", pub);
            model.addAttribute("reviews", reviews);
            String name = res.getUserName();
            model.addAttribute("name", name);
            return "Search";
        }
        else{
            
            return "BookError";
        }
    }
    @RequestMapping("/UserRent")
    public String jumpToRent(Model model)
    {
        User res = dal.getUserByEmail(Email);
        String name = res.getUserName();
        model.addAttribute("name", name);
        return "UserRent";
    }

    @RequestMapping("/UserRent2")
    public String addRent(Model model, @RequestParam(name = "book", required = true) String bookID,
    @RequestParam(name = "sdate", required = true) Date StartDate, @RequestParam(name = "edate", required = true) Date EndDate){

        if(bookID.equals("")){
            return "AllFieldsneedFill";
        }

        int id = Integer.parseInt(bookID);
        User res = dal.getUserByEmail(Email);
        this.userId = res.getUserID();
        if(!bookID.equals(""))
        {
        boolean available = dal.searchRentedBook(id);
        if(!available)
        {
            return "rentError";
        }
        }
        else
        {
            return "enterBook";
        }
        
        boolean alRented =  dal.checkForRentedBook(id, userId);
        if(alRented)
        {
            return "rentError";
        }
        Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String s1 = sdf.format(timeNow);
        String s2 = sdf.format(StartDate);
        boolean dateC = false;
        int state = StartDate.compareTo(timeNow);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("state "+ state);
        if(s1.equals(s2) || state >=0)
        {
            dateC = true;
        }

        if(dateC)
        {
        boolean rentedBook = dal.insertRentedBook(id, userId, StartDate, EndDate);
        System.out.println(dateC);
        if(rentedBook)
        {
            dal.updateStatus(id);
            String name = res.getUserName();
        model.addAttribute("name", name);
            return "UserRent2";
        }
        else
        {
        return "rentError";
        }
        }
        else
        {
            return "currentDate";
        }
    }


    @RequestMapping("/UserRentError")
    public String checkBookForRent(@RequestParam(name = "book", required = true) String bookID)
    {
        if(!bookID.equals(""))
        {
        int id = Integer.parseInt(bookID);
        boolean available = dal.searchRentedBook(id);
        if(!available)
        {
        return "UserRentError";
        }
        else
        {
            return "UserRentAvailable";
        }
        }
        else
        {
            return "enterBook";
        }
    }
    @RequestMapping("/UserRate")
    public String jumpToRatepage(Model model){
        User res = dal.getUserByEmail(Email);
        String name = res.getUserName();
        model.addAttribute("name", name);
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
        String name = res.getUserName();
        model.addAttribute("name", name);
        System.out.println(res.getUserID());
        System.out.println(Email);


        // System.out.println(res.getUserID());
        // System.out.println(Email);

        return "UserInfo";
    }

    @RequestMapping("/UserPage")
    public String rentAndReviewInformation(Model model){
        User res = dal.getUserByEmail(Email);
        this.userId = res.getUserID();
        String name = res.getUserName();
        List<Borrow> borrows = dal.searchBorrowBooks(userId);
        model.addAttribute("borrow", borrows);
        List<Review> reviews = dal.searchReviewByUser(userId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("name", name);
        return "UserPage";
    }

    @RequestMapping("/ReviewRemove")
    public String RemoveReview(Model model, @RequestParam(name = "bookID", required = true) String id)
    {
        if(!id.equals(""))
        {
        User res = dal.getUserByEmail(Email);
        this.userId = res.getUserID();
        int bookID = Integer.parseInt(id);
        boolean check = dal.checkUserReview(userId, bookID);
        String name = res.getUserName();
        model.addAttribute("name", name);
        if(check)
        {
            dal.deleteReview(userId, bookID);
            List<Borrow> borrows = dal.searchBorrowBooks(userId);
            model.addAttribute("borrow", borrows);
            List<Review> reviews = dal.searchReviewByUser(userId);
            model.addAttribute("reviews", reviews);
            return "UserPage";     
         }
         else{
             return "ReviewNotExist";
         }
        }
        return "RemoveReviewMissing";
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
        User res = dal.getUserByEmail(Email);
       
        this.userId = res.getUserID();
        List<Borrow> borrows = dal.searchBorrowBooks(userId);
        List<Review> reviews = dal.searchReviewByUser(userId);
        model.addAttribute("borrow", borrows);
        model.addAttribute("reviews", reviews);
        String name = res.getUserName();
        model.addAttribute("name", name);
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
        else if(dal.checkEmailDuplication(Email)){
            return "EmailAlreadyExisted";
        }
        else{
            this.Email=Email;
            this.registeredPassword=Password;
            return "MoreNewUserInfo";
        }
    }

    @RequestMapping("/signUp")
    public String signUp(@RequestParam(name = "name", required = false) String Name,@RequestParam(name = "address", required = false) String Address,@RequestParam(name = "phone", required = false) String Phone,@RequestParam(name = "birth", required = false) Date Birth){
        Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
        int state=Birth.compareTo(timeNow);
        if(state>0){
            return "unsuccessSignUp";
        }

        try{
            double i=Double.parseDouble(Phone);
        }catch(NumberFormatException e){
            return "unsuccessSignUp";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birth = sdf.format(Birth);

        if(dal.registerUser(this.Email, this.registeredPassword, Name, Address, Phone, birth) > 0){
            return "userPage";
        }
        else{
            return "unsuccessSignUp";
        }
    }


    @RequestMapping("/checkBookName")
    public String checkBookName(Model model, @RequestParam(name = "book", required = true) String bookID)
    {
        if(!bookID.equals("")){
            int id = Integer.parseInt(bookID);
            List<Book> booklist = dal.searchByBookID(id);
            if(booklist.size()==0)
            {
                return "RateCheckBookError";
            }
            else
            {
                model.addAttribute("booklist",booklist.get(0).getName());
                return "RateCheckBook";
            }
        }
        else{
            return "RateCheckBookError";
        }
    }

    @RequestMapping("/postReview")
    public String postReview(Model model, @RequestParam(name="book",required = true)String bookID,
    @RequestParam(name="description",required=true)String description,
    @RequestParam(name="rating",required=true)String rating){
        if(bookID.equals("")||description.equals("")||rating.equals("")){
            return "AllFieldsneedFill";
        }
        //System.out.println("HERE");
        String formated = rating + " stars";
        int id = Integer.parseInt(bookID);
        User res = dal.getUserByEmail(Email);
        this.userId = res.getUserID();
        boolean flag = dal.HasNotReviewedYet(userId, id);
        if(!flag){
            return "HasReviewed";
        }

        List<Book> booklist = dal.searchByBookID(id);
        if(booklist.size()==0){
            return "RateCheckBookError";
        }
        Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
        boolean write_flag = dal.writeReview(userId, id, description, formated, timeNow);
        if(write_flag){
            return "RateSuccess";
        }
        else{
            return "RateCheckBookError";
        }
    }
}

