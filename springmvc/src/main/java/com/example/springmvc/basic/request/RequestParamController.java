package com.example.springmvc.basic.request;


import com.example.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // HttpServletRequest와 HttpServletResponse를 사용한 요청 파라미터 처리 예제
    @RequestMapping("request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 파라미터에서 username과 age를 추출
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 로그로 username과 age 출력
        log.info("username = {} , age = {}", username, age);

        // 응답으로 "ok" 메시지 전송
        response.getWriter().write("ok");
    }

    // @RequestParam 어노테이션을 사용한 요청 파라미터 처리 예제
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String myName,
            @RequestParam("age") int myAge) {
        // 로그로 myName과 myAge 출력
        log.info("username = {} , age = {}", myName, myAge);
        return "ok";
    }

    // @RequestParam 어노테이션 사용시 파라미터 이름을 생략한 예제 , 변수명과 파라미터 네임이 같아야 함
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        // 로그로 username과 age 출력
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    // @RequestParam 어노테이션 없이 요청 파라미터 처리 예제
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // 변수명이 요청 파라미터 이름과 동일해야 한다
        // 로그로 username과 age 출력
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    // 필수 요청 파라미터 처리 예제
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        // age는 요청 파라미터에서 빠질 수 있음
        // 빠질 경우 null 값이 들어가야 하기 때문에 Integer로 선언
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    // 요청 파라미터 기본 값 설정 예제
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        // 기본 값이 설정된 경우 파라미터가 없을 때 기본 값을 사용
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    // 요청 파라미터를 Map으로 처리하는 예제
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // 요청 파라미터를 Map으로 받아서 처리
        log.info("username = {} , age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttributeV1(@RequestParam String username,
                                  @RequestParam int age){
        HelloData helloData =new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV3( HelloData helloData){
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }
}
