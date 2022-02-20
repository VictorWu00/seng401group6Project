package com.ucalgary.careeradvice.dal;

import com.ucalgary.careeradvice.model.User;
import com.ucalgary.careeradvice.repository.AdminRepository;
import com.ucalgary.careeradvice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides access to stored data, whether through a database or a file stored on the device.
 * <p>
 * This class provides an abstraction layer over the repository layer for easy mocking/testing of data.
 */
@Service
public class StorageDAL {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AdminRepository adminRepository;

    public User getUser(String username, String password) {
        return userRepository.getUser(username, password);
    }


    public boolean isAdmin(String username, String password){
        boolean auth = adminRepository.isAdmin(username,password);
        return auth;
    }

    public boolean isUser(String username, String password){
        boolean auth = UserRepository.isUser(username,password);
        return auth;
    }
}

