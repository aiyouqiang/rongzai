package com.qf.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {

    public static void main(String[]args){
        Md5Hash md5Hash = new Md5Hash("jack","jack",1024);
        System.out.println(md5Hash.toString());

         md5Hash = new Md5Hash("lisi","lisi",1024);
        System.out.println(md5Hash.toString());
    }
}
