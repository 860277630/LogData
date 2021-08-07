package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import static java.util.UUID.randomUUID;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@Controller
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;
    
    static final Logger LOGGER = Logger.getLogger(UserController.class);
    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        System.out.println("requestID"+requestID);
        MDC.put("requestID",requestID);
        User user =  userService.Sel(id);
        model.addAttribute("user", user);
        LOGGER.info("xxxxxxxxxxxxxxxxxx"+user.toString());
        return "index";
    }
}