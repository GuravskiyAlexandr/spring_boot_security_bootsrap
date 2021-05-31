package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {
    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/")
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserById(user.getId());
        model.addAttribute("userAuthentication", userFromDb);
        model.addAttribute("roleAdmin", new Role(2L, "ROLE_ADMIN"));
        if (userFromDb.getRoles().contains(new Role(2L, "ROLE_ADMIN"))) {
            model.addAttribute("myUsersList", userService.getAllUsers());
        }
        model.addAttribute("user", new User(Sets.newHashSet(new Role("ROLE_USER"), new Role("ROLE_ADMIN"))));
        return "index";
    }

    @PostMapping("/admin/userAdd")
    public String addNewUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User newUser) {
        userService.userEdit(newUser);
        return "redirect:/";
    }

    @GetMapping(value = "/admin/user/{id}")
    @ResponseBody
    public User findUserForEdit(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/admin/delete")
    public String delete(@ModelAttribute("id") User user) {
        userService.userDelete(user);
        return "redirect:/";
    }
}
