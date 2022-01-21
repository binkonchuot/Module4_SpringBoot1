package com.example.module4_spring1.repository;

import com.example.module4_spring1.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}
