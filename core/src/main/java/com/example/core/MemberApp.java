package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        //new Member(1L,"memberA", Grade.VIP); Member에 command+option+v 단축키 이용하면 아래와 같이 됨
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = "+memberA.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
