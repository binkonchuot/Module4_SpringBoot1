package com.example.module4_spring1.repository;

import com.example.module4_spring1.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface UserRepo extends PagingAndSortingRepository<User,Long> {
}
