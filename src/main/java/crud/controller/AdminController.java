package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(ModelMap model){
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "/users";
    }

    @GetMapping("admin")
    public String show(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("person", userService.getUser(id));
        return "admin";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("newUser") User user) {
        return "create";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("edit")
    public String edit(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("updateUser", userService.getUser(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateUser") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("delete")
    public String delete(@RequestParam("id")Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
