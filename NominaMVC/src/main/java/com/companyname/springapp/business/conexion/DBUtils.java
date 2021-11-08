package com.companyname.springapp.business.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

//import javax.servlet.jsp.tagext.TryCatchFinally;
//
//import org.eclipse.jdt.internal.compiler.ast.TryStatement;

public class DBUtils {

	public DriverManagerDataSource conexion() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/nominamvc");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
}
	