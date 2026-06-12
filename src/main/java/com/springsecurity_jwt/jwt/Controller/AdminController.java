package com.springsecurity_jwt.jwt.Controller;

import com.springsecurity_jwt.jwt.Model.Users;
import com.springsecurity_jwt.jwt.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/welcome")
    public String welcomeAdmin(Principal principal) {
        return """
        <h1 align='center'>Welcome, Admin! Mr. %s</h1>""".formatted(principal.getName());
    }

    @RequestMapping("/getusers")
    public List<Users>  getAllUsers()
    {
        return adminService.getAllUsers();
    }

}
