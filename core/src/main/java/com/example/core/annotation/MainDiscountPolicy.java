package com.example.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
@Component
public @interface MainDiscountPolicy  {
}
