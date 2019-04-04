package com.qf.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MySecondAnno {

    String name() default "anno";

    String value() default "复仇者联盟5,战火重燃";
}
