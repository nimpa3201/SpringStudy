package com.example.core.singleton;

import com.example.core.AppConfig;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 호출할 대 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        
        //3. 참조값 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberservice1 != memberservice2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonservice1 = SingletonService.getInstance();
        SingletonService singletonservice2 = SingletonService.getInstance();

        System.out.println("singletonservice1 = " + singletonservice1);
        System.out.println("singletonservice2 = " + singletonservice2);

        assertThat(singletonservice1).isSameAs(singletonservice2);


    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);


    }


}
