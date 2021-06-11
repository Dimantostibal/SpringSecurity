package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        return "/admin";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("newUser") User user) {
        return "create";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam(value = "roleUser", required = false) String roleUser,
                          @RequestParam(value = "roleAdmin", required = false) String roleAdmin) {
        Set<Role> roleSet = new HashSet<>();
        if (roleAdmin != null) {
            roleSet.add(roleService.getRoleByName("ROLE_ADMIN"));
            user.setUserRoles(roleSet);
            userService.add(user);
        } else if (roleUser != null){
            roleSet.add(roleService.getRoleByName("ROLE_USER"));
            user.setUserRoles(roleSet);
            userService.add(user);
        }
        return "redirect:/users";
    }

    @GetMapping("edit")
    public String edit(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("updateUser", userService.getUser(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateUser") User user,
                         @RequestParam(value = "roleUser", required = false) String roleUser,
                         @RequestParam(value = "roleAdmin", required = false) String roleAdmin) {
        Set<Role> roleSet = new HashSet<>();
        if (roleAdmin != null) {
            roleSet.add(roleService.getRoleByName("ROLE_ADMIN"));
            user.setUserRoles(roleSet);
        } else if (roleUser != null){
            roleSet.add(roleService.getRoleByName("ROLE_USER"));
            user.setUserRoles(roleSet);
        }
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("delete")
    public String delete(@RequestParam("id")Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
