package com.qf.anno;

import java.lang.reflect.Method;

@MyFirstAnno
public class TestAnno {

    @MySecondAnno(value = "展示信息",name = "seAnno")
    public void show(){
        System.out.println("紫薯精");
    }
    public static void main(String[] args) throws Exception{
        TestAnno testAnno = new TestAnno();
        Class c = testAnno.getClass();

        MyFirstAnno anno = (MyFirstAnno) c.getAnnotation(MyFirstAnno.class);

        System.out.println(anno);

        System.out.println(anno.name()+"---"+anno.value());

        Method method = c.getDeclaredMethod("show");

        MySecondAnno mySecondAnno = method.getDeclaredAnnotation(MySecondAnno.class);
        System.out.println(mySecondAnno.value()+"------"+mySecondAnno.name());
        if (mySecondAnno!=null){

        }


    }
}
