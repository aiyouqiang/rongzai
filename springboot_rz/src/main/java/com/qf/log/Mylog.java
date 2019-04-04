package com.qf.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Mylog {
    String value(); //记录方法功能

    String description();//详细描述方法
}
