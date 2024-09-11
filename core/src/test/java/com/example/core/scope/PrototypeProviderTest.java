package com.example.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeProviderTest {

    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
       ClientBean clientBean1 = ac.getBean(ClientBean.class); //클라이언트 A
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class); //클라이언트 B
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    static class ClientBean{
        @Autowired
        private Provider<PrototypeBean> Provider;

        public int logic(){
            PrototypeBean prototypeBean =Provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count=0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy");
        }
    }
}

