package com.example.module4_spring1.service;

import com.example.module4_spring1.model.User;
import com.example.module4_spring1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{
@Autowired
UserRepo userRepo;

    @Override
    public Page<User> findAll(Pageable pageable) {
       return userRepo.findAll(pageable);
    }
    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).get();
    }
}

