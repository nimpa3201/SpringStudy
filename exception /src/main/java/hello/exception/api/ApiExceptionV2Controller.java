package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class ApiExceptionV2Controller {


    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){

        if (id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
            //500 html 반환 문제를 해결하려면 오류 페이지 컨트롤러도 JSON응답 할 수 있도록 수정해야한다.
        }

        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id,"hello "+id);
    }


    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }

}
