package com.example.demo.controller;

import com.sun.istack.internal.logging.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.concurrent.atomic.AtomicReference;

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

    private static Logger log = Logger.getLogger(UserController.class);


    //普通情况下MDC的使用
    @RequestMapping("getUserByNoThread/{id}")
    public String getUserByNoThread(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        System.out.println("requestID"+requestID);
        MDC.put("requestID",requestID);
        User user =  userService.Sel(id);
        model.addAttribute("user", user);
        log.info("MDC_MSG");
        return "index";
    }
    //异步情况下MDC的使用方式一,同样适用于线程池
    @RequestMapping("getUserBySyns1/{id}")
    public String getUserBySyns1(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        MDC.put("requestID",requestID);
        log.info("MDC加入，我要打印日志了");
        User user = new User();
        new Thread(()->{
            //重新抛入
            MDC.put("requestID",requestID);
            userService.Sel(id);
        }).start();
        model.addAttribute("user", user);
        log.info("xxxxxxxxxxxxxxxxxx"+ user.toString());
        return "index";
    }
    //异步情况下MDC的使用方式二，同样适用于线程池
    @RequestMapping("getUserBySyns2/{id}")
    public String getUserBySyns2(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        MDC.put("requestID",requestID);
        log.info("MDC加入，我要打印日志了");
        User user = new User();
        new Thread(()->{
            //重新抛入
            MDC.put("requestID",requestID);
            userService.Sel(id);
        }).start();
        model.addAttribute("user", user);
        log.info("xxxxxxxxxxxxxxxxxx"+ user.toString());
        return "index";
    }
}
