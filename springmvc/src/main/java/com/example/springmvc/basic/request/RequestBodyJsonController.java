package com.example.springmvc.basic.request;

import com.example.springmvc.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 이 컨트롤러는 JSON 형식의 요청 본문을 처리하는 다양한 방법을 보여줍니다.
 * 예시 요청 본문: {"username":"hello", "age":20}
 * Content-Type: application/json
 */
@Slf4j // 로그 기능을 사용하기 위한 Lombok 어노테이션
@Controller // 스프링 MVC 컨트롤러임을 나타내는 어노테이션
public class RequestBodyJsonController {

    // JSON 데이터를 파싱하기 위한 Jackson ObjectMapper 인스턴스
    private ObjectMapper objectMapper = new ObjectMapper();

    // HttpServletRequest와 HttpServletResponse를 사용하여 JSON 요청 본문을 읽고 처리하는 방법
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 본문을 읽기 위한 InputStream
        ServletInputStream inputStream = request.getInputStream();
        // InputStream을 String으로 변환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // 요청 본문을 로그에 출력
        log.info("messageBody = {}", messageBody);

        // JSON 문자열을 HelloData 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        // HelloData 객체의 필드를 로그에 출력
        log.info("username =  {} , age = {}", helloData.getUsername(), helloData.getAge());

        // 응답 본문에 "ok" 작성
        response.getWriter().write("ok");
    }

    // @RequestBody를 사용하여 JSON 요청 본문을 String으로 직접 받아 처리하는 방법
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        // 요청 본문을 로그에 출력
        log.info("messageBody = {}", messageBody);

        // JSON 문자열을 HelloData 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        // HelloData 객체의 필드를 로그에 출력
        log.info("username =  {} , age = {}", helloData.getUsername(), helloData.getAge());

        // 응답 본문에 "ok" 반환
        return "ok";
    }

    // @RequestBody를 사용하여 JSON 요청 본문을 HelloData 객체로 직접 받아 처리하는 방법
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        // HelloData 객체의 필드를 로그에 출력
        log.info("username =  {} , age = {}", helloData.getUsername(), helloData.getAge());

        // 응답 본문에 "ok" 반환
        return "ok";
    }

    // HttpEntity를 사용하여 JSON 요청 본문을 HelloData 객체로 직접 받아 처리하는 방법
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        // HttpEntity에서 본문을 추출하여 HelloData 객체로 변환
        HelloData data = httpEntity.getBody();

        // HelloData 객체의 필드를 로그에 출력
        log.info("username =  {} , age = {}", data.getUsername(), data.getAge());

        // 응답 본문에 "ok" 반환
        return "ok";
    }

    // @RequestBody를 사용하여 JSON 요청 본문을 HelloData 객체로 직접 받아 처리하고, 이를 그대로 응답 본문으로 반환하는 방법
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        // HelloData 객체의 필드를 로그에 출력
        log.info("username =  {} , age = {}", data.getUsername(), data.getAge());

        // HelloData 객체를 응답 본문으로 반환
        return data;
    }
}
