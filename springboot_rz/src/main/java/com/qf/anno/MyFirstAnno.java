package com.qf.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyFirstAnno {

    String name() default "anno";

    String value() default "复仇者联盟4,终局之战";
}
