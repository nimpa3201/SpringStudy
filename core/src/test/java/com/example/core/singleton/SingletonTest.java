package com.example.core.singleton;

import com.example.core.AppConfig;
import com.example.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
