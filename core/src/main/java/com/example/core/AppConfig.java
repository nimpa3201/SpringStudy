package com.example.core;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // 설정 정보
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new  MemoryMemberRepository()


    //예상
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    @Bean //스프링 컨테이너에 빈 등록
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
       return new OrderServiceImpl(memberRepository(),discountPolicy());

    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

// new MemoryMemberRepository() 부분 중복이 제거 되었다. MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다
// AppConfig 역활과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
