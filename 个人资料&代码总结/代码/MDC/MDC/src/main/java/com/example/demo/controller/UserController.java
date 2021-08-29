package com.example.demo.controller;

import com.example.demo.utils.MdcThreadUtils;
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

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.UUID.randomUUID;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */
//logback 对线程的维护不如log4j   在进行异步调用时  需要主动的去put  进去
@Controller
@RequestMapping("/testBoot")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //普通情况  无异步情况  MDC的使用
    @RequestMapping("getUserByNoThread/{id}")
    public String getUserByNoThread(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        log.info("==============普通情况MDC使用，requestID：==="+requestID);
        MDC.put("requestID",requestID);
        User user =  userService.Sel(id);
        log.info("MDC_MSG");
        model.addAttribute("user", user);
        return "index";
    }

    //异步情况下MDC不进行异步操作    logback下MDC不会自动进行异步通信
    @RequestMapping("getUserBySyns1/{id}")
    public String getUserBySyns1(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        log.info("=====MDC不进行异步操作，requestID：==="+requestID);
        MDC.put("requestID",requestID);
        log.info("====异步非线程池外日志====");
        //异步执行式
        for(int i = 0; i<3;i++){
            new Thread(()->{
                log.info("非线程池异步执行，controller层日志......");
                userService.Sel(id);
            }).start();
        }
        log.info("====异步非程池外日志====");
        //线程池式
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i  = 0 ; i< 3; i++){
            executorService.submit(()->{
                log.info("线程池异步执行，controller层日志......");
                userService.Sel(id);
            });}
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }


    //异步情况下MDC进行异步操作方式一  logback下MDC不会自动进行异步通信
    // put方式  适用于参数比较少的情况
    @RequestMapping("getUserBySyns2/{id}")
    public String getUserBySyns2(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        log.info("=====MDC不进行异步操作，requestID：==="+requestID);
        MDC.put("requestID",requestID);
        log.info("====异步非线程池外日志====");
        //异步执行式
        for(int i = 0; i<3;i++){
            new Thread(()->{
                MDC.put("requestID",requestID);
                log.info("非线程池异步执行，controller层日志......");
                userService.Sel(id);
            }).start();
        }
        log.info("====异步非程池外日志====");
        //线程池式
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i  = 0 ; i< 3; i++){
            executorService.submit(()->{
                MDC.put("requestID",requestID);
                log.info("线程池异步执行，controller层日志......");
                userService.Sel(id);
            });}
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    //异步情况下MDC进行异步操作方式二  logback下MDC不会自动进行异步通信
    // map方式  适用于参数比较多的情况
    @RequestMapping("getUserBySyns3/{id}")
    public String getUserBySyns3(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        log.info("=====MDC不进行异步操作，requestID：==="+requestID);
        MDC.put("requestID",requestID);
        //map中包含所有已经put进入到MDC的属性
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        log.info("====异步非线程池外日志====");
        //异步执行式
        for(int i = 0; i<3;i++){
            new Thread(()->{
                MDC.setContextMap(contextMap);
                log.info("非线程池异步执行，controller层日志......");
                userService.Sel(id);
            }).start();
        }
        log.info("====异步非程池外日志====");
        //线程池式
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i  = 0 ; i< 3; i++){
            executorService.submit(()->{
                MDC.setContextMap(contextMap);
                log.info("线程池异步执行，controller层日志......");
                userService.Sel(id);
            });}
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    //异步情况下MDC进行异步操作方式三  logback下MDC不会自动进行异步通信
    // 工具类方式，更加简洁
    @RequestMapping("getUserBySyns4/{id}")
    public String getUserBySyns4(@PathVariable int id,Model model){
        String requestID = randomUUID().toString().replace("-", "");
        log.info("=====MDC不进行异步操作，requestID：==="+requestID);
        MDC.put("requestID",requestID);
        //map中包含所有已经put进入到MDC的属性

        MdcThreadUtils.threadExecute(()->{
            userService.Sel(id);
        });
/*        MdcThreadUtils.threadExecute(new Runnable() {
            @Override
            public void run() {
                userService.Sel(id);
            }
        });*/
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }
}