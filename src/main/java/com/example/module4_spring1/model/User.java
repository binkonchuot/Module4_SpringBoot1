package com.example.module4_spring1.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.*;

//id,user,pass,fullname,phone,email,avatar
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @Size(min = 8, message = "nhap hon 8 ki tu")
    private String user_name;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9]{8,}$", message = "> 8 ki tu ")
    private String pass;

    private String fullname;

    @Min(value = 9,message = "phone must be from 10")
    private String phone;

    @Email
    private String email;

    private String avatar;

    @ManyToOne
    private Role role;
    public User() {
    }

    public User(long id, String user_name, String pass, String fullname, String phone, String email, String avatar, Role role) {
        this.id = id;
        this.user_name = user_name;
        this.pass = pass;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
