package com.example.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //주석 대신 사용할 수 있음
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass()); //getClass()대신 LogTestController.class가능


    @RequestMapping("/log-test")
    public String LogTest(){
        String name = "Spring";
       // System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

 }
