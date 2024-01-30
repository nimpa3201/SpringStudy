package com.example.core.discount;

import com.example.core.AppConfig;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


class DiscountPolicyTest {

   DiscountPolicy discountPolicy ;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.discountPolicy();
    }

    @Test
    @DisplayName("vip는 10% 할인이 적용되어야 합니다")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount =discountPolicy.discount(member,10000);

        //then
        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("vip가 아니면 할인이 적용되지 않아야 합니다")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount =discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(0);

    }




}