package com.example.core.order;

import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {
    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
         memberRepository.save(new Member(1L,"name", Grade.VIP)); //회원 추가

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository , new FixDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA",10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}