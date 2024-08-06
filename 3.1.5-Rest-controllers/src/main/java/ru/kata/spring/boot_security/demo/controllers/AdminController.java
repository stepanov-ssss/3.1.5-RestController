package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.showUsers());
        model.addAttribute("username", principal.getName());
        model.addAttribute("userThis", userService.findByUsername(principal.getName()));
        model.addAttribute("roles", userService.findRoles());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usersId", userService.findUserById(id));
        return "/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userService.findRoles());
        return "admin";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", userService.findRoles());
        model.addAttribute("user", userService.findUserById(id));
        return "/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
