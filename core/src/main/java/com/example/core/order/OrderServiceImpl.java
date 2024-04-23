package com.example.core.order;

import com.example.core.annotation.MainDiscountPolicy;
import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import com.example.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 필드값에 대해 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


   /* OrderServiceImpl 클래스에서 @Autowired 생성자를 제거하고, 롬복의 @RequiredArgsConstructor 애너테이션을 추가하여 final 필드에 대한 생성자를 자동으로 생성했다.

    이 변경은 코드를 더 간결하게 만들고, 의존성 주입을 보다 명확하게 관리할 수 있도록 했다.  */



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member  =memberRepository.findById(memberId);
        int discountPrice=  discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
