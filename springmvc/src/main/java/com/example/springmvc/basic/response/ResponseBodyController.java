package com.example.springmvc.basic.response;


import com.example.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j  // 로깅 기능을 위한 애너테이션
//@Controller
//@ResponseBody
@RestController  // RESTful 웹 서비스를 처리하는 컨트롤러임을 나타내는 애너테이션
public class ResponseBodyController {

    // 문자열 응답을 직접 HttpServletResponse 객체를 사용해 전송하는 메서드
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");  // 응답 메시지 바디에 "ok" 문자열을 씀
    }

    // ResponseEntity를 사용해 문자열 응답을 전송하는 메서드
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);  // 상태 코드 200과 함께 "ok" 문자열을 반환
    }

    // 문자열 응답을 간단히 전송하는 메서드
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";  // 문자열 "ok"를 응답 메시지 바디에 전송
    }

    // ResponseEntity를 사용해 JSON 형식의 객체를 전송하는 메서드
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("미노이");  // HelloData 객체에 사용자 이름 설정
        helloData.setAge(20);  // HelloData 객체에 나이 설정
        return new ResponseEntity<>(helloData, HttpStatus.OK);  // 상태 코드 200과 함께 HelloData 객체를 JSON 형식으로 반환
    }

    // JSON 형식의 객체를 간단히 전송하는 메서드
    @ResponseStatus(HttpStatus.OK)  // 응답 상태 코드를 200으로 설정
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("미노이");  // HelloData 객체에 사용자 이름 설정
        helloData.setAge(20);  // HelloData 객체에 나이 설정
        return helloData;  // HelloData 객체를 JSON 형식으로 반환
    }
}
