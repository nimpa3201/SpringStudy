package hello.itemservice.message;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;




@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

        @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null,null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()->ms.getMessage("no_code",null,null)) //예외 터짐
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "기본 메세지", null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage(){ //인자 추가
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test // 국제화
    void defaultLang(){
            assertThat(ms.getMessage("hello",null,null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello",null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test //영어로
    void enLang(){
            assertThat(ms.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }



}
