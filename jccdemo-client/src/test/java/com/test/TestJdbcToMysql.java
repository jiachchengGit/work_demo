package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbcToMysql {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://192.168.0.6:3306/testmysql?user=root&password=chen&useUnicode=true&characterEncoding=UTF8";
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		System.out.println("成功加载MySQL驱动程序");
		Connection conn = DriverManager.getConnection(url);
		System.out.println(conn == null);
	}

}
