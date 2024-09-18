package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        //세션 생성 서버 -> 클라이언트
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member,response);  //서버에서 세션, 쿠키 만들고 서버 입장에선 웹브라우저에 응답이 나감

        //요청에 응답 쿠키 저장  클라이언트 -> 서버
        MockHttpServletRequest request = new MockHttpServletRequest(); //웹브라우저가 요청 , 쿠키를 만들어서 서버에 전달
        request.setCookies(response.getCookies()); //mySessionId = uuid ...

        //세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);


        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }



}
