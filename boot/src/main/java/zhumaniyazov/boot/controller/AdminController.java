package zhumaniyazov.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zhumaniyazov.boot.model.User;
import zhumaniyazov.boot.service.RoleService;
import zhumaniyazov.boot.service.UserService;
import zhumaniyazov.boot.util.UserValidator;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;
    private final UserService userService;

    private final RoleService roleService;


    public AdminController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "admin/show";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";

    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";

    }

    @PostMapping
    public String create(Model model, @ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRoles());
            return "admin/new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit";
    }


    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

