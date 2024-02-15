package org.jsp.employeeapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeDao {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_app", "root", "admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String saveEmployee(Employee emp) {
		String qry = "insert into employee values(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(1, emp.getId());
			pst.setString(2, emp.getName());
			pst.setString(3, emp.getDesignation());
			pst.setDouble(4, emp.getSalary());
			pst.setString(5, emp.getEmail());
			pst.setLong(6, emp.getPhone());
			pst.setString(7, emp.getPassword());
			pst.executeUpdate();
			return "Employee Saved";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Employee Not Saved";
		}
	}

	public String updateEmployee(Employee emp) {
		String qry = "update employee set name=?,designation=?,salary=?,email=?,phone=?,password=? where id=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(7, emp.getId());
			pst.setString(1, emp.getName());
			pst.setString(2, emp.getDesignation());
			pst.setDouble(3, emp.getSalary());
			pst.setString(4, emp.getEmail());
			pst.setLong(5, emp.getPhone());
			pst.setString(6, emp.getPassword());
			pst.executeUpdate();
			return "Employee Updated";
		} catch (SQLException e) {
			e.printStackTrace();
			return "EMployee Not Updated";
		}
	}

	public Employee findEmpById(int id) {
		String qry = "select * from employee where id=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			Employee emp = new Employee();
			if (rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDesignation(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setEmail(rs.getString(5));
				emp.setPhone(rs.getLong(6));
				emp.setPassword(rs.getString(7));
				return emp;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Employee verifyByPhone(long phone, String password) {
		String qry = "select * from employee where phone = ? and password = ?";
		try {
			pst = con.prepareStatement(qry);
			pst.setLong(1, phone);
			pst.setString(2, password);
			pst.executeQuery();
			Employee emp = new Employee();
			if (rs.next()) {
				emp.setId(rs.getInt(1));
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Employee verifyByEmail(String email, String password) {
		String qry = "select * from employee where phone = ? and password = ?";
		try {
			pst = con.prepareStatement(qry);
			pst.setString(1, email);
			pst.setString(2, password);
			pst.executeQuery();
			Employee emp = new Employee();
			if (rs.next()) {
				emp.setId(rs.getInt(1));
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Employee findEmpByDesign(String designation) {
		String qry = "select * from employee where designation=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setString(1, designation);
			rs = pst.executeQuery();
			Employee emp = new Employee();
			if (rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDesignation(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setEmail(rs.getString(5));
				emp.setPhone(rs.getLong(6));
				emp.setPassword(rs.getString(7));
				return emp;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Employee> filterEmpBySalary(double lowsal, double highsal) {
		String sql = "select * from employee where lowsal>=? and highsal<=?";
		List<Employee> emp1 = new ArrayList<Employee>();
		try {
			pst = con.prepareStatement(sql);
			pst.setDouble(1, lowsal);
			pst.setDouble(2, highsal);
			rs = pst.executeQuery();
			Employee emp = new Employee();
			while (rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDesignation(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setEmail(rs.getString(5));
				emp.setPhone(rs.getLong(6));
				emp.setPassword(rs.getString(7));
				emp1.add(emp);
			}
			return emp1;
		} catch (SQLException e) {
			e.printStackTrace();
			return emp1;
		}
	}

	public String exit() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Application Closed";
	}
}