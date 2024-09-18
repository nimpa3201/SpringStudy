package hello.login.web.session;


/*
세션 관리는 크게 다음 3가지 기능을 제공하면 된다.
 **세션 생성**
1)sessionId 생성 (임의의 추정 불가능한 랜덤 값)
2)세션 저장소에 sessionId와 보관할 값 저장
3)sessionId로 응답 쿠키를 생성해서 클라이언트에 전달

**세션 조회**
1)클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 값 조회

**세션 만료**
3)클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 sessionId와 값 제거

 */

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SSESION_COOKIE_NAME = "mySsesionId"; //단축키 option+command+c
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); //동시에 여러 요청 접근할때 쓰는 자료형

    /*
    세션 생성
     */
    public void createSession(Object value, HttpServletResponse response) {
        //세션 id 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SSESION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);

    }

    /*
    세션 조회
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SSESION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }
//        Cookie[] cookies = request.getCookies();
//        if(cookies==null){
//            return null;
//        }
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals(SSESION_COOKIE_NAME)){
//                return sessionStore.get(cookie.getValue());
//            }
//
//        }
//        return null;
//
//    }
//


    /*
    세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SSESION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }


    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);


    }
}
