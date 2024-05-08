package com.example.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeBean.class);
        System.out.println("find prototypeBean1");
        prototypeBean prototypeBean1 = ac.getBean(prototypeBean.class); // 호출되는 시점에서 빈 생성
        System.out.println("find prototypeBean2");
        prototypeBean prototypeBean2 = ac.getBean(prototypeBean.class); // 호출되는 시점에서 빈 생성
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();

    }



    @Scope("prototype")
    static class prototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");
        }

        @PreDestroy //빈이 소멸되기 전에 자원을 정리하거나 리소스를 해제해야 하는 경우에 @PreDestroy
        public void destory(){
            System.out.println("prototypeBean.destory");
        }
    }
}
