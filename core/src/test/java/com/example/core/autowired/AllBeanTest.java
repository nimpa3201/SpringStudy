package com.example.core.autowired;

import com.example.core.AutoAppConfig;
import com.example.core.discount.DiscountPolicy;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"UserA", Grade.VIP);
        int fixDiscountPolicy = discountService.discount(member,10000,"fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);

        assertThat(fixDiscountPolicy).isEqualTo(1000); //fix 테스트

        int rateDiscountPolicy = discountService.discount(member,20000,"rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000); //rate 테스트
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;
        //@Autowired 생략함
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList){
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);

        }

        public int discount(Member member, int price , String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }

}
