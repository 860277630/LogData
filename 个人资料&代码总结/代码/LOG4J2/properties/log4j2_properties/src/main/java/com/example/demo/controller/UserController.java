package com.example.demo.controller;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//@Slf4j
public class UserController {

    //static final Logger LOGGER = LogManager.getLogger(UserController.class);
    static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        MDC.put("requestID",requestID);
        User user =  userService.Sel(id);
        model.addAttribute("user", user);
        LOGGER.info("xxxxxxxxxxxxxxxxxx"+user.toString());
        //log.info("xxxxxxxxxxxxxxxxx"+user.toString());
        return "index";
    }

}