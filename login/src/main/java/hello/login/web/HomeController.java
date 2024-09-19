package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

  //  @GetMapping("/")
    public String home() {
        return"home";
    }


   // @GetMapping("/") //required = false -> 로그인하지 않은 사용자도 허용(쿠키값 없는)
    public String homeLogin(@CookieValue(name="memberId",required = false) Long memberId, Model model){
        if(memberId == null){
            return "home";
        }
        //로그인 성공한 사용자
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/") //required = false -> 로그인하지 않은 사용자도 허용(쿠키값 없는)
    public String homeLoginV2(HttpServletRequest request, Model model){
        //세션 관리자에 저장된 회원 정보 조회
        Member member =(Member) sessionManager.getSession(request);

        if(member == null){
            return "home";
        }
        //로그인 성공한 사용자

        model.addAttribute("member",member);
        return "loginHome";
    }

}