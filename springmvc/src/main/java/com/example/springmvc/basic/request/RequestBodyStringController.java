package com.example.springmvc.basic.request;


import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j // 로그 기능을 사용하기 위한 Lombok 어노테이션
@Controller // 스프링 MVC 컨트롤러임을 나타내는 어노테이션
public class RequestBodyStringController {

    // HttpServletRequest와 HttpServletResponse를 사용하여 요청 본문을 읽는 방법
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); // 요청 본문을 읽기 위한 InputStream
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // InputStream을 String으로 변환

        log.info("messageBody = {}", messageBody); // 로그에 요청 본문 출력
        response.getWriter().write("ok"); // 응답 본문에 "ok" 작성
    }

    // InputStream과 Writer를 사용하여 요청 본문을 읽고 응답을 작성하는 방법
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // InputStream을 String으로 변환

        log.info("messageBody = {}", messageBody); // 로그에 요청 본문 출력
        responseWriter.write("ok"); // 응답 본문에 "ok" 작성
    }

    // HttpEntity를 사용하여 요청 본문을 읽고 응답을 작성하는 방법
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody(); // HttpEntity에서 본문 추출
        log.info("messageBody = {}", messageBody); // 로그에 요청 본문 출력
        return new HttpEntity<>("ok"); // 응답 본문에 "ok" 작성하여 반환
    }

    // RequestEntity와 ResponseEntity를 사용하여 요청 본문을 읽고 상태 코드와 함께 응답을 작성하는 방법
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody(); // RequestEntity에서 본문 추출
        log.info("messageBody = {}", messageBody); // 로그에 요청 본문 출력
        return new ResponseEntity<>("ok", HttpStatus.CREATED); // 상태 코드 201(CREATED)와 함께 응답 본문에 "ok" 작성하여 반환
    }

    // @RequestBody를 사용하여 요청 본문을 읽고 @ResponseBody로 응답을 작성하는 방법
    @ResponseBody // 반환 값을 응답 본문으로 사용하기 위한 어노테이션
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV5(@RequestBody String messageBody) {
        log.info("messageBody = {}", messageBody); // 로그에 요청 본문 출력
        return "ok"; // 응답 본문에 "ok" 작성하여 반환
    }
}
