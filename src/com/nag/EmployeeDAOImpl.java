package com.nag;
import com.nag.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDAOImpl {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mytestdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	
	public void addEmployee(Employee e) throws Exception {
		
		Connection dbConnection = null;
		String sql = "INSERT INTO EMPLOYEES " + "(NAME, ID, LOCATION) VALUES (?, ?, ?)";

		try {
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setInt(2, e.getId());
			ps.setString(3, e.getLocation());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException exception) {
			throw new RuntimeException(exception);

		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException exception) {}
			}
		}
	}
	
	public Employee getEmployee(int id) throws Exception {
		
		Connection dbConnection = null;
		String sql = "SELECT * FROM EMPLOYEES WHERE ID="+id;

		try {
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String name = rs.getString(1);
			String location = rs.getString(3);
			Employee e = new Employee(name, id, location);
			return e;

		} catch (SQLException exception) {
			throw new RuntimeException(exception);

		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException exception) {}
			}
		}
		
	}

}
