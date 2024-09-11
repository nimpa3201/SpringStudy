package com.example.servlet.web.frontcontroller.v5;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> HandlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        HandlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        HandlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        HandlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4 추가
        HandlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        HandlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        HandlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }
//handler -- controller
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);
        String viewName = mv.getViewName();
        MyView view = viewResolve(viewName);
        view.render(mv.getModel(), request, response);
    }




    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return HandlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
             return adapter;
            }

        }throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.");


    }



    private static MyView viewResolve(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
