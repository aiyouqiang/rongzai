package com.qf.anno;

public class MyAnno {

    private int age;

    @Deprecated//标记方法过时
    public void setAge(int age){
        this.age = age;
    }
    @Deprecated
    public int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
