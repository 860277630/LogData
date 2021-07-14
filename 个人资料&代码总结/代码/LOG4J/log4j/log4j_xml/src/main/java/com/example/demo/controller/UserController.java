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
    
    static final Logger LOGGER = Logger.getLogger("traceLog");
    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id,Model model){
        MDC.put("sessionId" , "f9e287fad9e84cff8b2c2f2ed92adbe6");
        MDC.put("cityId" , String.valueOf(1));
        MDC.put("siteName" , "北京");
        MDC.put("userName" , "userwyh");
        MDC.put("mobile" , "110");
        MDC.put("mchId" , String.valueOf(12));
        MDC.put("mchName", "商户名称");
        User user =  userService.Sel(id);
        model.addAttribute("user", user);
        LOGGER.info("xxxxxxxxxxxxxxxxxx"+user.toString());
        return "index";
    }
}