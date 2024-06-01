package com.example.core.web;

import com.example.core.common.MyLogger;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        System.out.println(myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        //Thread.sleep(1000); 각 요청의 로그 순서 섞이지만 수행되는 거 볼 수 있음
        logDemoService.logic("testId");
        return "OK";
    }
}
