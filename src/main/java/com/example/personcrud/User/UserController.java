package com.example.personcrud.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("listUsers", list);
        return "users";
    }

    @GetMapping("/showByName")
    public String showUsersList(Model model, @Param("keyword") String keyword){
        List<User> listUsers = userService.findAllByName(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("postTitle", "Add New User");
        return "user-form";
    }


    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message","The user has been saved");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user",  user);
            model.addAttribute("pageTitle", "Edit User (Id: " + id + ") ");
            return "user-form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "The user with ID" + id + "has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
}
