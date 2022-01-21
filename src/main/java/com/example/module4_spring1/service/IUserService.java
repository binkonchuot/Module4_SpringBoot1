package com.example.module4_spring1.service;

import com.example.module4_spring1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;

public interface IUserService {
    Page<User> findAll(Pageable pageable);
    List<User> findAll();
    void save(User user);
    void delete(Long id);
    User findById(Long id);
}
