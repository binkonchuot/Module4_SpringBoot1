package com.example.module4_spring1.controller;

import com.example.module4_spring1.model.Role;
import com.example.module4_spring1.model.User;
import com.example.module4_spring1.service.IRoleService;
import com.example.module4_spring1.service.IUserService;
import com.sun.glass.ui.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Pageable;
import java.io.File;
import java.util.List;

@Controller
public class HomeController {
     @Autowired
    IUserService userService;

     @Autowired
    IRoleService roleService;
     @Value("uploadPart")
     String uploadPart;

//
//    @Autowired
//    Validate validate;
    @ModelAttribute("roles")
    public List<Role> listRole(){
        return roleService.findAll();
    }
    @ModelAttribute("user")
    public User getUser(){
        return new User();
    }

    @GetMapping("/show")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "id")String option){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("users",userService.findAll( PageRequest.of(page,3, Sort.by(option))));
        modelAndView.addObject("option",option);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreat(){
        ModelAndView modelAndView = new ModelAndView("create");
        User user = new User();
        List<Role> list = roleService.findAll();
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        User user = userService.findById(id);
        List<Role> list = roleService.findAll();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @GetMapping("delete/{id}")
    public ModelAndView showdelete(@PathVariable Long id){
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable Long id,@ModelAttribute User user){
        userService.delete(id);
        return new ModelAndView("redirect:/show");
    }
    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute(value = "user") User user, BindingResult bindingResult, @RequestParam MultipartFile upImg) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            List<Role> list = roleService.findAll();
            modelAndView.addObject("user", user);
            modelAndView.addObject("role", list);
            return modelAndView;
        }
        String nameFile = upImg.getOriginalFilename();
        try {
            System.out.println(uploadPart);
            FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\ADMIN\\IdeaProjects\\module4_spring1\\src\\main\\resources\\static\\img\\" + nameFile));
            user.setAvatar("/img/" + nameFile);
        } catch (Exception e) {
            user.setAvatar("/img/abc.jpeg");
            e.printStackTrace();
        }
        userService.save(user);
        return new ModelAndView("redirect:/show");
    }

}
