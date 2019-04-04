package com.qf.anno;

import java.sql.Connection;

@DBAnno(value = "ORACLE")
public class TestAnno2 {

    public Connection getConnection(){
        try {
            Class c = TestAnno2.class;
            DBAnno dbAnno = (DBAnno) c.getAnnotation(DBAnno.class);
            String DriverNAME = null;

            if (dbAnno.value().equals("MYSQL")){
                DriverNAME = "com.mysql.jdbc.Driver";
                System.out.println("MYSQL");
            }
            if (dbAnno.value().equals("ORACLE")){
                DriverNAME = "oracle.jdbc.OracleDriver";
                System.out.println("oracle");
            }
            if (dbAnno.value().equals("SQLSERVER")){
                DriverNAME = "SQLSERVER.dDriver";
                System.out.println("SQLSERVER");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        TestAnno2 anno2 = new TestAnno2();
        anno2.getConnection();
    }

}
