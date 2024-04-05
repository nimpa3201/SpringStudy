package com.example.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses= AutoAppConfig.class,
        //basePackages = "com.example.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //이 필터는 @Configuration이 등록된 AppConfig등의 설정 정보 등록과 실행을 막는다
) // 스프링빈 자동으로 끌어올림
public class AutoAppConfig {
}
