package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 위 코드는 AppConfig 클래스에 정의된 설정 정보를 사용하여 스프링 ApplicationContext를 생성한다.
        // 이는 ApplicationContext가 AppConfig에 정의된 객체(빈)들을 생성하고 관리하도록 하는 것을 의미한다.
        // AppConfig 클래스에는 빈 정의 같은 설정이 포함되어 있다.

        MemberService memberService = applicationContext.getBean("memberService",MemberService.class); // 인자로 메서드 이름과 빈환 타입을 받음


        //new Member(1L,"memberA", Grade.VIP); Member에 command+option+v 단축키 이용하면 아래와 같이 됨
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = "+memberA.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
