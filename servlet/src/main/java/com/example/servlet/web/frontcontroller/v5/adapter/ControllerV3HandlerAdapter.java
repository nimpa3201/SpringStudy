package com.example.servlet.web.frontcontroller.v5.adapter;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws HttpServerErrorException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv ;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() //모든 파라미터 이름 다 가져옴
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
