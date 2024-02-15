package org.jsp.employeeapp.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEmployeeTable {
	public static void main(String[] args) {
		Connection con = null;
        Statement st = null;
        String qry = "Create table employee(id int not null,name varchar(45),designation varchar(45) not null,salary decimal(10,2) not null,email varchar(45) not null unique,phone bigint(20) not null unique,password varchar(45) not null,primary key(id))";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_app", "root", "admin");
            st= con.createStatement();
            boolean res = st.execute(qry);
            System.out.println("Employee Table Created");
            System.out.println(res);
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }finally{
            if(con!=null){
                try{
                    con.close();
                    System.out.println("Connection closed");
                }catch(SQLException e){
                    e.printStackTrace();
                }    
            }
            if(st!=null){
                try{
                    con.close();
                    System.out.println("Connection closed");
                }catch(SQLException e){
                    e.printStackTrace();
                }    
            }
        }
	}
}


